package com.dewonderstruck.apps.ashx0.ui.transaction.list;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.preference.PreferenceManager;

import com.dewonderstruck.apps.Config;

import com.dewonderstruck.apps.ashx0.R;

import com.dewonderstruck.apps.ashx0.ui.common.PSAppCompactActivity;
import com.dewonderstruck.apps.ashx0.utils.Constants;
import com.dewonderstruck.apps.ashx0.utils.MyContextWrapper;
import com.dewonderstruck.apps.ashx0.databinding.ActivityTransactionListBinding;

public class TransactionListActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTransactionListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_list);

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

    private void initUI(ActivityTransactionListBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.transaction__title));

        // setup Fragment

        setupFragment(new TransactionListFragment());

    }

    //endregion


}