package com.example.myapplication.trivia

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entity.Player
import com.example.myapplication.databinding.FragmentFailedBinding
//this fragment display failing when the user has a mistake in one of the questions
class FailedFragment: Fragment() {
    private var _binding: FragmentFailedBinding? = null

    //use after init:
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // gets the player for its details
        val player = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("player", Player::class.java)
        } else {
            arguments?.getParcelable("player") as Player?
        }

        //gets the number of question so it could be provide back to the triviafragment so the user could have another chance.
        val num=arguments?.getInt("number of question")!!.toInt()
        if (player != null) {
            println(player.fullName)
        }
        //NAVIGATE back to trivia fragment with the current question number.
        binding.tryAgain.setOnClickListener {
            findNavController().navigate(
                R.id.action_failedFragment_to_triviaFragment,
                Bundle().apply { putParcelable("player", player);putInt("number of question",num) })


        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}