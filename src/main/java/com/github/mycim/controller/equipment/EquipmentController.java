package com.github.mycim.controller.equipment;

import com.github.analysis.ThreadContextHolder;
import com.github.analysis.TransactionID;
import com.github.annotation.Compensable;
import com.github.common.cons.Response;
import com.github.common.cons.TransactionIDEnum;
import com.github.mycim.dto.Infos;
import com.github.mycim.dto.Params;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import com.github.mycim.service.access.IAccessInqService;
import com.github.mycim.service.equipment.IEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Compensable(interfaceClass = IEquipmentController.class, confirmableKey = "EquipmentConfirm", cancellableKey = "EquipmentCancel")
@RequestMapping("/eqp")
public class EquipmentController implements IEquipmentController {

    @Autowired
    private IAccessInqService accessInqService;

    @Autowired
    private IEquipmentService equipmentService;

    @ResponseBody
    @RequestMapping(value = "/chamber_status_change/req", method = RequestMethod.POST)
    @TransactionID(value = TransactionIDEnum.JCW01)
    @Override
    public Response equipmentReportDataCollectionReq(@RequestBody EquipmentReportDataCollectionReqParams
                                                             reportDataCollectionReqParams) {
        String txID = TransactionIDEnum.JCW01.getValue();
        ThreadContextHolder.setTransactionId(txID);

        // 1.用户权限检查
        Params.AccessControlCheckInqParams accessControlCheckInqParams =
                new Params.AccessControlCheckInqParams(true);
        accessControlCheckInqParams.setEquipmentID(reportDataCollectionReqParams.getEquipmentId());
        Infos.ObjCommon objCommon = accessInqService.checkPrivilegeAndGetObjCommon(txID, reportDataCollectionReqParams.getUser(),
                accessControlCheckInqParams);

        return Response.createSucc(txID, equipmentService.sxEqpReportDataCollectionReq(objCommon,
                reportDataCollectionReqParams));
    }
}
