package com.example.myapplication.ui.soccerAdapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entity.Player
import com.example.myapplication.databinding.PlayerItemBinding
import com.squareup.picasso.Picasso


class SoccerPlayersAdapter(private var players: List<Player> = listOf(), val clicked: (Player) -> Unit) :
    RecyclerView.Adapter<SoccerPlayersAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(
            PlayerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    @SuppressLint("NotifyDataSetChanged")
    //updating the players list thats displaying on the screen
    fun update(players:List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }


    override fun getItemCount() = players.size


    override fun onBindViewHolder(holder: VH, position: Int) {
        val player = players[position]

        with(holder.binding) {
//filtering the display so only soccer players would be on the screen
            if(player.sportType.lowercase()=="soccer"
                &&player.team!="null"
                &&player.fullName!="null"){
                team.text = player.team
                fullName.text = player.fullName
                Picasso.get().load(player.picture).into(pictureView)

                root.setOnClickListener {
                    clicked(player)
                }
            }

        }

    }

    class VH(val binding: PlayerItemBinding) : RecyclerView.ViewHolder(binding.root)
}