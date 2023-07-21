package com.example.barbu

import com.example.barbu.player.Player
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Position
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Rank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test


class TrickTest {
    private lateinit var trick: Trick
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var player4: Player

    @Before
    fun setUp() {
        trick = Trick()
        player1 = Player("j1", Position.SOUTH)
        player2 = Player("j2", Position.WEST)
        player3 = Player("j3", Position.NORTH)
        player4 = Player("j4", Position.EAST)
    }

    @Test
    fun testIsEmpty() {
        // Vérifier que le Trick est vide au départ
        assertEquals(true, trick.isEmpty())

        // Ajouter une carte
        trick.playCard(Card(Suit.DIAMONDS, Rank.ACE), player1)

        // Vérifier que le Trick n'est plus vide
        assertEquals(false, trick.isEmpty())
    }

    @Test
    fun testIsOver() {
        // Vérifier que le Trick n'est pas terminé au départ
        assertEquals(false, trick.isOver())

        // Ajouter 4 cartes
        trick.playCard(Card(Suit.DIAMONDS, Rank.ACE), player1)
        print("taillle 1 $trick")
        assertEquals(false, trick.isOver())
        trick.playCard(Card(Suit.HEARTS, Rank.KING), player2)
        print("taillle 2 $trick")
        assertEquals(false, trick.isOver())
        trick.playCard(Card(Suit.SPADES, Rank.QUEEN), player3)
        print("taillle 3 $trick")
        assertEquals(false, trick.isOver())
        trick.playCard(Card(Suit.CLUBS, Rank.JACK), player4)
        print("taillle 4 $trick")
        // Vérifier que le Trick est terminé
        assertEquals(true, trick.isOver())
        trick.pickup()
        print("taillle 0 $trick")
        assertEquals(false, trick.isOver())
    }

    @Test
    fun testRequiredSuit() {
        // Ajouter une carte au Trick
        trick.playCard(Card(Suit.DIAMONDS, Rank.ACE), player1)

        // Vérifier que la couleur requise est celle de la première carte
        assertEquals(Suit.DIAMONDS, trick.requiredSuit())
        trick.playCard(Card(Suit.HEARTS, Rank.ACE), player1)
        assertEquals(Suit.DIAMONDS, trick.requiredSuit())
    }

    @Test
    fun testPlayCard() {
        val card1 = Card(Suit.DIAMONDS, Rank.TEN)
        val card2 = Card(Suit.HEARTS, Rank.KING)
        val card3 = Card(Suit.DIAMONDS, Rank.ACE)

        // Jouer la première carte
        trick.playCard(card1, player1)

        // Vérifier que la première carte et le joueur gagnant sont définis
        assertEquals(card1, trick.southCard)
        assertEquals(player1, trick.firstPlayer)

        assertEquals(card1, trick.winCard)
        assertEquals(player1, trick.winPlayer)

        // Jouer une deuxième carte de même couleur mais de rang inférieur
        trick.playCard(card2, player2)

        // Vérifier que la deuxième carte devient la plus forte et que le joueur gagnant est mis à jour
        assertEquals(card1, trick.winCard)
        assertEquals(player1, trick.winPlayer)

        // Jouer une troisième carte de même couleur mais de rang supérieur
        trick.playCard(card3, player3)

        // Vérifier que la deuxième carte devient la plus forte et que le joueur gagnant est mis à jour
        assertEquals(card3, trick.winCard)
        assertEquals(player3, trick.winPlayer)
        assertEquals(player1, trick.firstPlayer)
    }

    @Test
    fun testPickup() {
        val card1 = Card(Suit.DIAMONDS, Rank.TEN)
        val card2 = Card(Suit.HEARTS, Rank.KING)
        val card3 = Card(Suit.DIAMONDS, Rank.ACE)
        val card4 = Card(Suit.CLUBS, Rank.JACK)

        // Jouer 4 cartes et effectuer le ramassage
        trick.playCard(card1, player1)
        trick.playCard(card2, player2)
        trick.playCard(card3, player3)
        trick.playCard(card4, player4)
        trick.pickup()

        // Vérifier que les cartes gagnées ont été ajoutées aux joueurs et que le Trick est réinitialisé
        assertEquals(0, player1.winCards.size)
        assertEquals(0, player2.winCards.size)
        assertEquals(4, player3.winCards.size)
        assertEquals(0, player4.winCards.size)
        assertNull(trick.southCard)
        assertNull(trick.westCard)
        assertNull(trick.northCard)
        assertNull(trick.eastCard)
        assertEquals(0, trick.nbCard)
    }
}