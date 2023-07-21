package com.example.barbu

import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Rank
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {
    @Test
    fun testCardToInt(){
        val card1= Card(Suit.DIAMONDS,Rank.ACE)
        val code1 = Utils.cardToInt(card1)
        assertEquals(code1,15)
        val card2= Card(Suit.HEARTS,Rank.QUEEN)
        val code2 = Utils.cardToInt(card2)
        assertEquals(code2,5)
        val card3= Card(Suit.HEARTS,Rank.SEVEN)
        val code3 = Utils.cardToInt(card3)
        assertEquals(code3,0)
    }
}