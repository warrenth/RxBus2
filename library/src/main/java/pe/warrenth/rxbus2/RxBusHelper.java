package pe.warrenth.rxbus2;

/**
 * Created by 152317 on 2018-02-23.
 */

public class RxBusHelper {

    public static Class<?> getRegistClass(Object object, Class<?> registClass) {
        Object registObject = null;

        if( ! registClass.getName().equals(object.getClass().getName())) {
            Class<?> currentClass = object.getClass();
            while(currentClass != null) {
                Class<?> parentClass = currentClass.getSuperclass();

                if(parentClass != null) {
                    if (parentClass.getName().equals(registClass.getName())) {
                        registObject = parentClass;
                        break;
                    }
                }
                currentClass = parentClass;
            }
        } else  {
            registObject = object.getClass();
        }

        return (Class<?>) registObject;
    }
}
