package com.github.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 屏幕截图
 *
 * @author 康盼Java开发工程师
 */
public class SnapshotUtil {

    /**
     * 截屏
     *
     * @param fileName   文件名路径
     * @param fileSuffix 文件名后缀
     */
    public static void snapShot(String fileName, String fileSuffix) {
        try {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            // 拷贝屏幕到一个 BufferedImage 对象 screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new
                    Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            // 根据文件前缀变量和文件格式变量，自动生成文件名
            String name = fileName + "." + fileSuffix;
            File file = new File(name);
            // 将screenshot对象写入图像文件
            ImageIO.write(screenshot, fileSuffix, file);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
