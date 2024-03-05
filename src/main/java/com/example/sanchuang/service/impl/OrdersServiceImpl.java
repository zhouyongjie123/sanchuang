package com.example.sanchuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sanchuang.pojo.Orders;
import com.example.sanchuang.service.OrdersService;
import com.example.sanchuang.mapper.OrdersMapper;
import com.example.sanchuang.util.CamelCaseUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public Orders order(Orders orders) {
        return ordersMapper.insert(orders) > 0
                ? orders : null;
    }

    @Override
    public Orders getOrders(Long id) {
        return ordersMapper.selectById(id);
    }

    @Override
    public Orders getOrders(Orders orders) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        Arrays.stream(orders.getClass().getDeclaredFields()).filter(field -> {
            field.setAccessible(true);
            try {
                return field.get(orders) != null;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).forEach(field -> {
            try {
                queryWrapper.eq(CamelCaseUtil.parseCamelToUnderline(field.getName()), field.get(orders).toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return ordersMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Orders> listAllOrders() {
        return ordersMapper.selectList(null);
    }
}




