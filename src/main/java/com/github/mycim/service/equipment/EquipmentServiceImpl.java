package com.github.mycim.service.equipment;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.github.jpa.lock.IObjectLockMethod;
import com.github.mycim.boCell.CimMachine;
import com.github.mycim.dto.Infos;
import com.github.mycim.method.equipment.IEquipmentMethod;
import com.github.mycim.method.event.IEventMethod;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import com.github.mycim.model.eqp.params.NonLotDataCollectionEventParams;
import com.github.mycim.model.eqp.result.EquipmentDataCollectionReportResults;
import com.github.mycim.model.eqp.result.EquipmentDataCollectionSpecResults;
import com.github.mycim.service.constraint.IConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private IObjectLockMethod objectLockMethod;

    @Autowired
    private IEquipmentMethod equipmentMethod;

    @Autowired
    private IConstraintService constraintService;

    @Autowired
    private IEventMethod eventMethod;

    @Override
    public List<EquipmentDataCollectionReportResults> sxEqpReportDataCollectionReq(Infos.ObjCommon objCommon, EquipmentReportDataCollectionReqParams reportDataParams) {
        // 1. 判断 reportDataParams.getEquipmentId() 是否为空
        // 2. 判断 reportDataParams.getDataCollectionItemList() 是否为空
        // 3. 对该设备进行上锁
        objectLockMethod.objectLock(CimMachine.class, reportDataParams.getEquipmentId());
        // 4.
        EquipmentDataCollectionSpecResults reportResults =
                equipmentMethod.equipmentDataCollectionReport(objCommon, reportDataParams);

        if (reportResults.isExecuteSpecAction()) {
            doNonLotEdcSpecAction(objCommon, reportDataParams, reportResults);
        }

        eventMethod.nonLotDataCollectionEventMake(objCommon, getNonLotDataCollectionEventParams(reportDataParams,
                reportResults));

        return reportResults.getDataCollectionItems()
                .parallelStream()
                .map(item -> {
                    EquipmentDataCollectionReportResults result = new EquipmentDataCollectionReportResults();
                    result.setDataCollectionPlanId(reportDataParams.getDataCollectionPlanId());
                    result.setDataValue(item.getInputValue());
                    if (CollectionUtils.isNotEmpty(item.getActionCodes())) {
                        result.setActionCode(String.join(",", item.getActionCodes()));
                    }
                    result.setSpecActionResult(item.getSpecCheckResult());
                    result.setItemName(item.getDataItemName());
                    result.setTargetValue(item.getTargetValue());
                    return result;
                }).collect(Collectors.toList());
    }

    private NonLotDataCollectionEventParams getNonLotDataCollectionEventParams(EquipmentReportDataCollectionReqParams reportDataParams,
                                                                               EquipmentDataCollectionSpecResults reportResults) {
        NonLotDataCollectionEventParams collectionEventParams = new NonLotDataCollectionEventParams();
        return collectionEventParams;
    }

    private void doNonLotEdcSpecAction(Infos.ObjCommon objCommon,
                                       EquipmentReportDataCollectionReqParams reportDataParams,
                                       EquipmentDataCollectionSpecResults reportResults) {

        Infos.ConstraintDetailAttributes strEntityInhibitions = new Infos.ConstraintDetailAttributes();

        Map<String, String> specCheckResultMapping = new HashMap<>(16);
        specCheckResultMapping.put("1", "SHUC");
        reportResults.getDataCollectionItems().forEach(item ->  {
            if (specCheckResultMapping.containsKey(item.getSpecCheckResult())) {
                String reasonCodeValue = specCheckResultMapping.get(item.getSpecCheckResult());
                strEntityInhibitions.setReasonCode(reasonCodeValue);
            } else {
                strEntityInhibitions.setReasonCode("SOHL");
            }
            constraintService.sxConstraintEqpAddReq(objCommon, strEntityInhibitions,"");
        });
    }
}
