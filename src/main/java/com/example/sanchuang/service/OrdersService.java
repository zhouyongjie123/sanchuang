package com.example.sanchuang.service;

import com.example.sanchuang.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrdersService extends IService<Orders> {
    Orders order(Orders orders);

    Orders getOrders(Long id);

    Orders getOrders(Orders orders);

    List<Orders> listAllOrders();
}
