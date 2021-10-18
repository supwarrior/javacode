package com.github.common.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    /**
     * 一些重要的会议开启 自动截屏
     * @param args
     */
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Long time = System.currentTimeMillis();
            String date = simpleDateFormat.format(new Date(time));
            date = date.replaceAll("\\:| \\ ","-");
            snapShot("../ideaPng/" + date ,"png");
        },5,60, TimeUnit.SECONDS);
    }
}
