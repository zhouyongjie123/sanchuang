package com.example.sanchuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sanchuang.data_model.SensorData;
import com.example.sanchuang.mapper.SensorDataMapper;
import com.example.sanchuang.service.SensorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl extends ServiceImpl<SensorDataMapper, SensorData>
        implements SensorService {

    @Resource
    private SensorDataMapper sensorDataMapper;

    @Override
    public SensorData getCurrentSensorData() {
        return SensorData.getInstance();
    }

    @Override
    public List<SensorData> getRecentSensorData() {
        // 调用mapper layer的接口,访问数据库,获取信息
        long timeMills = System.currentTimeMillis() - 60000;// 获取一分钟之前的毫秒数
        LambdaQueryWrapper<SensorData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(SensorData::getTimeMills, timeMills);
        return sensorDataMapper.selectList(queryWrapper);
    }

    @Override
    public boolean writeData() {
        // 调用mapper layer的接口,向数据库插入传感器数据
        return sensorDataMapper.insert(SensorData.getInstance()) > 0;
    }
}
