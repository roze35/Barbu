package com.example.barbu


import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Rank
import com.example.barbu.utils.Suit
import org.junit.Assert.assertEquals
import org.junit.Test



class PlayerTest {
    private var player: Player=Player("Lolo",Position.SOUTH)
/*
    @Before
    fun setUp() {
        player = Mockito.mock(Player::class.java)
    }
*/

    fun add4CArds(){
        player.hand.add(Card(Suit.DIAMONDS, Rank.ACE))
        player.hand.add(Card(Suit.HEARTS,Rank.SEVEN))
        player.hand.add(Card(Suit.HEARTS,Rank.QUEEN))
        player.hand.add(Card(Suit.DIAMONDS,Rank.NINE))
        player.winCards.add(Card(Suit.CLUBS,Rank.NINE))
    }

    @Test
    fun testReInit() {
        // Ajouter des cartes à la main et aux cartes gagnées
        add4CArds()

        // Appeler la méthode reInit()
        player.reInit()

        // Vérifier que la main et les cartes gagnées sont vides
        assertEquals(0, player.hand.size)
        assertEquals(0, player.winCards.size)
    }

    @Test
    fun testRemoveCard() {
        // Ajouter 4 cartes à la main
        add4CArds()
        assertEquals(4, player.hand.size)

        //Retirer l'ace de carreau
        val cardToRemove = Card(Suit.DIAMONDS,Rank.ACE)
        player.removeCard(cardToRemove)

        // Vérifier que la carte a été supprimée de la main
        assertEquals(3, player.hand.size)
    }

    @Test
    fun testAddCard() {
        val cardToAdd = Card(Suit.SPADES,Rank.JACK)

        // Appeler la méthode addCard() avec une carte à ajouter
        player.addCard(cardToAdd)

        // Vérifier que la carte a été ajoutée à la main
        assertEquals(1, player.hand.size)
        assertEquals(cardToAdd, player.hand.first())

        val cardToAdd2 = Card(Suit.CLUBS,Rank.JACK)
        player.addCard(cardToAdd2)
        assertEquals(2, player.hand.size)
    }

    @Test
    fun testPlayRandomCard() {

        val player1=Player("Lolo",Position.SOUTH)
        player1.addCard(Card(Suit.HEARTS,Rank.ACE))
        player1.addCard(Card(Suit.HEARTS,Rank.KING))
        player1.addCard(Card(Suit.HEARTS,Rank.QUEEN))

        Referee.trick.playCard(Card(Suit.HEARTS,Rank.SEVEN),player)

        val possibleCards=Referee.possibleCards(player1.hand)

        // Appeler la méthode playRandomCard() avec l'ensemble de cartes possibles
        val playedCard = player.playRandomCard(possibleCards)

        // Vérifier que la carte jouée est présente dans l'ensemble des cartes possibles
        assertEquals(3, possibleCards.size)
        assertEquals(true, possibleCards.contains(playedCard))

        val player2=Player("j2",Position.WEST)
        player2.addCard(Card(Suit.DIAMONDS,Rank.ACE))
        player2.addCard(Card(Suit.DIAMONDS,Rank.KING))
        player2.addCard(Card(Suit.HEARTS,Rank.QUEEN))
        val possibleCards2=Referee.possibleCards(player2.hand)
        println(possibleCards2.toString())
        assertEquals(1, possibleCards2.size)
        val playedCard2 = player2.playRandomCard(possibleCards2)
        assertEquals(true, possibleCards.contains(playedCard2))
        assertEquals(Card(Suit.HEARTS,Rank.QUEEN), possibleCards2.elementAt(0))

        val player3=Player("j3",Position.NORTH)
        player3.addCard(Card(Suit.DIAMONDS,Rank.ACE))
        player3.addCard(Card(Suit.DIAMONDS,Rank.KING))
        player3.addCard(Card(Suit.DIAMONDS,Rank.QUEEN))
        val possibleCards3=Referee.possibleCards(player3.hand)
        println("troisieme test ${possibleCards3}")
        assertEquals(3, possibleCards3.size)

    }

}