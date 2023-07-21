package com.example.barbu.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.barbu.cardGame.Card
import kotlin.random.Random

class Utils {
    companion object {
        fun nextPosition(position:Int): Int {
            return (position+1)%4
        }

        fun positionToInt(p: Position):Int{
            return p.ordinal
        }

        fun randomDeal(possibleCards: MutableSet<Card>): Card {
            val random = Random
            val list = possibleCards.toList()
            val randomIndex = random.nextInt(possibleCards.size)
            return list[randomIndex]
        }

        @SuppressLint("DiscouragedApi")
        fun getRessourceId(card: Card, context: Context?): Int {
            val cardString =
                card.rank.toString().lowercase() + "_of_" + card.suit.toString().lowercase()
            return context!!.resources.getIdentifier(cardString, "drawable", context.packageName)
        }

        fun cardToInt(card:Card):Int{
            return card.suit.ordinal*8+(card.rank.ordinal)
        }
    }
}