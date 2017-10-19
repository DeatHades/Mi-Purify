package com.coderstory.Purify.utils;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by CoderStory on 2017/10/19 0019.
 */

public class XposedHelper {
    public static void findAndHookMethod(String p1, ClassLoader lpparam, String p2, Object... parameterTypesAndCallback) {
        try {
            XposedHelpers.findAndHookMethod(p1, lpparam, p2, parameterTypesAndCallback);

        } catch (Throwable localString3) {
            XposedBridge.log(localString3);
        }
    }

    public static void findAndHookConstructor(String p1, ClassLoader lpparam, Object... parameterTypesAndCallback) {
        try {
            XposedHelpers.findAndHookConstructor(p1, lpparam, lpparam, parameterTypesAndCallback);

        } catch (Throwable localString3) {
            XposedBridge.log(localString3);
        }
    }
    public static void findAndHookMethod(String p1, String p2, Object[] p3) {
        try {
            XposedHelpers.findAndHookMethod(Class.forName(p1), p2, p3);
        } catch (Throwable localString3) {
            XposedBridge.log(localString3);
        }
    }
    public static Object getDrmResultSUCCESS() {
        try {
            Class<Enum> drmSuccess = (Class<Enum>) Class.forName("miui.drm.DrmManager$DrmResult");
            if (drmSuccess != null) {
                return Enum.valueOf(drmSuccess, "DRM_SUCCESS");
            }
        } catch (Throwable localString4) {
            XposedBridge.log(localString4);

        }
        return null;
    }


}
