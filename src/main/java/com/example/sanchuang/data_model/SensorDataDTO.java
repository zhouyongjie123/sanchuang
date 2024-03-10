package com.example.sanchuang.data_model;

import lombok.Data;

@Data
// 用于封装传感器数据的类
public class SensorDataDTO {
    private Integer humidity;// 湿度
    private Integer temperature;// 温度
    private Integer soilMoisture;// 土壤湿度
    private Integer lightIntensity;// 光照强度
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
