package com.adnet.adstest.nativead

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adnet.adstest.R
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import java.util.*

class RecyclerViewAdapter(private val list: ArrayList<Any>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MENU_ITEM_VIEW_TYPE = 0
    private val UNIFIED_NATIVE_AD_VIEW_TYPE = 1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            UNIFIED_NATIVE_AD_VIEW_TYPE -> {
                val unifiedNativeLayoutView =
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.ad_unified, viewGroup, false)
                return UnifiedNativeAdViewHolder(unifiedNativeLayoutView)
            }
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemLayoutView =
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item_container, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
            else -> {
                val menuItemLayoutView =
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item_container, viewGroup, false)
                return MenuItemViewHolder(menuItemLayoutView)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            UNIFIED_NATIVE_AD_VIEW_TYPE -> {
                val unifiedNativeAd = list.get(position) as UnifiedNativeAd
                populateNativeAdView(
                    unifiedNativeAd,
                    (holder as UnifiedNativeAdViewHolder).getAdView()
                )
            }
            MENU_ITEM_VIEW_TYPE -> {
                val menuItemViewHolder = holder as MenuItemViewHolder
                val menuItem = list[position] as MenuItem
                menuItemViewHolder.menuItemName.text = menuItem.name
                menuItemViewHolder.menuItemImage.setImageResource(menuItem.imagePathId)
                menuItemViewHolder.menuItemPrice.text = menuItem.price
                menuItemViewHolder.menuItemCategory.text = menuItem.category
                menuItemViewHolder.menuItemDescription.text = menuItem.description
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val recyclerViewItem = list[position]
        if (recyclerViewItem is UnifiedNativeAd) {
            return UNIFIED_NATIVE_AD_VIEW_TYPE
        }
        return MENU_ITEM_VIEW_TYPE
    }

    private fun populateNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView) {

        (adView.headlineView as TextView).text = nativeAd.headline
        (adView.bodyView as TextView).text = nativeAd.body
        (adView.callToActionView as Button).text = nativeAd.callToAction

        val icon = nativeAd.icon

        if (icon == null) {
            adView.iconView.visibility = View.INVISIBLE
        } else {
            (adView.iconView as ImageView).setImageDrawable(icon.drawable)
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            adView.starRatingView.visibility = View.VISIBLE
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating.toFloat()
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            adView.advertiserView.visibility = View.VISIBLE
            (adView.advertiserView as TextView).text = nativeAd.advertiser
        }

        adView.setNativeAd(nativeAd)
    }
}
