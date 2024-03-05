package com.example.sanchuang.data_model;

import lombok.Data;

@Data
public class SensorData {
    // todo (zyj,2024/3/5 08:48) 写传感器数据的封装成员
    private static volatile SensorData sensorData = null;

    private static Long timestamp;
    private SensorData() {
    }

    public static SensorData getInstance() {
        if (sensorData == null) {
            synchronized (SensorData.class) {
                if (sensorData == null) {
                    sensorData = new SensorData();
                }
            }
        }
        timestamp = System.currentTimeMillis();
        return sensorData;
    }
}
