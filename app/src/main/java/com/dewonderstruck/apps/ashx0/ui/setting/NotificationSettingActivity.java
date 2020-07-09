package com.dewonderstruck.apps.ashx0.ui.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.dewonderstruck.apps.ashx0.R;
import com.dewonderstruck.apps.Config;

import com.dewonderstruck.apps.ashx0.databinding.ActivityNotificationSettingBinding;
import com.dewonderstruck.apps.ashx0.ui.common.PSAppCompactActivity;
import com.dewonderstruck.apps.ashx0.ui.notification.setting.NotificationSettingFragment;
import com.dewonderstruck.apps.ashx0.utils.Constants;
import com.dewonderstruck.apps.ashx0.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;

public class NotificationSettingActivity extends PSAppCompactActivity {

    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNotificationSettingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notification_setting);

        // Init all UI
        initUI(binding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }
    //endregion


    //region Private Methods

    private void initUI(ActivityNotificationSettingBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.notification_setting__title));

        // setup Fragment
        setupFragment(new NotificationSettingFragment());

    }

    //endregion

}
