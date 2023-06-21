package com.example.barbu.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.barbu.R
import com.example.barbu.Referee



class TrickAdapter : Adapter<TrickAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val cardView:ImageView=itemView.findViewById(R.id.ivCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("affichage","appel de onCreateViwHolder de TrickAdapter")
        val inflater= LayoutInflater.from(parent.context)
        val cardView=inflater.inflate(R.layout.card_view,parent,false)
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        //Log.d("affichage","getItemCount de TrickAdapter "+Referee.trick.size())
        return Referee.trick.size()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Log.d("affichage","appel de onBindViewHolfrt de TrickAdapter")
        val card = Referee.trick.cards.toList()[position]
        val cardString =
            card.rank.toString().lowercase() + "_of_" + card.suit.toString().lowercase()
        val context = holder.itemView.context
        val imageCardId =
            context.resources.getIdentifier(cardString, "drawable", context.packageName)
        holder.cardView.setImageResource(imageCardId)
    }


}