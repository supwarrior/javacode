package com.github.searcher;

import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.MapSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.param.Operator;
import com.ejlchina.searcher.util.MapUtils;
import com.github.ddd.BaseCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * https://searcher.ejlchina.com/
 *
 */
@RestController
@RequestMapping("/user")
public class SearcherBeanController {

    @Autowired
    private BaseCore baseCore;


    /**
     * 注入 Map 检索器，它检索出来的数据以 Map 对象呈现
     */
    @Autowired
    private MapSearcher mapSearcher;
    /**
     * 注入 Bean 检索器，它检索出来的数据以 泛型 对象呈现
     */
    @Autowired
    private BeanSearcher beanSearcher;

    @PostConstruct
    public void postConstruct() {
        String sql = "insert into user (id, name, age) values (?, ?, ?)";
        Object[] params = {1L, "上扬", 28};
        baseCore.insert(sql, params);
    }


    @GetMapping("/index")
    public SearchResult<Map<String, Object>> index() {

        Map<String, Object> params = MapUtils.builder()
                .page(0, 15)                                        // 该方法会自动使用正确的参数名
                .build();
        SearchResult<Map<String, Object>> result = mapSearcher.search(User.class, params);

        Map<String,Object> params_op =
                MapUtils.builder().field(User::getName,"上扬").op(Operator.Equal).build();

        mapSearcher.search(User.class, params_op);

        return  result;
        // 一行代码，实现一个用户检索接口（MapUtils.flat 只是收集前端的请求参数）
       // return mapSearcher.search(User.class, MapUtils.flat(request.getParameterMap()));
    }

}
