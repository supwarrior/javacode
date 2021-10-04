package com.github.resource;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.github.common.Constant.JAVA;
import static com.github.common.Constant.JAVA_MAIN_SRC;

/**
 * 跟文件操作有关的
 *
 * @author 康盼Java开发工程师
 */
public class Directory {
    /**
     * @param dir
     * @param regex
     * @return
     */
    public static File[] list(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    /**
     * @param file
     * @param regex
     * @return
     */
    public static FileList get(File file, String regex) {
        FileList list = new FileList();
        for (File item : file.listFiles()) {
            if (item.isDirectory()) {
                list.getDirs().add(item);
                // 因为list是局部变量
                 list.addAll(get(item, regex));
            } else {
                if (item.getName().endsWith(regex)) {
                    list.getFiles().add(item);
                }
            }
        }
        return list;
    }

    /**
     * 获取某包下所有类
     *
     * @param basedir      运行项目的根路径
     * @param packageName  包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassName(String basedir, String packageName, boolean childPackage) {
        String packagePath = basedir + JAVA_MAIN_SRC + packageName.replace(".", "/");
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
        List<String> classNames = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        if (childFiles == null) {
            // 文件下的类为空
            return classNames;
        }
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    classNames.addAll(getClassNameByFile(childFile.getPath(), true));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(JAVA)) {
                    classNames.add(childFilePath);
                }
            }
        }
        return classNames;
    }

}
