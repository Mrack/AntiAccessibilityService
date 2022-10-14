package cn.mrack.server;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class AutoService extends AccessibilityService {
    private static final String TAG = "AutoService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (null == event || null == event.getSource()) {
            return;
        }
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && event.getPackageName().equals("cn.mrack.myapplication")) {
            final CharSequence className = event.getClassName();
            Log.d(TAG, "currentWindows: " + className);
            List<AccessibilityNodeInfo> nodeInfoList = event.getSource().findAccessibilityNodeInfosByText("抢红包");
            Log.d(TAG, "onAccessibilityEvent: " + nodeInfoList);
            for (AccessibilityNodeInfo node : nodeInfoList) {

                if (node.isClickable() && node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }

    }

    @Override
    public void onInterrupt() {

    }
}
