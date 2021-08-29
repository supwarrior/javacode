package test;

import com.github.collections.Entry;
import com.github.collections.HashMap;
import com.github.javabean.BeanDriverManager;
import com.github.javabean.Beans;
import com.github.service.IUserService;
import com.github.model.User;
import com.github.service.impl.UserServiceImpl;
import com.github.service.impl.VipUserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;


/**
 * @author 康盼Java开发工程师
 */
public class AllTest {


    @Test
    public void getByTypeForUser() {
        User user = (User) Beans.getByType(User.class);
        System.out.println(user);
    }

    @Test
    public void getByNameForUser() {
        User user = (User) Beans.getByName("user");
        System.out.println(user);
    }

    @Test
    public void getByTypeForUserServiceImpl() {
        IUserService userService = (UserServiceImpl) Beans.getByType(UserServiceImpl.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void getByNameForUserServiceImpl() {
        IUserService userService = (UserServiceImpl) Beans.getByName("userServiceImpl");
        System.out.println(userService.getUser());
    }


    @Test
    public void getByTypeForVipUserServiceImpl() {
        IUserService userService = (VipUserServiceImpl) Beans.getByType(VipUserServiceImpl.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void getByNameForVipUserServiceImpl() {
        IUserService userService = (VipUserServiceImpl) Beans.getByName("vipUserServiceImpl");
        System.out.println(userService.getUser());
    }

    @Test
    public void getByTypeForIUserService() {
        IUserService userService = (IUserService) Beans.getByType(IUserService.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void classForNameTest() throws Exception {
        Class.forName("com.github.javabean.BeanDriverManager");
        BeanDriverManager manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Beans.getByType(UserServiceImpl.class);
        Beans.getByType(VipUserServiceImpl.class);
        Beans.getByType(User.class);
        manager.printAllBean();
        manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Assert.assertNull(manager);
    }

    @Test
    public void hashMapTest() {
        HashMap hashMap = new HashMap(16,100);
        hashMap.put("8","a");
        hashMap.put("9","b");
        hashMap.put("3","c");
        Iterator iterator = hashMap.iterator();
        while (iterator.hasNext()) {
            Entry<String,String> entry = (Entry<String,String>) iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Assert.assertEquals(hashMap.get("3"),"c");
        Assert.assertTrue(hashMap.containsKey("3"));
        hashMap.clear();
        Assert.assertTrue(hashMap.isEmpty());
        Assert.assertEquals(hashMap.size(),0);
        Assert.assertNull(hashMap.get("3"));
    }

}
