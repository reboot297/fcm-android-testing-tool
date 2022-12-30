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

package com.reboot297.fcmtestingtool.ui.options.fcm

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reboot297.fcmtestingtool.fcm.FCMDataManager
import com.reboot297.fcmtestingtool.utils.IntentUtils
import com.reboot297.fcmtestingtool.utils.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for FCM Options screen.
 */
@HiltViewModel
class FCMOptionsViewModel @Inject constructor(
    private val fcmDataManager: FCMDataManager,
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val intentUtils: IntentUtils
) : ViewModel(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val _isAutoInisEnabledLiveData = MutableLiveData<Boolean>()
    val isAutoInisEnabledLiveData: LiveData<Boolean> = _isAutoInisEnabledLiveData

    private val _tokenLiveData = MutableLiveData<String>()
    val tokenLiveData: LiveData<String> = _tokenLiveData

    init {
        sharedPreferenceManager.registerListener(this)
    }

    /**
     * Update UI if fcm token was changed.
     */
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (SharedPreferenceManager.KEY_FCM_TOKEN == key) {
            _tokenLiveData.postValue(sharedPreferenceManager.getToken())
        }
    }

    fun loadData() {
        _isAutoInisEnabledLiveData.postValue(fcmDataManager.isAutoInitEnabled())
        _tokenLiveData.postValue(sharedPreferenceManager.getToken())
    }

    fun setIsAutoEnabled(enabled: Boolean) {
        fcmDataManager.setAutoInitEnabled(enabled)
        _isAutoInisEnabledLiveData.postValue(fcmDataManager.isAutoInitEnabled())
    }

    fun deleteToken() {
        fcmDataManager.deleteToken()
    }

    fun shareValue(text: String) {
        intentUtils.shareText(text)
    }

    override fun onCleared() {
        sharedPreferenceManager.unRegisterListener(this)
        super.onCleared()
    }
}
