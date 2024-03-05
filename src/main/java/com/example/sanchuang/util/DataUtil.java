package com.example.sanchuang.util;

import com.example.sanchuang.data_model.SensorDataDTO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 提供将传感器数据封装到SensorData的工具类
public class DataUtil {

    // 将数据字符串分割并封装进入Map
    public static Map<String, String> wrapData(String string) {
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
    public static void packageSensorData(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        SensorDataDTO sensorDataDTO = SensorDataDTO.getInstance();
        try {
            Arrays.stream(sensorDataDTO.getClass().getFields()).filter(field -> keySet.contains(field.getName())).forEach(field -> {
                try {
                    field.set(sensorDataDTO, map.get(field.getName()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
