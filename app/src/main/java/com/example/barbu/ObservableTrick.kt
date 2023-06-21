package com.example.barbu

interface ObservableTrick {
    fun addObserverTrick(o:ObserverTrick)
    fun removeObseverTrick(o:ObserverTrick)
    fun notifyObserverTrick()
}