package com.example.sanchuang.controller.common;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class UnitProtocol {
    private boolean flag;
    private String message;
    private Object data;

    private UnitProtocol(String message) {
        this.flag = false;// 出错默认false
        this.data = null;
        this.message = message;
    }

    private UnitProtocol(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
        this.message = ""; // 设置默认的message为空字符串
    }

    private UnitProtocol(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public UnitProtocol() {
    }

    public static UnitProtocol success(Object data) {
        return new UnitProtocol(true, data);
    }

    public static UnitProtocol fail(String message) {
        return new UnitProtocol(message);
    }

    public static UnitProtocol success(Object data, String message) {
        return new UnitProtocol(true, message, data);
    }

    public static UnitProtocol success(String message) {
        return new UnitProtocol(true, message);
    }
}
