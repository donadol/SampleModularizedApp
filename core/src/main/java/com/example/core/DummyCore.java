package com.example.core;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

public class DummyCore {
    public static String sumThings(Context context){
        if (isPresent("com.example.face.DummyFace")) {
            // This block will never execute when the dependency is not present
            // There is therefore no more risk of code throwing NoClassDefFoundException.
            try {
                Class face = Class.forName("com.example.face.DummyFace");
                Log.i("DUMMY_CORE", "encontrado");
                String result = invokeMethodWithArgs(face, "dummyFunc", new Class<?>[0], null, context);
                // invokeMethodWithArgs(face, "installActivity", Context.class, null, context);
                return result;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return "No encontrado";
    }

    public static boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Throwable ex) {
            Log.e("DUMMY_CORE", ex.toString());
            // Class or one of its dependencies is not present...
            return false;
        }
    }

    private static <T> T invokeMethodWithArgs(Class<?> clazz, String methodName, Class<?> argTypes, Object target, Object... params) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, argTypes);
            Log.i("DUMMY_CORE", method.getName());
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return (T) method.invoke(target, params);
        } catch (Exception e) {
            return null;
        }
    }
}
