package com.dewonderstruck.apps.ashx0.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.databinding.DataBindingUtil
import com.dewonderstruck.apps.Config
import com.dewonderstruck.apps.ashx0.R
import com.dewonderstruck.apps.ashx0.databinding.ActivityProfileEditBinding
import com.dewonderstruck.apps.ashx0.ui.common.PSAppCompactActivity
import com.dewonderstruck.apps.ashx0.utils.Constants
import com.dewonderstruck.apps.ashx0.utils.MyContextWrapper

class ProfileEditActivity : PSAppCompactActivity() {
    //region Override Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProfileEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)
        // Init all UI
        initUI(binding)
    }

    override fun attachBaseContext(newBase: Context) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(newBase)
        val CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE)
        val CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE)
        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true))
    }

    //endregion
    //region Private Methods
    private fun initUI(binding: ActivityProfileEditBinding) {

        // Toolbar
        initToolbar(binding.toolbar, resources.getString(R.string.edit_profile__title))

        // setup Fragment
        setupFragment(ProfileEditFragment())
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);
    }

    //endregion
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentById(R.id.content_frame)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }
}