package com.yahya.thehorn.models

import androidx.annotation.DrawableRes

data class HomeMenu(
    @DrawableRes
    var icon: Int,
    val title: String
)