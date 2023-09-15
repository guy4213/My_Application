package com.example.myapplication.ui.player

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
import com.example.myapplication.databinding.FragmentPlayersBinding
import com.example.myapplication.ui.adapter.PlayersAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class PlayersFragment : Fragment() {

    private var _binding:FragmentPlayersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val playerViewModel =
            ViewModelProvider(this)[PlayerViewModel::class.java]

        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //provide the text typing by user immediate to a function to filter the recycler view players list
        binding.serchtextview.addTextChangedListener {

            playerViewModel.filterBy(it.toString())



        }
        //go back to main menu
        binding.backPlayers.setOnClickListener{
            findNavController().navigate(
                R.id.action_playersFragment_to_homeFragment
            )
        }
        //pass me with the relevant player's details to playerDetails fragment and navigate to it
        val playersAdapter = PlayersAdapter { player ->
            findNavController().navigate(
                R.id.action_playersFragment_to_playerDetailsFragment,
                Bundle().apply { putParcelable("player", player);putString("PlayersFragment","playersfragment") }
            )
        }
        //linking the recycler view and choosing display type(linear)
        with(binding.recyclerPlayers) {
            layoutManager = LinearLayoutManager(context)
            adapter = playersAdapter
        }

//listening to changes in the recyclers list after searching a specific string and update the players list
        playerViewModel.players.observe(viewLifecycleOwner) {
            playersAdapter.update(it)
            println(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

