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
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import com.reboot297.fcmtestingtool.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

private const val HASH_STRATEGY_SHA = "sha"

@Singleton
class KeyHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    /**
     * Get SHA FingerPrint.
     *
     * @return fingerprint of the keystore with which signed this app.
     */
    fun getSHAFingerPrint(): String? {
        return getFingerPrints(HASH_STRATEGY_SHA).firstOrNull()
    }

    /**
     * Get FingerPrint.
     *
     * @param hashStrategy - string like SHA1, SHA256, MD5
     * @return list of data
     */
    private fun getFingerPrints(hashStrategy: String): List<String> {
        val result = mutableListOf<String>()
        try {
            val info: PackageInfo = context.packageManager
                .getPackageInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance(hashStrategy)
                md.update(signature.toByteArray())
                val digest = md.digest()
                val builder = java.lang.StringBuilder()
                for (i in digest.indices) {
                    if (i != 0) {
                        builder.append(":")
                    }
                    val b = digest[i].toInt() and 0xff
                    val hex = Integer.toHexString(b)
                    if (hex.length == 1) {
                        builder.append("0")
                    }
                    builder.append(hex)
                }
                Log.e("KEY", "$hashStrategy $builder")
                result.add(builder.toString())
            }
        } catch (e: java.lang.Exception) {
            Log.e("Error", e.toString())
        }
        return result
    }
}
