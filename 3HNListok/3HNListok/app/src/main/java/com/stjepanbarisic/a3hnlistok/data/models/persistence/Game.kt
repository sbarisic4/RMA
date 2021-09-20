package com.stjepanbarisic.a3hnlistok.data.models.persistence

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val gameId: Long = 0,
    val startTime: Long,
    var isFinished: Boolean = false
) : Parcelable