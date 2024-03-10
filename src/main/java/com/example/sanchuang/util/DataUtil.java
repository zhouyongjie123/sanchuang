package com.example.sanchuang.util;

import com.example.sanchuang.data_model.SensorData;
import com.example.sanchuang.data_model.SensorDataDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// 提供将传感器数据封装到SensorData的工具类
public class DataUtil {

    // 将数据字符串分割并封装进入Map
    private static Map<String, String> wrapData(String string) {
        HashMap<String, String> hashMap = new HashMap<>();
        Arrays.stream(string.split("\\s+")).forEach(pairs -> {
            String[] keyValue = pairs.split(":");
            if (keyValue.length == 2) {
                hashMap.put(keyValue[0], keyValue[1]);
            }
        });
        return hashMap;
    }

    // 将Map数据包装进入SensorData
    private static void packageSensorData(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        SensorData sensorData = SensorData.getInstance();
        sensorData.setTimeMills(System.currentTimeMillis());// 设置当前时间戳
        try {
            Arrays.stream(sensorData.getClass().getDeclaredFields()).filter(field -> {
                field.setAccessible(true);
                return keySet.contains(field.getName());
            }).forEach(field -> {
                try {
                    field.set(sensorData, Integer.parseInt(map.get(field.getName())));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            parseSensorDataDTO(sensorData);// 同步DTO数据
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 读取文件最后一行的内容
    private static String readLatestSensorData(String filePath) {
        try {
            List<String> list = Files.readAllLines(Paths.get(filePath));
            return list.get(list.size() - 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void wrapLatestSensorData(String filePath) {
        DataUtil.packageSensorData(DataUtil.wrapData(readLatestSensorData(filePath)));
    }

    public static void parseSensorDataDTO(SensorData sensorData) {
        SensorDataDTO sensorDataDTO = SensorDataDTO.getInstance();
        sensorDataDTO.setHumidity(sensorData.getHumidity());
        sensorDataDTO.setTemperature(sensorData.getTemperature());
        sensorDataDTO.setSoilMoisture(sensorData.getSoilMoisture());
        sensorDataDTO.setLightIntensity(sensorData.getLightIntensity());
    }
}
