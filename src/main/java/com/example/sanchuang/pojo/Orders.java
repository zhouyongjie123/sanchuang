package com.example.sanchuang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

/**
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    private Long id;

    private String name;

    private Integer number;

    private Date date;

    private String phone;

    private static final long serialVersionUID = 1L;
}