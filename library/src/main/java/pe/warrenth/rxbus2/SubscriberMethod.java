package pe.warrenth.rxbus2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by warrenth on 2018-01-23.
 */

public class SubscriberMethod {
    public Object subscriber;
    public Class<?> paramType;
    public Method method;
    public ThreadMode threadMode;
    public String eventTag;
    public String uniqueClassName;

    public SubscriberMethod(Object subscriber, Class<?> paramType, Method method, ThreadMode threadMode, String eventTag, String uniqueClassName) {
        this.subscriber = subscriber;
        this.paramType = paramType;
        this.method = method;
        this.threadMode = threadMode;
        this.eventTag = eventTag;
        this.uniqueClassName = uniqueClassName;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public Class<?> getParamType() {
        return paramType;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public String getEventTag() {
        return eventTag;
    }

    public String getUniqueClassName() {
        return uniqueClassName;
    }

    public void invoke(Object o) {
        try {
            Class[] parameterType = method.getParameterTypes();
            if(parameterType != null && parameterType.length == 1){
                method.invoke(subscriber, o);
            }else if(parameterType == null || parameterType.length == 0){
                method.invoke(subscriber);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
