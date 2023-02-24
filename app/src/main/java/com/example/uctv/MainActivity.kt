package com.example.uctv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.usercentrics.sdk.GeneralStyleSettings
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import com.usercentrics.sdk.ui.tv.BannerSettings
import com.usercentrics.sdk.ui.tv.UsercentricsBanner

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    val LOG_TAG = "[USERCENTRICS][UCTV]"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = UsercentricsOptions(settingsId = "egLMgjg9j")
        Usercentrics.initialize(this, options)

        Usercentrics.isReady({ status ->
            Log.d(LOG_TAG, "shouldCollectConsent - ${status.shouldCollectConsent}")
            if (status.shouldCollectConsent) {
                // Show banner to collect consent
                val banner = UsercentricsBanner(
                    context = this,
                    settings = BannerSettings()
                )
                banner.showFirstLayer { userResponse ->
                    // Handle userResponse
                    print(userResponse)
                }
            } else {
                // Apply consent with status.consents
            }

        }, {
            print("UC not ready")
        })
    }
}