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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reboot297.fcmtestingtool.databinding.ItemFcmInfoBinding

/**
 * Adapter to display FCM messages.
 */
class FCMInfoAdapter : ListAdapter<FCMInfo, FCMInfoViewHolder>(FCMInfoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FCMInfoViewHolder {
        return FCMInfoViewHolder(
            ItemFcmInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FCMInfoViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            labelView.text = root.context.getString(item.label)
            valueView.text = when (item.value) {
                is Int -> root.context.getString(item.value)
                is String -> item.value.toString()
                else -> ""
            }
        }
    }
}

/**
 * ViewHolder for FCM Info item.
 */
class FCMInfoViewHolder(val binding: ItemFcmInfoBinding) : RecyclerView.ViewHolder(binding.root)
