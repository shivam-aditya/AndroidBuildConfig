package com.androidbuildconfig;

import android.support.annotation.NonNull;
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
    private String utmSource;

    public RNAndroidBuildConfigModule(ReactApplicationContext reactContext, Class buildConfigClass, String UtmSource) {
        super(reactContext);
        this.reactContext = reactContext;
        this.buildConfigClass = buildConfigClass;
        this.utmSource = UtmSource;
        Log.d(NAME, "In constructor in RNAndroidBuildConfigModule. buildConfigClass size is: " + buildConfigClass.getDeclaredFields().length);
    }

    @Override
    public String getName() {
        return "RNAndroidBuildConfig";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = getStringObjectMap();
        buildConfigConstants = constants;

        return constants;
    }

    @NonNull
    private Map<String, Object> getStringObjectMap() {
        final Map<String, Object> constants = new HashMap<>();

        Field[] fields = buildConfigClass.getDeclaredFields();
        Log.d(NAME, "BuildConfig size is: " + fields.length);

        for (Field f : fields) {
            try {
                Log.d(NAME, "BuildConfig field name: " + f.getName());
                Log.d(NAME, "BuildConfig field value: " + f.get(null));
                constants.put(f.getName(), f.get(null));
            } catch (IllegalAccessException e) {
                Log.d(NAME, "Could not access BuildConfig field " + f.getName());
            }
        }
        return constants;
    }

    //working
    @ReactMethod
    public void getUtmSource(Promise promise) {
        Log.d(NAME, "UtmSource from getUtmSource in RNAndroidBuildConfig is" + this.utmSource);
        promise.resolve(this.utmSource);
    }

    //non-working
    @ReactMethod
    public void getUtmSourceFromClass(Promise promise) {
        if (this.buildConfigConstants == null || this.buildConfigConstants.isEmpty()) {
            Log.d(NAME, "buildConfigConstants in RNAndroidBuildConfig is null");
            final Map<String, Object> constants = getStringObjectMap();
            buildConfigConstants = constants;
        }

        int size = buildConfigConstants.size();
        Log.d(NAME, "buildConfigConstants size after setting from null in getUtmSource is" + size);
        Log.d(NAME, "buildConfigConstants after setting from null in getUtmSource is" + buildConfigConstants);
        if (size > 0) {
            String utmSource = buildConfigConstants.get("DEFAULT_UTM_SOURCE").toString();
            Log.d(NAME, "UtmSource from getUtmSource in getUtmSource is: " + utmSource);
            promise.resolve(utmSource);
        } else {
            promise.resolve("NO DATA GOT");
        }
    }

    //non-working
    @ReactMethod
    public void getBuildConfigValue(String key, Promise promise) {
        if (this.buildConfigConstants == null || this.buildConfigConstants.isEmpty()) {
            Log.d(NAME, "buildConfigConstants in RNAndroidBuildConfig is null");
            final Map<String, Object> constants = getStringObjectMap();
            buildConfigConstants = constants;
        }

        int size = buildConfigConstants.size();
        Log.d(NAME, "buildConfigConstants size after setting from null in getBuildConfigValue is" + size);
        Log.d(NAME, "buildConfigConstants after setting from null in getBuildConfigValue is" + buildConfigConstants);
        if (size > 0) {
            Log.d(NAME, "getBuildConfigValue key is" + key);
            String value = buildConfigConstants.get(key).toString();
            promise.resolve(value);
        } else {
            promise.resolve("NO DATA GOT2 ");
        }
    }
}