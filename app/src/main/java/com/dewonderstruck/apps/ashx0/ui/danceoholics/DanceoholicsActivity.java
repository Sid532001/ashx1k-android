package com.dewonderstruck.apps.ashx0.ui.danceoholics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.preference.PreferenceManager;

import com.dewonderstruck.apps.Config;
import com.dewonderstruck.apps.ashx0.R;
import com.dewonderstruck.apps.ashx0.databinding.ActivityDanceoholicsBinding;
import com.dewonderstruck.apps.ashx0.databinding.ActivitySettingBinding;
import com.dewonderstruck.apps.ashx0.ui.common.PSAppCompactActivity;
import com.dewonderstruck.apps.ashx0.utils.Constants;
import com.dewonderstruck.apps.ashx0.utils.MyContextWrapper;

public class DanceoholicsActivity extends PSAppCompactActivity {
    protected com.dewonderstruck.apps.ashx0.databinding.ActivityDanceoholicsBinding binding;

    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDanceoholicsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_danceoholics);

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

    private void initUI(ActivityDanceoholicsBinding binding) {
        this.binding = binding;

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.edit_danceoholics_title));
        // setup Fragment
        setupFragment(new DanceholicsFragment());

    }

    //endregion


}
