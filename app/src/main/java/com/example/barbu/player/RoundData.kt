package com.example.barbu.player


import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Utils



data class RoundData(val myNum:Int){

    var numTrick: Int =1
    var hand:IntArray=IntArray(32){-1}
    var output: IntArray=IntArray(32){0}
    var playedCard:Int=-1

    constructor(roundData: RoundData) : this(roundData.myNum) {
        this.numTrick = roundData.numTrick+1
        this.hand = roundData.hand.copyOf()
        this.output = IntArray(32){0}
        hand[roundData.playedCard]=-1
        this.playedCard=-1
    }


    fun addCard(card: Card){
        val code = Utils.cardToInt(card)
        hand[code] = 1
    }

    fun playCard(player: Player, card: Card){
        if (Utils.positionToInt(player.position)==myNum) {
            val code = Utils.cardToInt(card)
            playedCard=code
            output[code]= 1
        }else{

        }
    }

    override fun toString():String{
        var res=""
        res+="numTrick $numTrick \n"
        res+="hand "+ hand.joinToString(",")+"\n"
        res+="output "+output.joinToString(",")+"\n"
        res+="carte jouee $playedCard \n"
        return res
    }

    fun toCsv():String{
        var res="$numTrick,"
        res+=hand.joinToString(",")+","
        res+=output.joinToString(",")
        return res+"\n"
    }

    fun newTrick(){
        this.numTrick++
    }

    fun finalScore(score:Int){
        for(i in output.indices){
            if (output[i]==1){
                output[i]=score
            }
        }
    }


}