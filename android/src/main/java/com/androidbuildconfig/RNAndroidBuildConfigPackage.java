
package com.androidbuildconfig;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;
public class RNAndroidBuildConfigPackage implements ReactPackage {

    private Class buildConfigClass;
    private String utmSource;
    private String NAME = "RNAndroidBuildConfig";
    private Map<String, Object> buildConfigConstants = new HashMap<>();

    public RNAndroidBuildConfigPackage(Class buildConfigClass, String UtmSource) {
        Log.d(NAME, "UtmSource at start in RNAndroidBuildConfigPackage is" + UtmSource);
        this.utmSource = UtmSource;
        Log.d(NAME, "In RNAndroidBuildConfigPackage constructor. buildConfigClass size is: " + buildConfigClass.getDeclaredFields().length);
        this.buildConfigClass = buildConfigClass;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      return Arrays.<NativeModule>asList(new RNAndroidBuildConfigModule(reactContext, this.buildConfigClass, this.utmSource));
    }

    // Deprecated from RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
      return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Collections.emptyList();
    }
}