package com.example.barbu.player

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GameData {
    var rounds= arrayListOf<RoundData>()
    var nbRound=0
    fun addRoundData(roundData:RoundData){
        rounds.add(roundData)
        nbRound++
    }

    fun finalScore(score:Int){
        //Log.d("affichage","mise a jour des scores")
        for (roundData in rounds){
            //Log.d("affichage","mise a jour d'un pli")
            roundData.finalScore(score)
        }
    }

    override fun toString():String{
        return rounds.joinToString("\nPli\n")
    }
    fun toCsv():String{
        var res=""
        for (roundData in rounds){
            res+= roundData.toCsv()
        }
        return res
    }

    fun toJson():String{
        var res="Json "
        for (roundData in rounds){
            res+= Json.encodeToString(roundData)
            res+="\n"
        }
        return res
    }
}