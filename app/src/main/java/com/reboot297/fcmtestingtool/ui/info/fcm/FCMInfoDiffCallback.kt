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

import androidx.recyclerview.widget.DiffUtil

/**
 * DiffCallback for FCM Info items.
 */
class FCMInfoDiffCallback : DiffUtil.ItemCallback<FCMInfo>() {
    override fun areItemsTheSame(oldItem: FCMInfo, newItem: FCMInfo) =
        oldItem.value == newItem.value

    override fun areContentsTheSame(oldItem: FCMInfo, newItem: FCMInfo) = oldItem == newItem
}
