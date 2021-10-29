package com.github.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.annotation.Component;
import com.github.annotation.RepeatSubmit;
import com.github.common.cons.Code;
import com.github.common.cons.MsgRetCodeConfig;
import com.github.ddd.BaseCore;
import com.github.ddd.CoreBeanMapping;
import com.github.javabean.Beans;
import com.github.mainfun.JsonObjectTest;
import com.github.mvc.model.TextValue;
import com.github.mvc.model.oms.BankOperationEnum;
import com.github.mvc.model.oms.BayEnum;
import com.github.mvc.model.oms.ComputerIntegratedManufacturingSystem;
import com.github.mvc.model.User;
import com.github.mvc.model.oms.ConstraintEnum;
import com.github.mvc.model.oms.EquipmentEnum;
import com.github.mvc.model.oms.LogInformationEnum;
import com.github.mvc.model.oms.LotListEnum;
import com.github.mvc.model.oms.LotOperationEnum;
import com.github.mvc.model.oms.LotStartEnum;
import com.github.mvc.model.oms.SystemComponent;
import com.github.mvc.service.IBeanService;
import com.github.mvc.service.IUserService;
import com.github.mvc.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Component
@RequestMapping("/api/bean")
@CrossOrigin(origins = {"http://localhost:8080", "http://192.168.1.146:8080"})
@Slf4j
public class BeanController {

    @Autowired
    private IBeanService beanService;

    @Autowired
    private ComputerIntegratedManufacturingSystem computerIntegratedManufacturingSystem;

    @Autowired
    private CoreBeanMapping coreBeanMapping;


    /**
     * 请求 http://localhost:8028/api/bean/loadSpringFactoriesBean
     *
     * @return 康盼
     */
    @GetMapping(path = "/loadSpringFactoriesBean")
    @ResponseBody
    @RepeatSubmit
    public int loadSpringFactoriesBean() throws Exception {
        IBeanService beanService = (IBeanService) Beans.getByName("beanService");
        beanService.loadSpringFactoriesBean();
        return -1;
    }

