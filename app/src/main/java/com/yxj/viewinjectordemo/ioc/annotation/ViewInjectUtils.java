package com.yxj.viewinjectordemo.ioc.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yxj on 16/12/24.
 */
public class ViewInjectUtils {

    public static void inject(Activity activity){
        injectContentView(activity);
        injectView(activity);
    }

    private static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            InjectView injectView = field.getAnnotation(InjectView.class);
            if(injectView != null){
                int viewId = injectView.value();
                try {
//                    View view = activity.findViewById(viewId);
//                    field.setAccessible(true);
//                    field.set(activity, view);

                    Method method = clazz.getMethod("findViewById",int.class);
                    field.setAccessible(true);
                    field.set(activity,method.invoke(activity, viewId));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if(contentView!=null){
            int layoutId = contentView.value();
            Method setViewMethod = null;
            try {
                setViewMethod = clazz.getMethod("setContentView", int.class);
                setViewMethod.invoke(activity,layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
