package com.example.database.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test1")
public class Test1 {

    @TableId
    private String name;

    private String sex;
}
