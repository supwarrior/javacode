package com.github.mainfun;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class JsonObjectTest {

    public AMSSendDTO parseTo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Title","cassette Usage Limitation Over");
        jsonObject.put("cassette ID","CFPD0397");

        AMSSendDTO retVal = new AMSSendDTO();
        retVal.setType("SystemMessage");
        retVal.setAppId("OMS");
        retVal.setMessage(jsonObject);
        return retVal;
    }

    @Data
    @NoArgsConstructor
    public static class AMSSendDTO {
        String appId;
        String type;
        JSONObject message;
    }
}