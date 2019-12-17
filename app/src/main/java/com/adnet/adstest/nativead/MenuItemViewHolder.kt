package com.adnet.adstest.nativead

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adnet.adstest.R

class MenuItemViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    lateinit var menuItemName: TextView
    lateinit var menuItemDescription: TextView
    lateinit var menuItemPrice: TextView
    lateinit var menuItemCategory: TextView
    lateinit var menuItemImage: ImageView

    init {
        menuItemName = view.findViewById(R.id.menu_item_name)
        menuItemDescription = view.findViewById(R.id.menu_item_description)
        menuItemPrice = view.findViewById(R.id.menu_item_price)
        menuItemCategory = view.findViewById(R.id.menu_item_category)
        menuItemImage = view.findViewById(R.id.menu_item_image)
    }
}