    @GetMapping(path = "/initializeAndDestroy/{beanName}")
    @ResponseBody
    @RepeatSubmit
    public int initializeAndDestroy(@PathVariable(name = "beanName") String beanName) {
        User user = (User) Beans.getByName("user");
        IUserService userService = (UserService) Beans.getByName("userService");
        beanService.initBean(beanName);
        beanService.destroyBean();
        return -1;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getMdsJsonView
     *
     * @return 康盼
     */
    @GetMapping(path = "/getMdsJsonView")
    @ResponseBody
    @RepeatSubmit
    @Cacheable(value = "coreCache")
    public String getMdsJsonView() {
        return computerIntegratedManufacturingSystem.getMdsJsonView();
    }


    /**
     * 请求 http://localhost:8028/api/bean/getMdsFilters
     * http://localhost:8080/#/term 的过滤器
     *
     * @return 康盼
     */
    @GetMapping(path = "/getMdsFilters")
    @ResponseBody
    @RepeatSubmit
    public List<TextValue> getMdsFilters() {
        String json = computerIntegratedManufacturingSystem.getMdsJsonView();
        List<SystemComponent> list = JSON.parseArray(json, SystemComponent.class);
        List<TextValue> result = new LinkedList<>();
        list.stream().map(SystemComponent::getFunction).distinct().forEach(ele -> {
            TextValue textValue = new TextValue(ele, ele);
            result.add(textValue);
        });
        return result;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getOmsFilters
     * http://localhost:8080/#/term 的过滤器
     *
     * @return 康盼
     */
    @GetMapping(path = "/getOmsFilters")
    @ResponseBody
    @RepeatSubmit
    public List<TextValue> getOmsFilters() {
        String json = computerIntegratedManufacturingSystem.getOmsJsonView();
        List<SystemComponent> list = JSON.parseArray(json, SystemComponent.class);
        List<TextValue> result = new LinkedList<>();
        list.stream().map(SystemComponent::getFunction).distinct().forEach(ele -> {
            TextValue textValue = new TextValue(ele, ele);
            result.add(textValue);
        });
        return result;
    }


    /**
     * 请求 http://localhost:8028/api/bean/getOmsJsonView
     *
     * @return 康盼
     */
    @GetMapping(path = "/getOmsJsonView")
    @ResponseBody
    @RepeatSubmit
    public String getOmsJsonView() {
        return computerIntegratedManufacturingSystem.getOmsJsonView();
    }


    /**
     * 请求 http://localhost:8028/api/bean/getTerminology
     *
     * @return 康盼
     */
    @GetMapping(path = "/getTerminology")
    @ResponseBody
    @RepeatSubmit
    public String getTerminology() {
        return computerIntegratedManufacturingSystem.getTerminology();
    }


    /**
     * 请求 http://localhost:8028/api/bean/getLotBatchManagementFlow
     *
     * @return 康盼
     */
    @GetMapping(path = "/getLotBatchManagementFlow")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getLotBatchManagementFlow() {
        List<SystemComponent> result = new LinkedList<>();
        for (LotOperationEnum ele : LotOperationEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }


    /**
     * 请求 http://localhost:8028/api/bean/getBankFlow
     *
     * @return 康盼
     */
    @GetMapping(path = "/getBankFlow")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getBankFlow() {
        List<SystemComponent> result = new LinkedList<>();
        for (BankOperationEnum ele : BankOperationEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getBankInformation
     *
     * @return 康盼
     */
    @GetMapping(path = "/getBankInformation/{key}")
    @ResponseBody
    @RepeatSubmit
    public List<TextValue> getBankInformation(@PathVariable(name = "key") String key) {
        return LogInformationEnum.getLogInformationEnum(key);
    }

    /**
     * 请求 http://localhost:8028/api/bean/getBayInfo
     *
     * @return 康盼
     */
    @GetMapping(path = "/getBayInfo")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getBayInfo() {
        List<SystemComponent> result = new LinkedList<>();
        for (BayEnum ele : BayEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getLotStartFlow
     *
     * @return 康盼
     */
    @GetMapping(path = "/getLotStartFlow")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getLotStartFlow() {
        List<SystemComponent> result = new LinkedList<>();
        for (LotStartEnum ele : LotStartEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getEquipmentFlow
     *
     * @return 康盼
     */
    @GetMapping(path = "/getEquipmentFlow")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getEquipmentFlow() {
        List<SystemComponent> result = new LinkedList<>();
        for (EquipmentEnum ele : EquipmentEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getLotListFlow
     *
     * @return 康盼
     */
    @GetMapping(path = "/getLotListFlow")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getLotListFlow() {
        List<SystemComponent> result = new LinkedList<>();
        for (LotListEnum ele : LotListEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }

    /**
     * 请求 http://localhost:8028/api/bean/getConstraintListFlow
     *
     * @return 康盼
     */
    @GetMapping(path = "/getConstraintListFlow")
    @ResponseBody
    @RepeatSubmit
    public List<SystemComponent> getConstraintListFlow() {
        List<SystemComponent> result = new LinkedList<>();
        for (ConstraintEnum ele : ConstraintEnum.values()) {
            SystemComponent systemComponent = new SystemComponent();
            systemComponent.setIndex(ele.getIndex());
            systemComponent.setFunction(ele.name());
            systemComponent.setDescription(ele.getDescription());
            systemComponent.setRemarks(ele.getDetails());
            result.add(systemComponent);
        }
        return result;
    }

    @Autowired
    private MsgRetCodeConfig msgRetCodeConfig;

    /**
     * 请求 http://localhost:8028/api/bean/msgRetCodeConfig
     *
     * @return 康盼
     */
    @GetMapping(path = "/msgRetCodeConfig")
    @ResponseBody
    @RepeatSubmit
    public Code msgRetCodeConfig() {
        return msgRetCodeConfig.getMsgOk();
    }


    /**
     * 请求 http://localhost:8028/api/bean/coreBeanMapping
     *
     * @return 康盼
     */
    @GetMapping(path = "/coreBeanMapping")
    @ResponseBody
    @RepeatSubmit
    public void coreBeanMapping() {
        JsonObjectTest.AMSSendDTO sendDTO = jsonObjectTest.parseTo();
        sendAlarmInfo(sendDTO);
        log.info("coreBeanMapping:{}", coreBeanMapping);
    }

    @Autowired
    private BaseCore baseCore;

    /**
     * 请求 http://localhost:8028/api/bean/baseCoreInsert
     *
     * @return 康盼
     */
    @GetMapping(path = "/baseCoreInsert")
    @ResponseBody
    @RepeatSubmit
    public List<Object[]> baseCoreInsert() {
        String sql = "insert into OMAMPLAN (id, EQP_ID, CHAMBER_ID, CHAMBER_RKEY) values (?,?, ?, ?)";
        Object[] strs = {1L, "EQP_ID", "CHAMBER_ID", "CHAMBER_RKEY"};
        baseCore.insert(sql, strs);
        return baseCore.queryAll("select * from OMAMPLAN where id = ?", 1L);
    }


    @Autowired
    private JsonObjectTest jsonObjectTest;


    @PostMapping("/ams/v1/open/alarmNotify")
    public void sendAlarmInfo(@RequestBody JsonObjectTest.AMSSendDTO amsSendDTO) {
        log.info("success");
    }

}
