package com.example.sanchuang.watcher;

import com.example.sanchuang.util.DataUtil;

import java.io.FileInputStream;
import java.nio.file.*;


// singleton instance
public class FileWatcher {
    private static volatile Thread thread = null;

    // 构造器私有化,防止外部new
    private FileWatcher() {
    }

    public static Thread getThread() {
        if (thread == null) {
            synchronized (Thread.class) {
                if (thread == null) {
                    thread = new Thread(() -> {
                        final String baseFilePath = "";// todo (zyj,2024/3/5 10:22) 正确填写文件地址
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
                            // 无限循环，等待事件发生
                            while (!Thread.currentThread().isInterrupted()) {
                                WatchKey key = watchService.take(); // 如果没有事件就绪，这会阻塞
                                for (WatchEvent<?> event : key.pollEvents()) {
                                    Path changedPath = (Path) event.context();
                                    // 检查是否是我们关心的文件发生了变化
                                    if (changedPath.toString().equals(filePath.getFileName().toString())) {
                                        // 读取文件的新内容
                                        System.out.println("File has been modified. Perform some actions here.");
                                        int len;
                                        byte[] buffer = new byte[32];
                                        while ((len = fileInputStream.read(buffer)) != -1) {
                                            // 将数据封装到SensorData实例中
                                            // todo (zyj,2024/3/5 08:33) 保证正确封装
                                            DataUtil.packageSensorData(DataUtil.wrapData(new String(buffer, 0, len)));
                                            //System.out.println(new String(buffer, 0, len));
                                        }
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
                }
            }
        }
        return thread;
    }
}