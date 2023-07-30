package com.example.database.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_timer")
public class SysTimer {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("status")
    private String status;

    @TableField("name")
    private String name;

    @TableField("method")
    private String method;

    @TableField("cron")
    private String cron;
}
