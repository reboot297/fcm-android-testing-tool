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

package com.reboot297.fcmtestingtool.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "ConnectivityMonitor"

/**
 * Class for listening connection status changes.
 */
@Singleton
class ConnectivityMonitor @Inject constructor(
    @ApplicationContext applicationContext: Context
) {

    private lateinit var connectivityManager: ConnectivityManager

    private val _networkState = MutableLiveData<NetworkType>()
    val networkState: LiveData<NetworkType> get() = _networkState

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(TAG, "onAvailable: $network")
            _networkState.postValue(getNetworkType())
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(TAG, "onLost: $network")
            _networkState.postValue(getNetworkType())
        }
    }

    init {
        Log.d(TAG, "init: ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager =
                applicationContext.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(networkCallback)
            } else {
                // TODO implement pre-N logic
            }
        } else {
            // TODO implement pre-M logic
        }
        _networkState.postValue(getNetworkType())
    }

    fun getNetworkType(): NetworkType {
        var isConnectedToWifi = false
        var isConnectedToCellular = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork.let {
                connectivityManager.getNetworkCapabilities(it)
            }?.let {
                isConnectedToCellular = it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                isConnectedToWifi = it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            connectivityManager.activeNetworkInfo?.let {
                if (it.isConnectedOrConnecting) {
                    isConnectedToCellular = it.type == ConnectivityManager.TYPE_MOBILE
                    isConnectedToWifi = it.type == ConnectivityManager.TYPE_WIFI
                }
            }
        }
        return if (isConnectedToCellular && isConnectedToWifi) {
            NetworkType.WIFI
        } else if (isConnectedToCellular) {
            NetworkType.CELLULAR
        } else if (isConnectedToWifi) {
            NetworkType.WIFI
        } else {
            NetworkType.NO_NETWORK
        }
    }
}

/**
 * Network types.
 */
enum class NetworkType {
    WIFI,
    CELLULAR,
    NO_NETWORK
}
