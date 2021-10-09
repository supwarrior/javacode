package com.github.mock;

import com.github.common.Constant;
import com.github.common.util.StringUtil;
import com.github.resource.Directory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static com.github.common.Constant.BASEDIR;
import static com.github.common.Constant.DOT;
import static com.github.common.Constant.JAVA_TEST_SRC;
import static com.github.common.Constant.OS;
import static com.github.common.Constant.TEST_CLASS_SUFFIX;

/**
 * @author 康盼Java开发工程师
 */
public class UnitTestGeneratorUtil {


    public static void generatorUnitTest(String testPackageName) throws Exception {
        List<String> javaFiles = Directory.getClassName(BASEDIR, testPackageName, true);
        List<Mocker> mockers = getMocker(javaFiles, testPackageName);
        for (Mocker mocker : mockers) {
            MockerConfig.generatorUnitTest(mocker.getFile(), mocker);
        }
    }


    private static List<Mocker> getMocker(List<String> javaFiles, String packageName) {
        List<Mocker> mockers = new ArrayList<>();
        for (String javaNamePath : javaFiles) {
            if (javaNamePath.endsWith("$1")) {
                continue;
            }
            Mocker mocker = new Mocker();
            String osName = System.getProperty(OS);
            String className;
            if (StringUtils.isNoneBlank(osName) && osName.contains(Constant.WINDOWS)) {
                className = javaNamePath.substring(javaNamePath.lastIndexOf("\\") + 1, javaNamePath.lastIndexOf("."));
            } else {
                className = javaNamePath.substring(javaNamePath.lastIndexOf("/") + 1, javaNamePath.lastIndexOf("."));
            }

            // 生成测试类文件路径   被测试类的绝对路径
            String testJavaName = BASEDIR + JAVA_TEST_SRC + packageName.replace(".", "/") + "/" + className + TEST_CLASS_SUFFIX + ".java";
            File testFile = new File(testJavaName);

            mocker.setPackageName(packageName);
            mocker.setAuthor("k");
            mocker.setDate("2022");
            mocker.setHeadDesc("com.github");
            mocker.setClassName(className);
            mocker.setLowerClassName(StringUtil.strConvertLowerCamel(className));
            mocker.setTestClassName(className + TEST_CLASS_SUFFIX);
            mocker.setFile(testFile);
            getMockField(packageName + DOT + className, mocker);
            getMockMethod(packageName + DOT + className, mocker);
            mockers.add(mocker);

        }
        return mockers;
    }

    private static void getMockMethod(String className, Mocker mocker) {
        List<MockerMethod> list = new ArrayList<>();
        try {
            Class<?> clazz = Class.forName(className);
            // public protected private
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                int mods = method.getModifiers();
                if (!Modifier.isStatic(mods)
                        && !Modifier.isAbstract(mods)
                        && !Modifier.isProtected(mods)
                        && !Modifier.isPrivate(mods)) {
                    MockerMethod mockerMethod = new MockerMethod();
                    mockerMethod.setMethodName(method.getName());
                    list.add(mockerMethod);
                }

            }
            mocker.setMockerMethods(list);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }

    private static void getMockField(String className, Mocker mocker) {
        try {
            Class<?> clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();
            List<MockerField> list = new ArrayList<>();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                    if (field.isAnnotationPresent(Autowired.class)) {
                        MockerField mockerField = new MockerField();
                        mockerField.setFieldName(field.getName());
                        mockerField.setTypeName(field.getType().getSimpleName());
                        mockerField.setTypeFullName(field.getType().getName());
                        list.add(mockerField);
                    }
                }
            }
            mocker.setMockerFields(list);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
