package com.github.mycim.dto;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.common.support.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Params {

    @Data
    public static class ChamberStatusChangeReqPrams {
        private User user;
        private ObjectIdentifier equipmentID;
        private List<Infos.EqpChamberStatus> eqpChamberStatuses;
        private ObjectIdentifier reasonCodeID;
        private String claimMemo;
    }

    @Data
    public static class AccessControlCheckInqParams {
        private User user;
        private ObjectIdentifier equipmentID;
        private ObjectIdentifier stockerID;
        private List<ObjectIdentifier> productIDList;
        private List<ObjectIdentifier> routeIDList;
        private List<ObjectIdentifier> lotIDLists;
        private List<ObjectIdentifier> machineRecipeIDList;
        private List<DepartmentCheckReasonCodeParams> departmentSectionCheckCodes;

        public AccessControlCheckInqParams() {
        }

        public AccessControlCheckInqParams(boolean isInitial) {
            if (isInitial) {
                init();
            }
        }
        private void init() {
            this.equipmentID = new ObjectIdentifier();
            this.stockerID = new ObjectIdentifier();
            this.productIDList = new ArrayList<>();
            this.routeIDList = new ArrayList<>();
            this.lotIDLists = new ArrayList<>();
            this.machineRecipeIDList = new ArrayList<>();
            this.departmentSectionCheckCodes = new ArrayList<>();
        }

    }

    @Data
    public static class DepartmentCheckReasonCodeParams{
        private String department;
        private String section;
        private ObjectIdentifier holdReasonCodeId;
    }

}
