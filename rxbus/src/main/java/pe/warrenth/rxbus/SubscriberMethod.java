package pe.warrenth.rxbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 152317 on 2018-01-23.
 */

public class SubscriberMethod {
    public Object subscriber;
    public Class<?> paramType;
    public Method method;
    public ThreadMode threadMode;
    public String eventTag;

    public SubscriberMethod(Object subscriber, Class<?> paramType, Method method, ThreadMode threadMode, String eventTag) {
        this.subscriber = subscriber;
        this.paramType = paramType;
        this.method = method;
        this.threadMode = threadMode;
        this.eventTag = eventTag;
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
