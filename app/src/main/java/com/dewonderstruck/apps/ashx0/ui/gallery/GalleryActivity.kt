package com.dewonderstruck.apps.ashx0.ui.gallery

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import com.dewonderstruck.apps.Config
import com.dewonderstruck.apps.ashx0.R
import com.dewonderstruck.apps.ashx0.databinding.ActivityGalleryBinding
import com.dewonderstruck.apps.ashx0.ui.common.PSAppCompactActivity
import com.dewonderstruck.apps.ashx0.utils.Constants
import com.dewonderstruck.apps.ashx0.utils.MyContextWrapper

class GalleryActivity : PSAppCompactActivity() {
    //region Override Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGalleryBinding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)

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
    private fun initUI(binding: ActivityGalleryBinding) {

        // Toolbar
        initToolbar(binding.toolbar, resources.getString(R.string.gallery__title))

        // setup Fragment
        setupFragment(GalleryFragment())
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);
    } //endregion
}