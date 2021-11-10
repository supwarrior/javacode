package com.github.mycim.event;

import com.github.mycim.dto.CoreBaseDTO;
import lombok.Data;

public class Event {

    @Data
    public static abstract class EventRecord implements CoreBaseDTO {
        private static final long serialVersionUID = -2447160342478270662L;
        private EventData eventCommon;
    }

    @Data
    public static class EventData implements CoreBaseDTO {
        private static final long serialVersionUID = -1411658845105408572L;
        private String transactionID;
        private String eventTimeStamp;
        private Double eventShopDate;
        private String userID;
        private String eventMemo;
        private String eventCreationTimeStamp;
    }
}
