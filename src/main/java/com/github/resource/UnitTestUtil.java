package com.github.resource;

import com.github.constant.Constant;
import com.github.dataInfo.JavaClassInfo;
import com.github.dataInfo.dto.JavaClassDTO;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class UnitTestUtil {

    /**
     * 生成测试类的对应类名 后缀
     */
    public static final String TEST_CLASS_SUFFIX = "Test";
    /**
     * 项目文件路径
     */
    public static final String JAVA_MAIN_SRC = "/src/main/java/";

    /**
     * 当前系统
     */
    public static final String WINDOWS = "Windows";

    /**
     * 测试类路径
     */
    public static final String JAVA_TEST_SRC = "/src/test/java/";

    /**
     * 测试类后缀名
     */
    public static final String JAVA = ".java";

    /**
     * 配置文件路径
     */
    public static final String CONFIG_PATH = "/src/main/resources/test/template/";

    /**
     * 获取类库
     * 预加载类信息
     */
    public static JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder();

    /**
     * 日期
     */
    public static final String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    /**
     * 作者
     */
    public static final String author = "kwx5319385";

    /**
     * 获取某包下所有类
     *
     * @param basedir      运行项目的根路径
     * @param packageName  包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassName(String basedir, String packageName, boolean childPackage) {
        String packagePath = basedir + Constant.JAVA_MAIN_SRC + packageName.replace(".", "/");
        return getClassNameByFile(packagePath, childPackage);
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath     文件路径 - 文件绝对路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassNameByFile(String filePath, boolean childPackage) {
        // 从项目文件获取某包下所有类
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        if (childFiles == null) {
            // 文件下的类为空
            return myClassName;
        }
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), true));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".java")) {
                    myClassName.add(childFilePath);
                }
            }
        }
        return myClassName;
    }

    public static void generatorUT(String testPackageName) {
        // 换行，空格去除
        testPackageName = testPackageName.replaceAll("\\r|\\n", "").trim();
        String basedir = System.getProperty("user.dir");
        List<String> testJavaList = getClassName(basedir, testPackageName, true);
        for (String absolutePath : testJavaList) {
            // 跳过内部类的生成
            if (absolutePath.endsWith("$1")) {
                continue;
            }
            JavaClassInfo javaClassInfo = new JavaClassInfo();
            javaClassInfo.setPackageName(testPackageName);
            javaClassInfo.setAbsolutePath(absolutePath);
            javaClassInfo.setTypeName(getTypeName(absolutePath));
            javaClassInfo.setFullyTypeName(javaClassInfo.getPackageName() + "." + javaClassInfo.getTypeName());
            javaClassInfo.setTestAbsolutePath(getTestAbsolutePath(basedir, javaClassInfo.getPackageName(), javaClassInfo.getTypeName()));

            try {
                Configuration cfg = getConfiguration(basedir);
                File testFile = new File(javaClassInfo.getTestAbsolutePath());
                // 文件存在追加方法
                if (testFile.exists()) {


                } else {
                   // 文件不存在生成
                   if(!testFile.getParentFile().exists() && !testFile.getParentFile().mkdir()) {
                        return;
                   }
                }
                String fullyTypeName = javaClassInfo.getFullyTypeName();
                JavaClass javaClass = javaProjectBuilder.getClassByName(fullyTypeName);
                if(!skip(fullyTypeName,javaClass)) {
                    //模板类信息
                    JavaClassDTO javaClassDTO = new JavaClassDTO();
                    javaClassDTO.setDate(DATE);
                    javaClassDTO.setAuthor(author);
                    javaClassDTO.setModelNameUpperCamel(javaClassInfo.getTypeName());
                    javaClassDTO.setModelNameLowerCamel(strConvertLowerCamel(javaClassInfo.getTypeName()));
                    javaClassDTO.setModelNameUpperCamelTestClass(javaClassInfo.getTypeName() + TEST_CLASS_SUFFIX);
                    javaClassDTO.setModelNameLowerCamelTestClass(strConvertLowerCamel(javaClassInfo.getTypeName() + TEST_CLASS_SUFFIX));
                }




            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }


    }

    private static String getTypeName(String absolutePath) {
        String osName = System.getProperty("os.name");
        String split = "/";
        if (osName.contains(WINDOWS)) {
            split = "\\";
        }
        return absolutePath.substring(absolutePath.lastIndexOf(split) + 1, absolutePath.lastIndexOf("."));
    }

    private static String getTestAbsolutePath(String basedir, String packageName, String typeName) {
        return basedir + JAVA_TEST_SRC + packageName.replace(".", "/") + "/" + typeName + TEST_CLASS_SUFFIX + JAVA;
    }

    private static Configuration getConfiguration(String basedir) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        File file = new File(basedir + CONFIG_PATH);
        cfg.setDirectoryForTemplateLoading(file);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    /**
     * 是否跳过类的测试生成
     *
     * @param fullyTypeName 类的全限定名
     * @param javaClass     类信息
     * @return 返回是否跳过类的测试生成，true-跳过
     */
    private static boolean skip(String fullyTypeName, JavaClass javaClass) {
        // 未查询到该类，请确保项目包中有该类，类名
        if (javaClass == null) {
            return true;
        }
        // 跳过接口
        if (javaClass.isInterface()) {
            return true;
        }
        if (javaClass.isEnum()) {
            return true;
        }
        // 跳过抽象类
        if (javaClass.isAbstract()) {
            return true;
        }
        // 跳过私有类
        if (javaClass.isPrivate()) {
            return true;
        }
        return false;
    }

    /**
     * 首字母转小写
     *
     * @param name 字符串值，必须字母开头
     * @return 首字母转小写
     */
    public static String strConvertLowerCamel(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toLowerCase();
        return at + name.substring(1);
    }

}
