package com.adnet.adstest.nativead

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adnet.adstest.R
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAdView


class UnifiedNativeAdViewHolder(val view:View): RecyclerView.ViewHolder(view) {
    private lateinit var adView:UnifiedNativeAdView

    fun getAdView():UnifiedNativeAdView{
        return adView
    }
    init {
        adView=view.findViewById(R.id.ad_view)
        adView.mediaView=view.findViewById(R.id.ad_media) as MediaView
        adView.headlineView=view.findViewById(R.id.ad_headline)
        adView.bodyView=view.findViewById(R.id.ad_body)
        adView.callToActionView=view.findViewById(R.id.ad_call_to_action)
        adView.iconView=view.findViewById(R.id.ad_icon)
        adView.priceView=view.findViewById(R.id.ad_price)
        adView.starRatingView=view.findViewById(R.id.ad_stars)
        adView.storeView=view.findViewById(R.id.ad_store)
        adView.advertiserView=view.findViewById(R.id.ad_advertiser)
    }

}