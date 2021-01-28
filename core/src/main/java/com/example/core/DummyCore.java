package com.example.core;

import android.util.Log;

import com.example.face.DummyFace;

public class DummyCore {
    public static String sumThings(){
        if (isPresent("com.example.face.DummyFace")) {
            return  DummyFace.dummyFunc();
        }

        return "No found";
    }

    public static boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Throwable ex) {
            Log.e("DUMMY_CORE", ex.toString());
            return false;
        }
    }
}
