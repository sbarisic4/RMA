package com.stjepanbarisic.a3hnlistok.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameWithFootballTeams
import com.stjepanbarisic.a3hnlistok.databinding.FragmentScheduleBinding
import com.stjepanbarisic.a3hnlistok.ui.activities.MapsActivity
import com.stjepanbarisic.a3hnlistok.ui.adapters.GameAdapter
import com.stjepanbarisic.a3hnlistok.ui.dialogs.CHANGE_GAME_RESULT_DIALOG_GAME
import com.stjepanbarisic.a3hnlistok.ui.dialogs.CHANGE_GAME_RESULT_DIALOG_ID
import com.stjepanbarisic.a3hnlistok.ui.dialogs.ChangeGameResultDialog
import com.stjepanbarisic.a3hnlistok.viewmodels.GamesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


private const val DIALOG_CHANGE_GAME_RESULT = 1

internal const val BUNDLE_KEY_HOST_FOOTBALL_TEAM = "host_football_team"

class ScheduleFragment : Fragment(), GameAdapter.OnGameClickListener,
    ChangeGameResultDialog.ChangeGameDialogEvents {

    private val gamesViewModel: GamesViewModel by viewModel()
    private val gameAdapter = GameAdapter(emptyList(), this)
    private lateinit var homeFragmentBinding: FragmentScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding = FragmentScheduleBinding.inflate(inflater, container, false)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentBinding.rvGames.apply {
            adapter = gameAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gamesViewModel.gamesWithFootballTeams.observe(this, { list ->
            if (!list.isNullOrEmpty()) {
                gameAdapter.loadNewGames(list)
            }
        })
    }

    override fun onFinishClick(gameWithFootballTeams: GameWithFootballTeams) {
        if (!gameWithFootballTeams.game.isFinished) {
            val dialog = ChangeGameResultDialog()
            val args = Bundle().apply {
                putInt(CHANGE_GAME_RESULT_DIALOG_ID, DIALOG_CHANGE_GAME_RESULT)
                putParcelable(CHANGE_GAME_RESULT_DIALOG_GAME, gameWithFootballTeams)
            }
            dialog.arguments = args
            dialog.show(childFragmentManager, "changeGameResult")
        }

    }

    override fun onLocationClick(hostFootballTeam: FootballTeam) {
        startMapActivity(bundleOf(BUNDLE_KEY_HOST_FOOTBALL_TEAM to hostFootballTeam))
    }

    private fun startMapActivity(args: Bundle) {
        val intent = Intent(requireContext(), MapsActivity::class.java)
        intent.putExtras(args)
        startActivity(intent)
    }

    override fun onEditGameDialogResult(dialogId: Int, args: Bundle) {
        if (dialogId == DIALOG_CHANGE_GAME_RESULT) {
            val changedGame =
                args.getParcelable(CHANGE_GAME_RESULT_DIALOG_GAME) as GameWithFootballTeams?
            changedGame?.let { gamesViewModel.finishGame(changedGame) }
        }
    }
}
