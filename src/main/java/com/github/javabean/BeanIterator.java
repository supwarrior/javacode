package com.github.javabean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import static com.github.common.Constant.PREFIX;


/**
 * @author 康盼Java开发工程师
 * @description bean迭代器
 */
public class BeanIterator<T> implements Iterator<T>, BeanDriver {

    /**
     * 类型
     */
    private Class<T> clazz;
    /**
     * 类加载器
     */
    private ClassLoader classLoader;
    /**
     * Enumeration枚举作用和Iterator类似
     */
    private Enumeration<URL> enumeration;
    /**
     * 下一个bean
     */
    private String nextBean;
    /**
     * bean 集合
     */
    private ArrayList<String> beans = new ArrayList<>();
    /**
     * ArrayList 迭代器
     */
    private Iterator<String> iterator;

    public BeanIterator(Class<T> clazz, ClassLoader classLoader) {
        this.clazz = clazz;
        this.classLoader = classLoader;
        try {
            enumeration = classLoader.getResources(PREFIX + clazz.getName());
            while (enumeration.hasMoreElements()) {
                parse(enumeration.nextElement());
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        try {
            if (beans == null || iterator == null || !iterator.hasNext()) {
                return false;
            }
            nextBean = iterator.next();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return true;
    }

    /**
     * initialize设置为true 对进行加载的类进行初始化
     *
     * @return bean
     */
    @Override
    public T next() {
        try {
            Class<?> beanClass = Class.forName(nextBean, false, classLoader);
            if (clazz.isAssignableFrom(beanClass)) {
                T result = clazz.cast(beanClass.newInstance());
                return result;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return null;
    }

    /**
     * 解析 META-INF.services 文件夹的文件
     *
     * @param url
     * @throws Exception
     */
    private void parse(URL url) throws Exception {
        InputStream in = url.openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        String beanName;
        while ((beanName = bufferedReader.readLine()) != null) {
            if (beanName != "") {
                beans.add(beanName);
            }
        }
        iterator = beans.iterator();
    }

}
