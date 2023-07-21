package com.example.barbu

import com.example.barbu.player.Player
import com.example.barbu.player.RoundData
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Position
import com.example.barbu.utils.Rank
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RoundDataTest {
    @Test
    fun newTrickTest(){
        val roundData= RoundData(0)
        assertEquals(0,roundData.numTrick)
        roundData.newTrick()
        assertEquals(1,roundData.numTrick)
    }

    @Test
    fun addCardTest(){
        val roundData= RoundData(0)
        assertTrue(roundData.hand.all { it == -1 })
        val card=Card(Suit.DIAMONDS,Rank.KING)
        roundData.addCard(card)
        assertTrue(roundData.hand[Utils.cardToInt(card)]==1)
    }

    @Test
    fun playCardTest(){
        val roundData= RoundData(0)
        assertTrue(roundData.hand.all { it == -1 })
        val card=Card(Suit.DIAMONDS,Rank.KING)
        roundData.addCard(card)
        assertTrue(roundData.hand[Utils.cardToInt(card)]==1)
        assertTrue(roundData.output[Utils.cardToInt(card)]==1)
        val player= Player("South", Position.SOUTH)
        roundData.playCard(player,card)
        assertTrue(roundData.hand.all { it == -1 })
    }

    @Test
    fun finalScoreTest(){
        val roundData= RoundData(0)
        assertTrue(roundData.hand.all { it == -1 })
        val card=Card(Suit.DIAMONDS,Rank.KING)
        roundData.addCard(card)
        assertTrue(roundData.hand[Utils.cardToInt(card)]==1)

    }

}