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

package com.reboot297.fcmtestingtool.ui.info.device

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.reboot297.fcmtestingtool.R
import com.reboot297.fcmtestingtool.ui.info.base.BaseInfoViewModel
import com.reboot297.fcmtestingtool.ui.info.base.InfoItem
import com.reboot297.fcmtestingtool.utils.ConnectivityMonitor
import com.reboot297.fcmtestingtool.utils.NetworkType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the screen with device info.
 */
@HiltViewModel
class DeviceInfoViewModel @Inject constructor(
    private val connectivityMonitor: ConnectivityMonitor
) : BaseInfoViewModel() {

    private var type: NetworkType = NetworkType.NO_NETWORK

    fun listenConnectionUpdates() {
        viewModelScope.launch {
            connectivityMonitor.networkState.asFlow().collect {
                type = it
                loadItems()
            }
        }
    }

    override fun generateFields(): List<InfoItem> {
        return listOf(
            InfoItem(
                R.string.device_info_label_network_status,
                getNetworkStatusValue(type),
                R.string.device_info_description_network_status
            )
        )
    }

    private fun getNetworkStatusValue(type: NetworkType) = when (type) {
        NetworkType.WIFI -> R.string.device_info_value_network_status_wifi
        NetworkType.CELLULAR -> R.string.device_info_value_network_status_mobile
        else -> R.string.device_info_value_network_status_off
    }
}
