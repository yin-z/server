package com.example.schedule;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database.mapper.SysTimerMapper;
import com.example.database.po.SysTimer;
import com.example.schedule.util.ScheduledUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ScheduledServiceImpl extends ServiceImpl<SysTimerMapper, SysTimer> implements ScheduledService {

   @Resource
   private ScheduledUtil schedule;

    @Override
    public void start() {
        LambdaQueryWrapper<SysTimer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysTimer::getStatus, "0");
        List<SysTimer> list = this.list(wrapper);
        System.out.println(list);
        for (SysTimer sysTimer : list) {
            schedule.enable(sysTimer);
        }
    }

    @Override
    public void addOrEdit(SysTimer sysTimer) {
        this.saveOrUpdate(sysTimer);
        if (sysTimer.getStatus()=="0"){
            schedule.enable(sysTimer);
        }
    }

    @Override
    public void disabled(String id) {
        schedule.disable(id);
    }

    @Override
    public void enable(String id) {
        SysTimer byId = this.getById(id);
        schedule.enable(byId);
    }

}
