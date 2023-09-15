package com.example.myapplication.ui.soccerPlayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentsoccerplayersBinding
import com.example.myapplication.ui.soccerAdapter.SoccerPlayersAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SoccerPlayersFragment : Fragment() {

    private var _binding:FragmentsoccerplayersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val soccerPlayersViewModel =
            ViewModelProvider(this)[SoccerPlayersViewModel::class.java]

        _binding = FragmentsoccerplayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchtextsoccer.addTextChangedListener {

            soccerPlayersViewModel.filterBy(it.toString())



        }
        //go back to main menu
        binding.backPlayers.setOnClickListener{
            findNavController().navigate(
                R.id.action_soccerPlayersFragment_to_homeFragment
                           )
        }
        //pass me with the relevant player's details to playerDetails fragment
        val soccerPlayersAdapter = SoccerPlayersAdapter { player ->
            findNavController().navigate(
                R.id.action_soccerPlayersFragment_to_playerDetailsFragment,
                Bundle().apply { putParcelable("player", player);putString("SoccerPlayers","Soccerfragment") }
            )
        }
        //linking the recycler view and choosing display type(linear)
        with(binding.recyclerSoccerAdapter) {
            layoutManager = LinearLayoutManager(context)
            adapter = soccerPlayersAdapter
        }
//listening to changes in the recyclers list after searching a specific string and update the players list
        soccerPlayersViewModel.players.observe(viewLifecycleOwner) {
            soccerPlayersAdapter.update(it)
            println(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}