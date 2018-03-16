package com.mengka.springboot.component;

import org.immutables.value.Value;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *  转发程序执行器
 *
 * @author huangyy
 * @version cabbage-forward2.0,2018-1-23
 * @since cabbage-forward2.0
 */
@Value.Immutable
public abstract class ForwardExecuter implements Serializable{

    private static final long serialVersionUID = 1L;

    private static ForwardExecuterCacheComponent forwardExecuterCacheComponent = ForwardExecuterCacheComponent.getInstance();

    public abstract String version();

    public abstract String packagePath();

    public abstract String className();

    public abstract String method();

    public abstract Class<?>[] parameterTypes();

    public abstract Object[] args();

    public abstract Integer maxResendCount();

    public abstract Date lastSentTime();

    public Object execute() throws Exception {
        Class myClass = forwardExecuterCacheComponent.getClasscfg(packagePath(),version(),className());
        Object obj = myClass.newInstance();
        Method method1 = myClass.getDeclaredMethod(method(), parameterTypes());
        return method1.invoke(obj, args());
    }

}
