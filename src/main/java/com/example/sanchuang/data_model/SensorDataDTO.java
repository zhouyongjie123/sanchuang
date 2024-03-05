package com.example.sanchuang.data_model;

import lombok.Data;

@Data
// 用于封装传感器数据的类
public class SensorDataDTO {
    // todo (zyj,2024/3/5 08:08) 写传感器数据的封装成员
    private static volatile SensorDataDTO sensorDataDTO = null;

    private SensorDataDTO() {
    }

    public static SensorDataDTO getInstance() {
        if (sensorDataDTO == null) {
            synchronized (SensorDataDTO.class) {
                if (sensorDataDTO == null) {
                    sensorDataDTO = new SensorDataDTO();
                }
            }
        }
        return sensorDataDTO;
    }
}
