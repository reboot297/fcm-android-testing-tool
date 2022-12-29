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

package com.reboot297.fcmtestingtool.ui.info.base

import androidx.recyclerview.widget.DiffUtil

/**
 * DiffCallback for Info items.
 */
class InfoDiffCallback : DiffUtil.ItemCallback<InfoItem>() {
    override fun areItemsTheSame(oldItem: InfoItem, newItem: InfoItem) =
        oldItem.value == newItem.value

    override fun areContentsTheSame(oldItem: InfoItem, newItem: InfoItem) = oldItem == newItem
}
