package com.androidbuildconfig;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RNAndroidBuildConfigModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private Class buildConfigClass;
    private String NAME = "RNAndroidBuildConfig";
    private Map<String, Object> buildConfigConstants = new HashMap<>();

    public RNAndroidBuildConfigModule(ReactApplicationContext reactContext, Class buildConfigClass) {
        super(reactContext);
        this.reactContext = reactContext;
        this.buildConfigClass = buildConfigClass;
    }

    @Override
    public String getName() {
        return "RNAndroidBuildConfig";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        Field[] fields = buildConfigClass.getDeclaredFields();
        Log.d(NAME, "BuildConfig fields are: " + fields.toString());

        for (Field f : fields) {
            try {
                Log.d(NAME, "BuildConfig field name: " + f.getName());
                Log.d(NAME, "BuildConfig field value: " + f.get(null));
                constants.put(f.getName(), f.get(null));
            } catch (IllegalAccessException e) {
                Log.d(NAME, "Could not access BuildConfig field " + f.getName());
            }
        }

        this.buildConfigConstants = constants;

        return constants;
    }

    @ReactMethod
    public void getUtmSource(Promise promise) {
        String utmSource = this.buildConfigConstants.get("DEFAULT_UTM_SOURCE").toString();
        Log.d(NAME, "UtmSource from getUtmSource in RNAndroidBuildConfig is" + utmSource);
        promise.resolve(utmSource);
    }

    @ReactMethod
    public void getBuildConfigValue(String key, Promise promise) {
        Log.d(NAME, "getBuildConfigValue key is" + key);
        String value = this.buildConfigConstants.get(key).toString();
        promise.resolve(value);
    }
}