package cn.mrack.myapplication;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.content.Context.ACCESSIBILITY_SERVICE;
import static android.os.Build.VERSION.SDK_INT;

/**
 * 关闭无障碍服务 防止模拟点击
 * @author Mrack
 * @date 2022/10/14
 */
public class AntiAccessibilityService {
    private static final String TAG = "AntiAccessibility";
    final private Context mContext;
    final private AccessibilityManager accessibilityManager;
    private Handler.Callback originCallback;

    public AntiAccessibilityService(Context mContext) {
        hiddenApi();
        this.mContext = mContext;
        this.accessibilityManager = (AccessibilityManager) this.mContext.getSystemService(ACCESSIBILITY_SERVICE);
    }

    public void anti() {
        try {
            enable(false);
            callback((Handler.Callback) message -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            enable(true);
            callback(this.originCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callback(Handler.Callback callback) throws Exception {
        Field mHandler = AccessibilityManager.class.getDeclaredField("mHandler");
        Field mCallback = Handler.class.getDeclaredField("mCallback");
        mHandler.setAccessible(true);
        mCallback.setAccessible(true);
        Object handler = mHandler.get(accessibilityManager);
        originCallback = (Handler.Callback) mCallback.get(handler);
        mCallback.set(handler, callback);
        mHandler.setAccessible(false);
        mCallback.setAccessible(false);
    }

    private void enable(boolean b) throws Exception {
        Field mIsEnabled = AccessibilityManager.class.getDeclaredField("mIsEnabled");
        mIsEnabled.setAccessible(true);
        mIsEnabled.set(accessibilityManager, b);
        mIsEnabled.setAccessible(false);
    }


    private void hiddenApi() {
        if (SDK_INT >= Build.VERSION_CODES.P) {
            try {
                Method forName = Class.class.getDeclaredMethod("forName", String.class);
                Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
                Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
                Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
                Object sVmRuntime = getRuntime.invoke(null);
            } catch (Throwable e) {
                Log.e(TAG, "reflect bootstrap failed:", e);
            }
        }
    }

}
