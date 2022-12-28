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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.reboot297.fcmtestingtool.databinding.FragmentFcmInfoBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Screen with information about fcm project.
 * The data displayed on this screen collected from Firebase console.
 */
@AndroidEntryPoint
class FCMInfoFragment : Fragment() {
    private var _binding: FragmentFcmInfoBinding? = null
    private val binding: FragmentFcmInfoBinding get() = _binding!!

    private val viewModel: FCMInfoViewModel by viewModels()
    private val adapter = FCMInfoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFcmInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fcmListView.adapter = adapter
        viewModel.itemsLiveData.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
