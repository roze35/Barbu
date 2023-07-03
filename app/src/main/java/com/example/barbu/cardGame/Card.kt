package com.example.barbu.cardGame

import android.os.Parcel
import android.os.Parcelable
import com.example.barbu.utils.Rank
import com.example.barbu.utils.Suit

data class Card(val suit: Suit, val rank: Rank) : Parcelable {
    constructor(parcel: Parcel) : this(
        Suit.valueOf(parcel.readString() ?: ""),
        Rank.valueOf(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(suit.name)
        parcel.writeString(rank.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}

