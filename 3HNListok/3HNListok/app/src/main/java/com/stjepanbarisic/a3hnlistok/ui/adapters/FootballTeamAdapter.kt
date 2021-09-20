package com.stjepanbarisic.a3hnlistok.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stjepanbarisic.a3hnlistok.data.models.wrappers.FootballTeamListItem
import com.stjepanbarisic.a3hnlistok.databinding.ItemFootballTeamBinding

class FootballTeamAdapter(private var footballTeams: List<FootballTeamListItem>) :
    RecyclerView.Adapter<FootballViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballViewHolder {
        val binding = ItemFootballTeamBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FootballViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        holder.bind(footballTeams[position], position)
    }

    override fun getItemCount(): Int {
        return footballTeams.size
    }

    fun loadNewTeams(newFootballTeams: List<FootballTeamListItem>) {
        footballTeams = newFootballTeams
        notifyDataSetChanged()
    }

}


class FootballViewHolder(private val binding: ItemFootballTeamBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: FootballTeamListItem, position: Int) {
        with(binding) {
            if (model.id != -1L) { // header
                ivLogo.setImageResource(model.image)
                tvIndex.text = position.toString()
            } else {
                ivLogo.setImageDrawable(null)
                tvIndex.text = ""
            }
            footballTeam = model
            executePendingBindings()
        }
    }

}