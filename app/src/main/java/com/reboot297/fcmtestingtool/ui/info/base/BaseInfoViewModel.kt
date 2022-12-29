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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Base ViewModel for the screen info.
 */
abstract class BaseInfoViewModel : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<InfoItem>>()
    val itemsLiveData: LiveData<List<InfoItem>> = _itemsLiveData

    /**
     * Reload list of items.
     */
    fun loadItems() {
        viewModelScope.launch {
            _itemsLiveData.postValue(generateFields())
        }
    }

    /**
     * Generate list of items.
     */
    protected abstract fun generateFields(): List<InfoItem>
}
