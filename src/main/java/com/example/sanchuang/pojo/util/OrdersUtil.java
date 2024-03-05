package com.example.sanchuang.pojo.util;

import com.example.sanchuang.pojo.Orders;
import com.example.sanchuang.pojo.Orders0;
import com.example.sanchuang.util.DateUtil;

import java.util.Date;

public class OrdersUtil {
    public static Orders parseOrders(Orders0 orders0) {
        Orders orders = new Orders();
        orders.setName(orders0.getName());
        orders.setDate(DateUtil.wrapDate(orders0.getDate(), orders0.getTime()));
        orders.setPhone(orders0.getPhone());
        orders.setNumber(orders0.getNumber());
        return orders;
    }
}
