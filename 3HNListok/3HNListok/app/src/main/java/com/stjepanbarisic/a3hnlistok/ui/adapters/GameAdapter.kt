package com.stjepanbarisic.a3hnlistok.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameWithFootballTeams
import com.stjepanbarisic.a3hnlistok.databinding.ItemGameBinding
import java.text.SimpleDateFormat

class GameAdapter(
    private var gamesWithFootballTeams: List<GameWithFootballTeams>,
    private val listener: OnGameClickListener
) :
    RecyclerView.Adapter<GameViewHolder>() {

    interface OnGameClickListener {
        fun onFinishClick(gameWithFootballTeams: GameWithFootballTeams)
        fun onLocationClick(hostFootballTeam: FootballTeam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(gamesWithFootballTeams[position], listener)
    }

    override fun getItemCount(): Int {
        return gamesWithFootballTeams.size
    }

    fun loadNewGames(newGames: List<GameWithFootballTeams>) {
        gamesWithFootballTeams = newGames
        notifyDataSetChanged()
    }

}


class GameViewHolder(private val binding: ItemGameBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        gameWithFootballTeams: GameWithFootballTeams,
        listener: GameAdapter.OnGameClickListener
    ) {
        with(binding) {
            val gameId = gameWithFootballTeams.game.gameId
            tvHostTeamGoals.text =
                gameWithFootballTeams.getHostFootballTeam().getGameGoals(gameId)?.value.toString()

            tvGuestTeamGoals.text =
                gameWithFootballTeams.getGuestFootballTeam().getGameGoals(gameId)?.value.toString()

            tvStartTime.text =
                SimpleDateFormat("dd/MM/yyyy HH:mm").format(gameWithFootballTeams.game.startTime)

            btnFinishGame.isEnabled = !gameWithFootballTeams.game.isFinished

            game = gameWithFootballTeams
            executePendingBindings()

            btnFinishGame.setOnClickListener {
                listener.onFinishClick(gameWithFootballTeams)
            }

            btnLocationOfGame.setOnClickListener {
                listener.onLocationClick(gameWithFootballTeams.getHostFootballTeam())
            }
        }
    }

}