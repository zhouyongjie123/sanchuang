package com.example.sanchuang.service;


import com.example.sanchuang.data_model.SensorDataDTO;
import org.springframework.stereotype.Service;

@Service
public interface SensorService {
    SensorDataDTO getCurrentSensorData();

    String getRecentSensorData();

    boolean writeData();// 向数据库插入数据
}
