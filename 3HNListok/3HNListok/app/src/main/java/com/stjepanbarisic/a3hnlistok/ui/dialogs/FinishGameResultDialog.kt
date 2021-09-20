package com.stjepanbarisic.a3hnlistok.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import com.stjepanbarisic.a3hnlistok.R
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameWithFootballTeams
import com.stjepanbarisic.a3hnlistok.databinding.DialogChangeGameResultBinding

const val CHANGE_GAME_RESULT_DIALOG_ID = "ID"
const val CHANGE_GAME_RESULT_DIALOG_GAME = "GAME"

class ChangeGameResultDialog : AppCompatDialogFragment() {

    interface ChangeGameDialogEvents {
        fun onEditGameDialogResult(dialogId: Int, args: Bundle)
    }

    private var listener: ChangeGameDialogEvents? = null
    private lateinit var dialogChangeGameResultBinding: DialogChangeGameResultBinding

    private val gameWithFootballTeams
        get() = arguments?.getParcelable<GameWithFootballTeams>(
            CHANGE_GAME_RESULT_DIALOG_GAME
        )

    private val dialogId
        get() = arguments?.getInt(CHANGE_GAME_RESULT_DIALOG_ID) ?: 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogChangeGameResultBinding =
            DialogChangeGameResultBinding.inflate(inflater, container, false)

        dialogChangeGameResultBinding.apply {
            val gameId = gameWithFootballTeams?.game?.gameId
            val hostTeam = gameWithFootballTeams?.getHostFootballTeam()
            val guestTeam = gameWithFootballTeams?.getGuestFootballTeam()
            tvHostTeamName.text = hostTeam?.name
            tvGuestTeamName.text = guestTeam?.name
            etHostGoals.setText(hostTeam?.getGameGoals(gameId ?: 0)?.value.toString())
            etGuestGoals.setText(guestTeam?.getGameGoals(gameId ?: 0)?.value.toString())
        }

        return dialogChangeGameResultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle(R.string.change_game_result)

        with(dialogChangeGameResultBinding) {

            btnUpdateGame.setOnClickListener {
                if (validateGameInput()) {
                    val gameWithFootballTeamsModified = gameWithFootballTeams?.let { game ->
                        addGoalsAndDecideWinner(
                            game
                        )
                    }
                    listener?.onEditGameDialogResult(
                        dialogId,
                        bundleOf(CHANGE_GAME_RESULT_DIALOG_GAME to gameWithFootballTeamsModified)
                    )

                    dismiss()
                }
            }

            btnCancel.setOnClickListener { dismiss() }
        }
    }


    private fun addGoalsAndDecideWinner(gameWithFootballTeams: GameWithFootballTeams): GameWithFootballTeams {
        val gameId = gameWithFootballTeams.game.gameId
        val hostTeam = gameWithFootballTeams.getHostFootballTeam()
        val guestTeam = gameWithFootballTeams.getGuestFootballTeam()

        val newHostGoals = dialogChangeGameResultBinding.etHostGoals.text.toString().toInt()
        hostTeam.getGameGoals(gameId)?.value = newHostGoals

        val newGuestGoals = dialogChangeGameResultBinding.etGuestGoals.text.toString().toInt()
        guestTeam.getGameGoals(gameId)?.value = newGuestGoals

        when {
            newHostGoals > newGuestGoals -> {
                hostTeam.wins += 1
                guestTeam.losses += 1
            }
            newHostGoals < newGuestGoals -> {
                guestTeam.wins += 1
                hostTeam.losses += 1
            }
            else -> {
                guestTeam.draw += 1
                hostTeam.draw += 1
            }
        }

        gameWithFootballTeams.game.isFinished = true

        return gameWithFootballTeams
    }


    private fun validateGameInput(): Boolean {

        var isValid = true

        dialogChangeGameResultBinding.etHostGoals.apply {
            if (text?.isEmpty() == true) {
                error = getString(R.string.valid_goals_required)
                requestFocus()
                isValid = false
            }
        }

        dialogChangeGameResultBinding.etGuestGoals.apply {
            if (text?.isEmpty() == true) {
                error = getString(R.string.valid_goals_required)
                requestFocus()
                isValid = false
            }
        }

        return isValid
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = try {
            if (parentFragment == null) {
                throw TypeCastException()
            } else {
                parentFragment as ChangeGameDialogEvents
            }

        } catch (e: TypeCastException) {
            try {
                context as ChangeGameDialogEvents
            } catch (e: ClassCastException) {
                throw ClassCastException("Activity $context must implement ChangeGameDialogEvents interface")
            }
        } catch (e: ClassCastException) {
            throw ClassCastException("Fragment $parentFragment must implement ChangeGameDialogEvents interface")
        }
    }

}