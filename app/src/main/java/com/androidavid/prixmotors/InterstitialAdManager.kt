
import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdManager private constructor(context: Context) {

    private val TAG = "InterstitialAdManager"
    private var mInterstitialAd: InterstitialAd? = null

    init {
        MobileAds.initialize(context) {}
    }

    companion object {
        @Volatile
        private var INSTANCE: InterstitialAdManager? = null

        fun getInstance(context: Context): InterstitialAdManager {
            return INSTANCE ?: synchronized(this) {
                val instance = InterstitialAdManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }

    fun loadInterstitialAd(activity: Activity) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(activity, "ca-app-pub-2196269772372009/3265134923", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.e(TAG, "Failed to load interstitial ad: ${adError.message}")
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Interstitial ad loaded successfully.")
                mInterstitialAd = interstitialAd
            }
        })
    }

    fun showInterstitialAd(activity: Activity) {
        mInterstitialAd?.show(activity)
    }
    fun showInterstitialAd(activity: Activity, onAdClosed: () -> Unit) {
        mInterstitialAd?.let { interstitialAd ->
            interstitialAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "The ad was dismissed.")
                    onAdClosed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {
                    Log.d(TAG, "The ad failed to show.")
                    onAdClosed()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "The ad was shown.")
                    mInterstitialAd = null // Ensure the ad is not shown again
                }
            }
            interstitialAd.show(activity)
        } ?: onAdClosed()
    }
}
