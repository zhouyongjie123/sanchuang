package com.example.sanchuang.watcher;

import com.example.sanchuang.util.DataUtil;

import java.io.FileInputStream;
import java.nio.file.*;


// singleton instance
public class FileWatcher {
    private static volatile FileWatcher fileWatcher = null;
    private static boolean flag;
    private final Thread thread = new Thread(() -> {
        // todo (zyj,2024/3/5 10:22) 正确填写文件地址
        final String baseFilePath = "/Users/zyj/Desktop/qqDownload/untitled.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(baseFilePath);
            // 定义要监控的文件路径
            Path filePath = Paths.get(baseFilePath);
            // 获取文件所在的目录
            Path directory = filePath.getParent();
            // 创建WatchService来监控目录
            WatchService watchService = FileSystems.getDefault().newWatchService();
            // 注册监听事件，这里我们关心ENTRY_MODIFY事件，即文件被修改
            directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("文件监听器初始化完成");
            // 无限循环，等待事件发生
            while (FileWatcher.getFlag()) {
                WatchKey key = watchService.take(); // 如果没有事件就绪，这会阻塞
                for (WatchEvent<?> event : key.pollEvents()) {
                    Path changedPath = (Path) event.context();
                    // 检查是否是我们关心的文件发生了变化
                    if (changedPath.toString().equals(filePath.getFileName().toString())) {
                        // 读取文件的新内容
                        System.out.println("File has been modified. Perform some actions here.");
                        DataUtil.wrapLatestSensorData(baseFilePath);
                    }
                }
                // 重置key以接收后续事件
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    });

    // 构造器私有化,防止外部new
    private FileWatcher() {
    }

    public static FileWatcher getInstance() {
        if (fileWatcher == null) {
            synchronized (FileWatcher.class) {
                if (fileWatcher == null) {
                    fileWatcher = new FileWatcher();
                }
            }
        }
        return fileWatcher;
    }

    public void start() {
        flag = true;
        thread.start();
    }

    public void stop() {
        flag = false;
    }

    public static boolean getFlag() {
        return flag;
    }
}