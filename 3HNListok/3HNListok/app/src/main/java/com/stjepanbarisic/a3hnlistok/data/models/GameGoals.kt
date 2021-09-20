package com.stjepanbarisic.a3hnlistok.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameGoals(
    val gameId: Long,
    var value: Int
) : Parcelable