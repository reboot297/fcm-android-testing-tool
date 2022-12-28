/*
 * Copyright (c) 2022. Viktor Pop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.reboot297.fcmtestingtool.ui.info.fcm

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reboot297.fcmtestingtool.R
import com.reboot297.fcmtestingtool.utils.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the screen with fcm info.
 */
@HiltViewModel
class FCMInfoViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
) : ViewModel(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val _itemsLiveData = MutableLiveData<List<FCMInfo>>()
    val itemsLiveData: LiveData<List<FCMInfo>> = _itemsLiveData

    init {
        sharedPreferenceManager.registerListener(this)
    }

    /**
     * Update UI if fcm token was changed.
     */
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (SharedPreferenceManager.KEY_FCM_TOKEN == key) {
            loadItems()
        }
    }

    /**
     * Reload list of items.
     */
    fun loadItems() {
        viewModelScope.launch {
            _itemsLiveData.postValue(fcmFields())
        }
    }

    /**
     * Generate list of items.
     */
    private fun fcmFields() = listOf(
        FCMInfo(
            R.string.fcm_info_registration_token_label,
            sharedPreferenceManager.getToken() ?: ""
        ),
        FCMInfo(R.string.fcm_info_project_id, R.string.project_id),
        FCMInfo(R.string.fcm_info_google_storage_bucket, R.string.google_storage_bucket),
        FCMInfo(R.string.fcm_info_google_app_id, R.string.google_app_id),
        FCMInfo(R.string.fcm_info_google_api_key, R.string.google_api_key),
        FCMInfo(
            R.string.fcm_info_google_crash_reporting_api_key,
            R.string.google_crash_reporting_api_key
        ),
        FCMInfo(R.string.fcm_info_gcm_default_sender_id, R.string.gcm_defaultSenderId),
        FCMInfo(R.string.fcm_info_default_web_client_id, R.string.default_web_client_id)
    )

    override fun onCleared() {
        sharedPreferenceManager.unRegisterListener(this)
        super.onCleared()
    }
}
