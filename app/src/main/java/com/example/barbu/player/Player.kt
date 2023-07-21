package com.example.barbu.player

import android.os.Parcel
import android.os.Parcelable
import com.example.barbu.utils.Position
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Utils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class Player(val name: String, val position: Position):Parcelable {
    var hand: MutableSet<Card> = mutableSetOf()
    var winCards: MutableSet<Card> = mutableSetOf()
    var gameData=GameData()
    var currentRoundData=RoundData(position.ordinal)

    constructor(parcel: Parcel) : this(parcel.readString() ?: "", Position.values()[parcel.readInt()]) {
        val handList = mutableListOf<Card>()
        val winCardsList = mutableListOf<Card>()
        parcel.readTypedList(handList, Card.CREATOR)
        parcel.readTypedList(winCardsList, Card.CREATOR)
        hand.addAll(handList)
        winCards.addAll(winCardsList)

        val jsonData=parcel.readString()
        gameData= Json.decodeFromString(jsonData?:"")
        val jsonCurrentRound=parcel.readString()
        currentRoundData=Json.decodeFromString(jsonCurrentRound?:"")
    }


    open fun reInit(){
        hand.clear()
        winCards.clear()
        gameData= GameData()
        currentRoundData= RoundData(position.ordinal)
    }

    open fun endGame(southScore:Int, westScore:Int, northScore:Int, eastScore:Int){
        val score=when(position){
            Position.SOUTH->southScore
            Position.WEST->westScore
            Position.NORTH->northScore
            Position.EAST->eastScore
        }
        gameData.finalScore(score)
        //var tmp=gameData.toCsv()
        //Log.d("csvForNN",tmp)
    }

    open suspend fun gameOver(southScore:Int, westScore:Int, northScore:Int, eastScore:Int){
    }

    fun addCard(card: Card?) {
        if (card != null) {
            hand.add(card)
            currentRoundData.addCard(card)
        }
    }

    fun playRandomCard(possibleCards:MutableSet<Card>): Card {
        val card= Utils.randomDeal(possibleCards)
        hand.remove(card)
        currentRoundData.playCard(this,card)
        return card
    }

    fun endTrick(){
        gameData.addRoundData(currentRoundData)
        currentRoundData=RoundData(currentRoundData)
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(position.ordinal)
        parcel.writeTypedList(hand.toList())
        parcel.writeTypedList(winCards.toList())

        val jsonData=gameData.toJson()
        parcel.writeString(jsonData)
        val jsonCurrentRound=Json.encodeToString(currentRoundData)
        parcel.writeString(jsonCurrentRound)
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

    fun generateCsv():String{
        return(gameData.toCsv())
    }

}


