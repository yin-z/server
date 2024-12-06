package com.example.admin.interceptors;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Properties;


@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}),
})
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof ParameterHandler) {
            replaceUnderscoreWithEscape(invocation);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void replaceUnderscoreWithEscape(Invocation invocation) throws Throwable {
        ParameterHandler ph = (ParameterHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(ph, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
        Object obj = ph.getParameterObject();
        if (null != obj && obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() instanceof String) {
                    String value = (String) entry.getValue();
                    if (value.contains("_")) {
                        value = value.replace("_", "\\_");
                        map.put(entry.getKey(), value);
                    }
                }
            }
            Field parameterObject = ph.getClass().getDeclaredField("parameterObject");
            parameterObject.setAccessible(true);
            parameterObject.set(ph, map);
        }
    }
}

