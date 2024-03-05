package com.example.sanchuang.controller;

import com.example.sanchuang.controller.common.UnitProtocol;
import com.example.sanchuang.pojo.Orders;
import com.example.sanchuang.pojo.Orders0;
import com.example.sanchuang.pojo.util.OrdersUtil;
import com.example.sanchuang.service.OrdersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("/order")
    public UnitProtocol order(@RequestBody Orders0 orders0) {
        Orders orders = OrdersUtil.parseOrders(orders0);
        Orders ordered = getOrders(orders);
        return ordered==null
                ?UnitProtocol.fail("预定失败")
                :UnitProtocol.success(orders,"预订成功");
    }

    private Orders getOrders(Orders orders) {
        Orders ordered = ordersService.order(orders);
        return ordered;
    }

    @GetMapping("/{id}")
    public UnitProtocol getOrders(@PathVariable Long id) {
        Orders orders = ordersService.getOrders(id);
        return orders == null
                ? UnitProtocol.fail("预订不存在")
                : UnitProtocol.success(orders);
    }

    @GetMapping("/query/{name}/{phone}")
    public UnitProtocol queryOrders(@PathVariable String name, @PathVariable String phone) {
        Orders orders = new Orders();
        orders.setName(name);
        orders.setPhone(phone);
        Orders queryOrders = ordersService.getOrders(orders);
        return queryOrders == null
                ? UnitProtocol.fail("没查到订单信息")
                : UnitProtocol.success(orders);
    }

    @GetMapping("/queryAll")
    public UnitProtocol queryAllOrders() {
        List<Orders> orders = ordersService.listAllOrders();
        return orders == null
                ? UnitProtocol.fail("没查到订单信息")
                : UnitProtocol.success(orders);
    }
}
