package com.example.sanchuang.service;


import com.example.sanchuang.data_model.SensorData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
    SensorData getCurrentSensorData();

    List<SensorData> getRecentSensorData();

    boolean writeData();// 向数据库插入数据
}
