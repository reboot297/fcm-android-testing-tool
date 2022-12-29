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

package com.reboot297.fcmtestingtool.ui.info.app

import com.reboot297.fcmtestingtool.BuildConfig
import com.reboot297.fcmtestingtool.R
import com.reboot297.fcmtestingtool.ui.info.base.BaseInfoViewModel
import com.reboot297.fcmtestingtool.ui.info.base.InfoItem
import com.reboot297.fcmtestingtool.utils.KeyHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the screen with application info.
 */
@HiltViewModel
class AppInfoViewModel @Inject constructor(
    private val keyHelper: KeyHelper
) : BaseInfoViewModel() {

    override fun generateFields() = listOf(
        InfoItem(
            R.string.app_info_label_package_name,
            BuildConfig.APPLICATION_ID,
            R.string.app_info_description_package_name
        ),
        InfoItem(
            R.string.app_info_label_sha_certificate,
            keyHelper.getSHAFingerPrint() ?: "",
            R.string.app_info_description_sha_certificate
        )
    )
}
