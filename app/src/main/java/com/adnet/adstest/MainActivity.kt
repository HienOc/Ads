package com.adnet.adstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.adnet.adstest.nativead.ActivityNativeAds
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RewardedVideoAdListener, View.OnClickListener {

    private lateinit var rewardedVideoAd: RewardedVideoAd
    private lateinit var interstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWatchVideo.setOnClickListener(this)
        btnIntertitialad.setOnClickListener(this)
        btnNativeAd.setOnClickListener(this)
        btnNativeAds.setOnClickListener(this)
    }
    private fun loadInterstitial(){
        interstitialAd= InterstitialAd(this)
        interstitialAd.adUnitId=getString(R.string.test_interstitial_unit_id)
        interstitialAd.loadAd(AdRequest.Builder().build())

        interstitialAd.adListener= object :AdListener(){
            override fun onAdClosed() {
                super.onAdClosed()
                interstitialAd.loadAd(AdRequest.Builder().build())
            }

        }
    }
    private fun loadBanner(){
        //Banner ads
        val adRequest= AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener=object : AdListener(){
            //xuly sukien
        }
    }
    private fun loadVideo(){
        //rewarded video ads
        MobileAds.initialize(this,getString(R.string.sample_ad_mob_app_id))
        rewardedVideoAd= MobileAds.getRewardedVideoAdInstance(this)
        rewardedVideoAd.rewardedVideoAdListener=this
        loadAd()
    }
    private fun loadAd(){
        rewardedVideoAd.loadAd(getString(R.string.test_rewarded_unit_id),AdRequest.Builder().build())
    }

    override fun onClick(v: View) {
       when(v.id){
           R.id.btnWatchVideo->{
               if(rewardedVideoAd.isLoaded){
                   rewardedVideoAd.show()
               }
           }
           R.id.btnIntertitialad->{
               if(interstitialAd.isLoaded){
                   interstitialAd.show()
               }
           }
           R.id.btnNativeAd->{
               var intent= Intent(this,NativeAdActivity::class.java)
               startActivity(intent)
           }
           R.id.btnNativeAds->{
               val intent=Intent(this, ActivityNativeAds::class.java)
               startActivity(intent)
           }
       }
    }

    override fun onStart() {
        super.onStart()
        loadVideo()
        loadInterstitial()
        loadBanner()
    }

    override fun onResume() {
        super.onResume()
        rewardedVideoAd.resume(this)
    }

    override fun onPause() {
        super.onPause()
        rewardedVideoAd.pause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        rewardedVideoAd.destroy(this)
    }

    override fun onRewardedVideoAdClosed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdLeftApplication() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdLoaded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdOpened() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoCompleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewarded(p0: RewardItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
