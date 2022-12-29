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
import com.reboot297.fcmtestingtool.R
import com.reboot297.fcmtestingtool.ui.info.base.BaseInfoViewModel
import com.reboot297.fcmtestingtool.ui.info.base.InfoItem
import com.reboot297.fcmtestingtool.utils.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the screen with fcm info.
 */
@HiltViewModel
class FCMInfoViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
) : BaseInfoViewModel(), SharedPreferences.OnSharedPreferenceChangeListener {

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
     * Generate list of items.
     */
    override fun generateFields() = listOf(
        InfoItem(
            R.string.fcm_info_label_registration_token,
            sharedPreferenceManager.getToken() ?: "",
            R.string.fcm_info_description_registration_token
        ),
        InfoItem(
            R.string.fcm_info_label_project_id,
            R.string.project_id,
            R.string.fcm_info_description_project_id
        ),
        InfoItem(
            R.string.fcm_info_label_google_storage_bucket,
            R.string.google_storage_bucket,
            R.string.fcm_info_description_google_storage_bucket
        ),
        InfoItem(
            R.string.fcm_info_label_google_app_id,
            R.string.google_app_id,
            R.string.fcm_info_description_google_app_id
        ),
        InfoItem(
            R.string.fcm_info_label_google_api_key,
            R.string.google_api_key,
            R.string.fcm_info_description_google_api_key
        ),
        InfoItem(
            R.string.fcm_info_label_google_crash_reporting_api_key,
            R.string.google_crash_reporting_api_key,
            R.string.fcm_info_description_google_crash_reporting_api_key
        ),
        InfoItem(
            R.string.fcm_info_label_gcm_default_sender_id,
            R.string.gcm_defaultSenderId,
            R.string.fcm_info_description_gcm_default_sender_id
        ),
        InfoItem(
            R.string.fcm_info_label_default_web_client_id,
            R.string.default_web_client_id,
            R.string.fcm_info_description_default_web_client_id
        )
    )

    override fun onCleared() {
        sharedPreferenceManager.unRegisterListener(this)
        super.onCleared()
    }
}
