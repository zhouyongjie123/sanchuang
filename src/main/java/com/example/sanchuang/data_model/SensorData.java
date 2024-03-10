package com.example.sanchuang.data_model;

import lombok.Data;

@Data
public class SensorData {
    // humidity:123 temperature:123 soilMoisture:123 lightIntensity:123
    private Integer humidity;// 湿度
    private Integer temperature;// 温度
    private Integer soilMoisture;// 土壤湿度
    private Integer lightIntensity;// 光照强度
    private static volatile SensorData sensorData = null;
    private Long timeMills;

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
        return sensorData;
    }
}
