package com.example.barbu

interface ObservableTrick {
    fun addObserver(observer: ObserverTrick)
    fun removeObserver(observer:ObserverTrick)
    fun notifyFollowingPlayer()
    fun notifyEndTrick()
    fun notifyNewTrick()
}