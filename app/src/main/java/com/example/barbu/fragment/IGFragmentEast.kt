package com.example.barbu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barbu.R

/**
 * A simple [Fragment] subclass.
 * Use the [IGFragmentEast.newInstance] factory method to
 * create an instance of this fragment.
 */
class IGFragmentEast() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                return(inflater.inflate(R.layout.east_ig, container, false))
            }

}