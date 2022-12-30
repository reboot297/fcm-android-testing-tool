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

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.reboot297.fcmtestingtool.databinding.LayoutRegistrationTokenBinding

/**
 * Layout with registration token info.
 */
class RegistrationTokenLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var onShareListener: OnShareListener? = null
    var onTokenDeleteListener: OnTokenDeleteListener? = null

    private val binding =
        LayoutRegistrationTokenBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.root.setOnClickListener { onClickExpand() }
        binding.shareView.setOnClickListener { onShareListener?.onShareText(binding.valueView.text.toString()) }
        binding.deleteView.setOnClickListener { onTokenDeleteListener?.onTokenDelete() }
    }

    fun setTokenValue(token: String) {
        binding.valueView.text = token
    }

    private fun onClickExpand() = with(binding) {
        val shouldExpand = !descriptionView.isVisible
        descriptionView.isVisible = shouldExpand
        expandView.isChecked = shouldExpand
        shareView.isVisible = shouldExpand
        deleteView.isVisible = shouldExpand
    }
}
