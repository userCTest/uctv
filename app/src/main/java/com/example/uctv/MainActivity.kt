package com.example.uctv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import com.usercentrics.sdk.ui.tv.BannerSettings
import com.usercentrics.sdk.ui.tv.UsercentricsBanner

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = UsercentricsOptions(settingsId = "egLMgjg9j")
        Usercentrics.initialize(this, options)

        Usercentrics.isReady({
            val banner = UsercentricsBanner(
                context = this,
                settings = BannerSettings()
            )
            banner.showFirstLayer { userResponse ->
                // Handle userResponse
                print(userResponse)
            }
        }, {
            print("UC not ready")
        })
    }
}