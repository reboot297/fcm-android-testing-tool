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

package com.reboot297.fcmtestingtool.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reboot297.fcmtestingtool.BuildConfig
import com.reboot297.fcmtestingtool.R
import com.reboot297.fcmtestingtool.databinding.FragmentAboutBinding

/**
 * Screen with information about the app
 */
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding: FragmentAboutBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            appNameValueView.setText(R.string.app_name)
            appVersionValueView.text = BuildConfig.VERSION_NAME
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
