package com.example.barbu

import com.example.barbu.cardGame.Card

interface ObserverTrick {
    fun followingPlayer()
    fun endTrick(winPlayer:Player,winCards:MutableSet<Card>)
    fun newTrick()
}