package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //navigate to playersFragment
        binding.playersCategory.setOnClickListener{
        findNavController().navigate(R.id.action_homeFragment_to_playersFragment)
        }
        //navigate to soccerPlayersFragment
        binding.soccer.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_soccerPlayersFragment)
        }


        return root
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}