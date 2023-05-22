package com.example.barbu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barbu.R

/**
 * A simple [Fragment] subclass.
 * Use the [IGFragmentNorth.newInstance] factory method to
 * create an instance of this fragment.
 */
class IGFragmentNorth() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position=arguments?.getSerializable("position")
        // Inflate the layout for this fragment
        return(inflater.inflate(R.layout.north_ig, container, false))
    }


}