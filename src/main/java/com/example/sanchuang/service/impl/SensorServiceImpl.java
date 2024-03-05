package com.example.sanchuang.service.impl;

import com.example.sanchuang.data_model.SensorDataDTO;
import com.example.sanchuang.service.SensorService;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {
    // todo (zyj,2024/3/5 08:29) 写mapper layer
    @Override
    public SensorDataDTO getCurrentSensorData() {
        return SensorDataDTO.getInstance();
    }

    @Override
    public String getRecentSensorData() {
        // todo (zyj,2024/3/5 08:32) 调用mapper layer的接口,访问数据库,获取信息
        return null;
    }

    @Override
    public boolean writeData() {
        // todo (zyj,2024/3/5 08:41) 调用mapper layer的接口,向数据库插入传感器数据
        return true;
    }
}
