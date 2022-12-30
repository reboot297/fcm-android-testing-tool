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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reboot297.fcmtestingtool.databinding.FragmentInfoBinding
import javax.inject.Inject

/**
 * Base screen for info items.
 */
abstract class BaseInfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding: FragmentInfoBinding get() = _binding!!

    abstract val viewModel: BaseInfoViewModel

    @Inject
    lateinit var adapter: InfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.infoListView.adapter = adapter
        viewModel.itemsLiveData.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
