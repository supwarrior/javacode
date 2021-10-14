package com.github.mvc.model;

import com.alibaba.fastjson.JSON;
import com.github.resource.HtmlUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.LinkedList;
import java.util.List;

/**
 * 计算机集成制造系统
 *
 * @author 康盼Java开发工程师
 */
public class ComputerIntegratedManufacturingSystem {


    /**
     * Manufacturing Definition Services	制造数据定义服务
     */
    private String mdsJsonView;

    /**
     * Operation Manager Services	制造过程管理服务
     */
    private String omsJsonView;

    public String getMdsJsonView() {
        List<SystemComponent> components = new LinkedList<>();
        Elements trs = HtmlUtil.getTrs("cim-mds.html", "mds");
        //遍历该表格内的所有的<tr> <tr/>
        for (int i = 0; i < trs.size(); ++i) {
            // 获取一个tr
            Element tr = trs.get(i);
            // 获取该行的所有td节点
            Elements tds = tr.select("td");
            // 选择某一个td节点
            SystemComponent component = new SystemComponent();
            Element module = tds.get(0);
            Element function = tds.get(1);
            Element term = tds.get(2);
            Element chineseTerm = tds.get(3);
            Element description = tds.get(4);
            Element remarks = tds.get(5);
            component.setModule(module.text());
            component.setFunction(function.text());
            component.setTerm(term.text());
            component.setChineseTerm(chineseTerm.text());
            component.setDescription(description.text());
            component.setRemarks(remarks.text());
            components.add(component);
        }
        return JSON.toJSONString(components);
    }


    public String getOmsJsonView() {
        List<SystemComponent> components = new LinkedList<>();
        Elements oms_trs = HtmlUtil.getTrs("cim-oms.html", "oms");
        //遍历该表格内的所有的<tr> <tr/>
        for (int i = 0; i < oms_trs.size(); ++i) {
            Element tr = oms_trs.get(i);
            Elements tds = tr.select("td");
            SystemComponent component = new SystemComponent();
            Element function = tds.get(0);
            Element term = tds.get(3);
            Element chineseTerm = tds.get(4);
            Element description = tds.get(5);
            Element remarks = tds.get(6);
            component.setModule("OMS");
            component.setFunction(function.text());
            component.setTerm(term.text());
            component.setChineseTerm(chineseTerm.text());
            component.setDescription(description.text());
            component.setRemarks(remarks.text());
            components.add(component);
        }
        return JSON.toJSONString(components);
    }

}
