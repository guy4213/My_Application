package com.example.myapplication.ui.playerDetails



import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entity.Player
import com.example.myapplication.databinding.FragmentPlayerDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class PlayerDetailsFragment : Fragment() {

    private var _binding: FragmentPlayerDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)



        return binding.root
    }
    //provides us a string to seperate between soccer fragment and players fragment
    private fun getPreviousClass():String?{
        return arguments?.getString("PlayersFragment")?:arguments?.getString("SoccerPlayers")
        }

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val player = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getParcelable("player", Player::class.java)
    } else {
        arguments?.getParcelable("player") as Player?
    }
    //displaying name,description and photo in the fragment
    if (player != null) {

        binding.playerFullname.text = player.fullName

        binding.Description.text = player.englishDescription

        Picasso.get().load(player.picture).into(binding.playerPicture)
        println(player.picture)
    }
    //navigate to trivia fragment with the specific player's details
    binding.goToTrivia.setOnClickListener{
      findNavController().navigate(
            R.id.action_playerDetailsFragment_to_triviaFragment,
            Bundle().apply { putParcelable("player", player) })
    }
    //navigate back to the relevant fragment with the help of method getPreviousClass() .
    binding.back.setOnClickListener {
        if (getPreviousClass() == "playersfragment") {
            findNavController().navigate(
                R.id.action_playerDetailsFragment_to_playersFragment
            )
        }
        else {  findNavController().navigate(
            R.id.action_playerDetailsFragment_to_soccerPlayersFragment2
        )
        }
    }

}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}