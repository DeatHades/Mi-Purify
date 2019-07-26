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
            // 1.5.9.0
            findAndHookMethod("com.android.thememanager.g.t", lpparam.classLoader, "isProductBought", XC_MethodReplacement.returnConstant(true));
            findAndHookMethod("com.android.thememanager.g.q", lpparam.classLoader, "isProductBought", XC_MethodReplacement.returnConstant(true));

            //1.6.2.0
            findAndHookMethod("com.android.thememanager.i.t", lpparam.classLoader, "isProductBought", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    int productPrice = XposedHelpers.getIntField(param.thisObject, "productPrice");
                    if (productPrice == 0) {
                        return XposedHelpers.getBooleanField(param.thisObject, "productBought");
                    } else {
                        return true;
                    }
                }
            });

            // return this.eV.getTrialTime() > 0;
            // 1.5.9.0
            findAndHookMethod("com.android.thememanager.util.ch", lpparam.classLoader, "w", XC_MethodReplacement.returnConstant(true));
            //1.6.20
            findAndHookMethod("com.android.thememanager.util.cg", lpparam.classLoader, "w", XC_MethodReplacement.returnConstant(true));

            // stringBuilder.append("   check rights file: ");
            // if (new File(uVar.b()).getAbsolutePath().startsWith("/system")) {
            if (findClassWithOutLog("com.android.thememanager.g.q", lpparam.classLoader) != null) {
                //1.5.9.0
                findAndHookMethod("com.android.thememanager.b.b.d", lpparam.classLoader, "a", findClass("com.android.thememanager.g.q", lpparam.classLoader), XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
            }
            if (findClass("com.android.thememanager.d.b.d", lpparam.classLoader) != null) {
                //1.6.2.0
                findAndHookMethod("com.android.thememanager.b.b.d", lpparam.classLoader, "a", findClass("com.android.thememanager.i.q", lpparam.classLoader), XC_MethodReplacement.returnConstant(getDrmResultSUCCESS()));
            }

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