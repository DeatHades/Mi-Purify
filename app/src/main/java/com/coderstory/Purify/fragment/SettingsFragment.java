package com.coderstory.Purify.fragment;


import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Switch;

import com.coderstory.Purify.R;

import ren.solid.library.fragment.base.BaseFragment;

import static com.coderstory.Purify.utils.FunctionModule.MusicCustomization;
import static com.coderstory.Purify.utils.FunctionModule.RemoveSearchBar;
import static com.coderstory.Purify.utils.FunctionModule.enableCheck;
import static com.coderstory.Purify.utils.FunctionModule.fixpcb;
import static com.coderstory.Purify.utils.FunctionModule.hideicon;
import static com.coderstory.Purify.utils.FunctionModule.installType;

public class SettingsFragment extends BaseFragment {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void setUpView() {


        $(R.id.enableCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditor().putBoolean(enableCheck, ((Switch) v).isChecked());
                getEditor().apply();
            }
        });


        $(R.id.RemoveSearchBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditor().putBoolean(RemoveSearchBar, ((Switch) v).isChecked());
                getEditor().apply();
            }
        });

        $(R.id.miuiMusicCustomization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditor().putBoolean(MusicCustomization, ((Switch) v).isChecked());
                getEditor().apply();
            }
        });


        $(R.id.installType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditor().putBoolean(installType, ((Switch) v).isChecked());
                getEditor().apply();
            }
        });


        $(R.id.fixpcb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditor().putBoolean(fixpcb, ((Switch) v).isChecked());
                getEditor().apply();
            }
        });

        $(R.id.hideicon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditor().putBoolean(hideicon, ((Switch) v).isChecked());
                getEditor().apply();
                ComponentName localComponentName = new ComponentName(getMContext(), "com.coderstory.Purify.activity.SplashActivity");
                PackageManager localPackageManager = getMContext().getPackageManager();
                localPackageManager.getComponentEnabledSetting(localComponentName);
                PackageManager packageManager = getMContext().getPackageManager();
                ComponentName componentName = new ComponentName(getMContext(), "com.coderstory.Purify.activity.SplashActivity");

                if (((Switch) v).isChecked()) {
                    packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                } else {
                    packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                            PackageManager.DONT_KILL_APP);
                }

            }
        });


    }

    @Override
    protected void setUpData() {
        ((Switch) $(R.id.enableCheck)).setChecked(getPrefs().getBoolean(enableCheck, true));
        ((Switch) $(R.id.RemoveSearchBar)).setChecked(getPrefs().getBoolean(RemoveSearchBar, false));
        ((Switch) $(R.id.miuiMusicCustomization)).setChecked(getPrefs().getBoolean(MusicCustomization, false));
        ((Switch) $(R.id.fixpcb)).setChecked(getPrefs().getBoolean(fixpcb, false));
        ((Switch) $(R.id.hideicon)).setChecked(getPrefs().getBoolean(hideicon, false));
        ((Switch) $(R.id.installType)).setChecked(getPrefs().getBoolean(installType, false));
    }

}
