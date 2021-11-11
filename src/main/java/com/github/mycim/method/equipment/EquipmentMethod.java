package com.github.mycim.method.equipment;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.github.ddd.CoreAttribute;
import com.github.ddd.factory.BaseCoreFactory;
import com.github.ddd.jpa.CoreJpaRepository;
import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.boCell.CimDataCollectionDefinition;
import com.github.mycim.boCell.CimDataCollectionSpecification;
import com.github.mycim.boCell.CimMachine;
import com.github.mycim.doCell.CimDataCollectionSpecItemDO;
import com.github.mycim.doCell.CimEquipmentEdcDO;
import com.github.mycim.dto.EDCDTO;
import com.github.mycim.dto.Infos;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import com.github.mycim.model.eqp.result.EquipmentDataCollectionSpecResults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EquipmentMethod implements IEquipmentMethod {

    @Autowired
    private BaseCoreFactory baseCoreFactory;

    @Autowired
    private CoreJpaRepository coreJpaRepository;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public EquipmentDataCollectionSpecResults equipmentDataCollectionReport(Infos.ObjCommon objCommon, EquipmentReportDataCollectionReqParams reportDataParams) {

        // 1.获取前端传参的 items
        List<Infos.DataCollectionItemInfo> dataCollectionItemList = reportDataParams.getDataCollectionItemList();
        // 2.检查 item 的 dataValue
        boolean invalid = dataCollectionItemList.parallelStream().anyMatch(item -> StringUtils.isBlank(item.getDataValue()));
        // 3.查询设备信息
        CimMachine equipment = baseCoreFactory.getBO(CimMachine.class, reportDataParams.getEquipmentId().getReferenceKey());
        // 4.查询 EDC 集合信息
        CoreAttribute attribute = new CoreAttribute(this.coreJpaRepository, CimEquipmentEdcDO.class, "0");
        Field field = CoreAttribute.findField(CimEquipmentEdcDO.class, "LINK_KEY");
        CoreAttribute.DictionaryContent dictionaryContent = attribute.new DictionaryContent(field);
        EDCDTO.NonLotEdcData nonLotEdcInfo = Optional.ofNullable(dictionaryContent.getAll()).filter(CollectionUtils::isNotEmpty).map(list -> {
            CimEquipmentEdcDO equipmentEdcDO = (CimEquipmentEdcDO)list.get(0);
            EDCDTO.NonLotEdcData edcData = new EDCDTO.NonLotEdcData();
            edcData.setEdcPlanId(equipmentEdcDO.getEdcPlanId());
            edcData.setEdcPlanRKey(equipmentEdcDO.getEdcPlanReferenceKey());
            edcData.setEdcSpecId(equipmentEdcDO.getEdcSpecId());
            edcData.setEdcSpecRKey(equipmentEdcDO.getEdcSpecReferenceKey());
            return edcData;
        }).orElse(null);

        // 5.获取 EDC PLAN 的数据
        CimDataCollectionDefinition planDataCollection =
                baseCoreFactory.getBO(CimDataCollectionDefinition.class, reportDataParams.getDataCollectionPlanId().getReferenceKey());

        // 6.组装 ItemData 数据
        List<EDCDTO.DCItemData> dataItemDataList = dataCollectionItemList.parallelStream().map(item -> {
            EDCDTO.DCItemData dcItemData = new EDCDTO.DCItemData();
            dcItemData.setDataItemName(item.getDataCollectionItemName());
            dcItemData.setValType(item.getDataType());
            dcItemData.setItemType(item.getItemType());
            dcItemData.setInputValue(item.getDataValue());
            dcItemData.setSeqNo(item.getSeqNo());
            return dcItemData;
        }).collect(Collectors.toList());

        // 7.SpecResults 设值
        EquipmentDataCollectionSpecResults reportResults = new EquipmentDataCollectionSpecResults();
        reportResults.setDataCollectionItems(dataItemDataList);
        reportResults.setDataCollectionId(reportDataParams.getDataCollectionPlanId());
        reportResults.setDataCollectionDesc(planDataCollection.getDescription());

        // 8.获取 EDC SPEC 的数据
        ObjectIdentifier specId = ObjectIdentifier.build(nonLotEdcInfo.getEdcSpecId(), nonLotEdcInfo.getEdcSpecRKey());
        CimDataCollectionSpecification spec = baseCoreFactory.getBO(CimDataCollectionSpecification.class, specId.getReferenceKey());

        // 9.SpecResults 继续设值
        reportResults.setDataCollectionSpecId(specId);
        reportResults.setDataCollectionSpecDesc(spec.getDescription());

        // 10.获取 EDC SPEC ITEM 的数据
        CoreAttribute attribute_specItemDO = new CoreAttribute(this.coreJpaRepository, CimDataCollectionSpecItemDO.class, "0");
        Field field_specItemDO = CoreAttribute.findField(CimDataCollectionSpecItemDO.class, "LINK_KEY");
        CoreAttribute.DictionaryContent dictionaryContent_specItemDO = attribute_specItemDO.new DictionaryContent(field_specItemDO);
        List<EDCDTO.DCItemSpecification> specificationList =
                dataCollectionItemList
                        .stream()
                        .map(item -> {
                            String name = item.getDataCollectionItemName();
                            CimDataCollectionSpecItemDO cimDataCollectionSpecItemDO = (CimDataCollectionSpecItemDO) dictionaryContent_specItemDO.get(name).get();
                            return null != item ? EDCDTO.getSpecItemInfo(cimDataCollectionSpecItemDO) : null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        // 11.SpecResults 继续设值
        reportResults.setSpecificationList(specificationList);

        // 12.PLAN 数据处理
        planDataCollection.doValueConversion(dataItemDataList);

        // 13.doSpecCheck
        equipment.doSpecCheck(dataItemDataList, specId, nonLotEdcInfo, spec);

        // 14.ItemData 数据过滤 只支持 Constraint 设备
        boolean isConstraint = dataItemDataList.parallelStream().anyMatch(
                item -> CollectionUtils.isNotEmpty(item.getActionCodes()) &&
                item.getActionCodes().contains("Inhibit-Equipment"));

        // 15.SpecResults 继续设值
        if (isConstraint) {
            reportResults.setExecuteSpecAction(true);
        }

        // 进行doSpecCheck之后，再次将Items设置。
        reportResults.setDataCollectionItems(dataItemDataList);
        return reportResults;

    }


}
