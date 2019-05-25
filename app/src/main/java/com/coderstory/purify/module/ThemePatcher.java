package com.coderstory.purify.module;

import android.content.Context;

import com.coderstory.purify.plugins.IModule;
import com.coderstory.purify.utils.XposedHelper;

import java.io.File;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.coderstory.purify.config.Misc.isEnable;

public class ThemePatcher extends XposedHelper implements IModule {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {

        if (!isEnable()) {
            return;
        }
        if (lpparam.packageName.equals("miui.drm")) {
            findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", Context.class, File.class, File.class, XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
            findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", Context.class, String.class, File.class, XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
            hookAllMethods("miui.drm.DrmManager", lpparam.classLoader, "getMorePreciseDrmResult", XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
            findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isPermanentRights", File.class, XC_MethodReplacement.returnConstant(true));
        }

        if (lpparam.packageName.equals("com.android.thememanager")) {
            // older
            findAndHookMethod("com.android.thememanager.f.q", lpparam.classLoader, "isProductBought", XC_MethodReplacement.returnConstant(true));
            findAndHookMethod("com.android.thememanager.f.t", lpparam.classLoader, "isProductBought", XC_MethodReplacement.returnConstant(true));
            // 1.5.9.0
            findAndHookMethod("com.android.thememanager.g.t", lpparam.classLoader, "isProductBought", XC_MethodReplacement.returnConstant(true));
            findAndHookMethod("com.android.thememanager.g.q", lpparam.classLoader, "isProductBought", XC_MethodReplacement.returnConstant(true));

            findAndHookMethod("com.android.thememanager.f.q", lpparam.classLoader, "isAuthorizedResource", XC_MethodReplacement.returnConstant(true));
            //1.5.9.0
            findAndHookMethod("com.android.thememanager.g.q", lpparam.classLoader, "isAuthorizedResource", XC_MethodReplacement.returnConstant(true));

            // return this.eV.getTrialTime() > 0;
            findAndHookMethod("com.android.thememanager.util.ce", lpparam.classLoader, "u", XC_MethodReplacement.returnConstant(true));
            // 1.5.9.0
            findAndHookMethod("com.android.thememanager.util.ch", lpparam.classLoader, "w", XC_MethodReplacement.returnConstant(true));

            findAndHookMethod("com.android.thememanager.b.b.c", lpparam.classLoader, "a", XposedHelpers.findClass("com.android.thememanager.f.q", lpparam.classLoader), XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
            //1.5.9.0
            findAndHookMethod("com.android.thememanager.b.b.d", lpparam.classLoader, "a", XposedHelpers.findClass("com.android.thememanager.f.q", lpparam.classLoader), XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));

        }
    }

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) {
        findAndHookMethod("miui.drm.DrmManager", null, "isLegal", Context.class, File.class, File.class, XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
        findAndHookMethod("miui.drm.DrmManager", null, "isLegal", Context.class, String.class, File.class, XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
        hookAllMethods("miui.drm.DrmManager", null, "getMorePreciseDrmResult", XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
        findAndHookMethod("miui.drm.DrmManager", null, "isPermanentRights", File.class, XC_MethodReplacement.returnConstant(true));
    }
}