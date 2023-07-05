package com.example.barbu

import android.os.Parcel
import android.os.Parcelable
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Utils

open class Player(val name: String, val position:Position):Parcelable {
    var hand: MutableSet<Card> = mutableSetOf()
    var winCards: MutableSet<Card> = mutableSetOf()

    constructor(parcel: Parcel) : this(parcel.readString() ?: "",Position.values()[parcel.readInt()]) {
        val handList = mutableListOf<Card>()
        val winCardsList = mutableListOf<Card>()
        parcel.readTypedList(handList, Card.CREATOR)
        parcel.readTypedList(winCardsList, Card.CREATOR)
        hand.addAll(handList)
        winCards.addAll(winCardsList)
    }


    open fun reInit(){
        hand.clear()
        winCards.clear()
    }

    open suspend fun gameOver(southScore:Int, westScore:Int, northScore:Int, eastScore:Int){

    }

    open fun removeCard(c:Card){
        hand.remove(c)
    }

    fun addCard(card: Card?) {
        if (card != null) {
            hand.add(card)
        }
    }

    fun playRandomCard(possibleCards:MutableSet<Card>): Card {
        val card= Utils.randomDeal(possibleCards)
        hand.remove(card)
        return card
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(position.ordinal)
        parcel.writeTypedList(hand.toList())
        parcel.writeTypedList(winCards.toList())
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Player> = object : Parcelable.Creator<Player> {
            override fun createFromParcel(parcel: Parcel): Player {
                return Player(parcel)
            }

            override fun newArray(size: Int): Array<Player?> {
                return arrayOfNulls(size)
            }
        }
    }

}


