package com.example.sanchuang.controller;

import com.example.sanchuang.controller.common.UnitProtocol;
import com.example.sanchuang.data_model.SensorData;
import com.example.sanchuang.data_model.SensorDataDTO;
import com.example.sanchuang.service.SensorService;
import com.example.sanchuang.util.DataUtil;
import com.example.sanchuang.watcher.FileWatcher;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sensors")
// 处理传感器类型的请求
public class SensorController {
    @Resource
    private SensorService sensorService;

    // 开启文件监听器
    @GetMapping("/start")
    public UnitProtocol startWatcher() {
        if (threadIsActive()) {
            return UnitProtocol.fail("监听器已经开启");
        }
        FileWatcher.getInstance().start();
        return UnitProtocol.success("开启成功");
    }

    // 关闭文件监听器
    @GetMapping("/stop")
    public UnitProtocol stopWatcher() {
        if (!threadIsActive()) {
            return UnitProtocol.fail("监听器未开启");
        }
        // todo (zyj,2024/3/10 15:58) 有小bug,监视器内线程有阻塞等待,不能直接stop
        FileWatcher.getInstance().stop();
        return UnitProtocol.success("关闭成功");
    }

    // 读取传感器的实时数据
    @GetMapping("/read")
    public UnitProtocol getCurrentData() {
        if (!threadIsActive()) {
            return UnitProtocol.fail("监听器未开启");
        }
        DataUtil.parseSensorDataDTO(sensorService.getCurrentSensorData());
        return UnitProtocol.success(SensorDataDTO.getInstance());
    }

    // 读取传感器近期的数据
    @GetMapping("/read/recent")
    public UnitProtocol getRecentData() {
        if (!threadIsActive()) {
            return UnitProtocol.fail("监听器未开启");
        }
        return UnitProtocol.success(sensorService.getRecentSensorData());
    }

    @GetMapping("/write")
    public UnitProtocol writeData() {
        if (!threadIsActive()) {
            return UnitProtocol.fail("监听器未开启");
        }

//        SensorData sensorData = SensorData.getInstance();
//        sensorData.setHumidity(1);
//        sensorData.setTemperature(2);
//        sensorData.setSoilMoisture(3);
//        sensorData.setLightIntensity(4);
//        sensorData.setTimeMills(System.currentTimeMillis());

        return sensorService.writeData() ? UnitProtocol.success("写入数据库成功") : UnitProtocol.fail("写入数据库失败");
    }

    private boolean threadIsActive() {
        return FileWatcher.getFlag();
    }
}
