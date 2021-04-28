package com.liu.test;

import android.app.Activity;
import android.util.ArrayMap;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

public class InjectActivity {
    
    
    ///啊啊啊
    private static final ArrayMap<String, Object> injectMap = new ArrayMap<String, Object>();

    public static void inject(Activity activity) {
        String className = activity.getClass().getName();
        try {
            Object inject = injectMap.get(className);

            if (inject == null) {
                //加载build生成的类
                Class<?> aClass = Class.forName(className + "$$InjectActivity");
                inject = aClass.newInstance();
                injectMap.put(className, inject);
            }
            //反射执行方法
            Method m1 = inject.getClass().getDeclaredMethod("inject", activity.getClass());
            m1.invoke(inject, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
