package com.example.barbu

class GraphicalPlayer(name:String, pos:Position):Player(name,pos) {
    override fun useInterface():Boolean{
        return true
    }

}