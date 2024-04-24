package com.androidavid.clucthmaster

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object FirebaseRemoteConfigHelper {

    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun fetchRemoteConfig(onComplete: Runnable) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete.run()
                }
            }
    }

    fun getString(key: String): String {
        return remoteConfig.getString(key)
    }

    fun getInt(key: String): Int {
        return remoteConfig.getLong(key).toInt()
    }
}
