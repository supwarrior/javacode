package com.github.mycim.boCell;

import com.github.annotation.Core;
import com.github.ddd.CoreAttribute;
import com.github.ddd.businessObject.AbstractBO;
import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.doCell.CimDataCollectionSpecItemDO;
import com.github.mycim.doCell.CimEquipmentDO;
import com.github.mycim.dto.EDCDTO;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Core
public class CimMachineBO extends AbstractBO<CimEquipmentDO> implements CimMachine {


    public CimMachineBO(CimEquipmentDO entity) {
        super(entity);
    }

    @Override
    public void doSpecCheck(List<EDCDTO.DCItemData> dataItemDataList, ObjectIdentifier specId,
                            EDCDTO.NonLotEdcData nonLotEdcData,CimDataCollectionSpecification spec) {

        CoreAttribute attribute = new CoreAttribute(this.coreJpaRepository, CimDataCollectionSpecItemDO.class, "0");
        Field field = CoreAttribute.findField(CimDataCollectionSpecItemDO.class, "LINK_KEY");
        CoreAttribute.DictionaryContent dictionaryContent = attribute.new DictionaryContent(field);

        // 1. dcItem 数据赋值
        dataItemDataList.forEach(dcItem -> {
            // 2.获取 EDC SPEC ITEM
            CimDataCollectionSpecItemDO item = (CimDataCollectionSpecItemDO) dictionaryContent.get(dcItem.getDataItemName()).get();
            EDCDTO.DCItemSpecification specInfo = EDCDTO.getSpecItemInfo(item);
            // 3. EDC SPEC ITEM 数据 check
            if (null != specInfo && !equalsIn(dcItem.getSpecCheckResult(), new String[]{"*", "#"})) {
                double numValue = dcItem.getNumValue().doubleValue();
                if (specInfo.getScreenLimitUpperRequired() && specInfo.getScreenLimitUpper() < numValue) {
                    dcItem.setSpecCheckResult("5");
                    Optional.ofNullable(specInfo.getActionCodesUscrn()).ifPresent((codeString) -> {
                        dcItem.setActionCodes(Arrays.asList(codeString.split(",")));
                    });
                }
            }
        });
    }

    public boolean equalsIn(String target, String... strGroup) {
        return null != strGroup && strGroup.length > 0 && ((Stream) Arrays.stream(strGroup).parallel()).anyMatch(str -> str.equals(target));
    }
}
