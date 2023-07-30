package com.example.schedule.util;

import com.example.database.po.SysTimer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduledUtil {

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Resource
    private ScheduledExecutionMethod method;
    private Map<String, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

    public void enable(SysTimer sysTimer){
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(
                () -> method.executeWork(sysTimer.getMethod()),
                new CronTrigger(sysTimer.getCron())
        );
        scheduledFutureMap.put(sysTimer.getId(), scheduledFuture);
    }

    public void disable(String id){
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(id);
        scheduledFuture.cancel(true);
    }
}
