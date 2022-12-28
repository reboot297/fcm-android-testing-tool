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

package com.reboot297.fcmtestingtool.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.reboot297.fcmtestingtool.utils.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Service to listen fcm messages.
 */
@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    /**
     * Listen updates for FCM token.
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Updated token: $token")
        sharedPreferenceManager.saveToken(token)
    }
}
