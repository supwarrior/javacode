package com.github.ddd.controller.product;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.ddd.domainObject.ProductRequestDO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productRequestControllerTest")
public class ProductRequestControllerTest {

    private RestTemplate restTemplate;

    public ProductRequestControllerTest(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.restTemplate.setMessageConverters(getJsonMessageConverters());
    }

    @GetMapping("/objectLockTest")
    public void objectLockTest() {
        String url = "http://localhost:8028/productRequest/objectLock/req";
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                ParameterizedTypeReference<List<ProductRequestDO>> ptr =
                        new ParameterizedTypeReference<List<ProductRequestDO>>() {};
                ResponseEntity<List<ProductRequestDO>> list = restTemplate
                        .exchange(url, HttpMethod.GET, null, ptr);
            }).start();
        }
    }

    @GetMapping("/updateTest")
    public void updateTest() {
        String url = "http://localhost:8028/productRequest/update/req";
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
              restTemplate.getForObject(url,String.class);
            }).start();
        }
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new FastJsonHttpMessageConverter());
        return converters;
    }

}
