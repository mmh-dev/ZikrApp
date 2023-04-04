package com.mmh.zikrapp.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mmh.zikrapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}