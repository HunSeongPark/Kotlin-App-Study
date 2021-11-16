package com.hunseong.recycler_view

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flower(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int,
    val description: String
) : Parcelable