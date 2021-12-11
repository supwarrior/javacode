package com.github.searcher;

import com.ejlchina.searcher.bean.BeanAware;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.ParamAware;
import com.ejlchina.searcher.bean.SearchBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Data
@Entity(name = "user")
@SearchBean(tables = "user")
public class User implements BeanAware, ParamAware {             // 默认映射到 user 表

    @Id
    @DbField("id")
    private Long id;            // 默认映射到 id 字段
    @Column(name = "name")
    @DbField("name")
    private String name;        // 默认映射到 name 字段
    @Column(name = "age")
    @DbField("age")
    private int age;            // 默认映射到 age 字段

    // Getter and Setter ...


    @Override
    public void afterAssembly() {
        System.out.println("装配之后触发的逻辑" + id);
    }

    @Override
    public void afterAssembly(Map<String, Object> paraMap) {
        // 该方法可接收到当前的检索参数
        System.out.println(paraMap);
    }
}
