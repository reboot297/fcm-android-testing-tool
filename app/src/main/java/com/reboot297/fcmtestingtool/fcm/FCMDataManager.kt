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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.reboot297.fcmtestingtool.utils.SharedPreferenceManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utils class to work with FCM.
 */
@Singleton
class FCMDataManager @Inject constructor() {

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    /**
     * Get FCM token and save it to the storage.
     */
    fun getFCMToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(
                OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    val token = task.result
                    Log.d("FCM", "TOKEN: $token")
                    sharedPreferenceManager.saveToken(token)
                }
            )
    }

    /**
     * Determines whether FCM auto-initialization is enabled or disabled.
     */
    fun isAutoInitEnabled() = FirebaseMessaging.getInstance().isAutoInitEnabled

    /**
     * Enables or disables auto-initialization of Firebase Cloud Messaging.
     */
    fun setAutoInitEnabled(enable: Boolean) {
        FirebaseMessaging.getInstance().isAutoInitEnabled = enable
    }

    /**
     * Delete Token.
     */
    fun deleteToken() {
        FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("FCM", "Token successfully deleted")
                sharedPreferenceManager.saveToken("")
            } else {
                Log.e("FCM", "Token was not deleted")
            }
        }
    }
}
