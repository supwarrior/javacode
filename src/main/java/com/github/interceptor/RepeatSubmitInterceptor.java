package com.github.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.annotation.Component;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class RepeatSubmitInterceptor extends AbstractRepeatSubmitInterceptor {

    /**
     * 10*1000 单位秒
     */
    @Value("${RetryServiceInterceptor.intervalTime}")
    private volatile Integer intervalTime;

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request) throws Exception {
        String params = JSON.toJSONString(request.getParameterMap());
        Map<String, Object> nowMap = new ConcurrentHashMap<String, Object>(100);
        nowMap.put("params", params);
        nowMap.put("time", System.currentTimeMillis());
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        Object sessionObj = session.getAttribute("data");
        if (sessionObj != null) {
            Map<String, Object> sessionMap = (Map<String, Object>) sessionObj;
            if (sessionMap.containsKey(url)) {
                Map<String, Object> preMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowMap, preMap) && compareTime(nowMap, preMap)) {
                    return true;
                }
            }
        }
        Map<String, Object> sessionMap = new HashMap<String, Object>(100);
        sessionMap.put(url, nowMap);
        session.setAttribute("data", sessionMap);
        return false;
    }

    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
        return (nowMap.get("params")).equals(preMap.get("params"));
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap) {
        return ((Long) nowMap.get("time") - (Long) preMap.get("time")) < intervalTime;
    }
}
