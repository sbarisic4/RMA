package com.stjepanbarisic.a3hnlistok.ui.activities

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.stjepanbarisic.a3hnlistok.R
import com.stjepanbarisic.a3hnlistok.data.models.persistence.Game
import com.stjepanbarisic.a3hnlistok.databinding.ActivityAddGameBinding
import com.stjepanbarisic.a3hnlistok.ui.dialogs.DATE_SELECTOR_DATE
import com.stjepanbarisic.a3hnlistok.ui.dialogs.DATE_SELECTOR_ID
import com.stjepanbarisic.a3hnlistok.ui.dialogs.DATE_SELECTOR_TITLE
import com.stjepanbarisic.a3hnlistok.ui.dialogs.DateSelectorFragment
import com.stjepanbarisic.a3hnlistok.viewmodels.GamesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


private const val DIALOG_GAME_START_DATE = 1

private const val SELECTED_DATE = "SelectedDate"

class AddGameActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityAddGameBinding
    private val gamesViewModel: GamesViewModel by viewModel()

    private var currentDate = GregorianCalendar(Locale.getDefault()).timeInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityAddGameBinding>(this, R.layout.activity_add_game)
                .apply {
                    viewModel = gamesViewModel
                    lifecycleOwner = this@AddGameActivity
                    executePendingBindings()
                }

        gamesViewModel.footballTeamsNames.observe(this, { list ->
            if (!list.isNullOrEmpty()) {
                setFootballNamesSpinners(list)
            }
        })

        binding.etDate.setOnClickListener {
            showDateSelector(getString(R.string.pick_game_date), DIALOG_GAME_START_DATE)
        }

        binding.btnAddGame.setOnClickListener {
            if (validateGameInput()) {
                with(binding) {
                    val spFirstTeamValue = spFirstTeam.selectedItem.toString()
                    val spSecondTeamValue = spSecondTeam.selectedItem.toString()
                    val matchStartTime = getTimeOfDayInMillis(
                        currentDate,
                        etHour.text.toString().toInt(),
                        etMinute.text.toString().toInt()
                    )
                    val game = Game(
                        startTime = matchStartTime
                    )

                    CoroutineScope(Main).launch {
                        gamesViewModel.createGame(spFirstTeamValue, spSecondTeamValue, game)
                        finish()
                    }
                }
            }
        }

        if (savedInstanceState != null) {
            currentDate = savedInstanceState.getLong(SELECTED_DATE)
        }

        binding.etDate.setText(getLocalFormattedDate(currentDate, this))
    }


    private fun showDateSelector(title: String, dialogId: Int) {

        val dateSelectorFragment = DateSelectorFragment()

        val date = Date()
        date.time = currentDate

        val arguments = bundleOf(
            DATE_SELECTOR_ID to dialogId,
            DATE_SELECTOR_TITLE to title,
            DATE_SELECTOR_DATE to date
        )

        dateSelectorFragment.arguments = arguments
        dateSelectorFragment.show(supportFragmentManager, "datePicker")
    }

    private fun setFootballNamesSpinners(teamNames: List<String>) {
        val teamNamesAdapter = ArrayAdapter(this, R.layout.spinner_item, teamNames)
        teamNamesAdapter.setDropDownViewResource(R.layout.spinner_item)
        binding.apply {
            spFirstTeam.adapter = teamNamesAdapter
            spSecondTeam.adapter = teamNamesAdapter
        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        when (view?.tag as Int) {
            DIALOG_GAME_START_DATE -> {
                val calendar = GregorianCalendar()
                calendar.set(year, month, dayOfMonth, 0, 0, 0)
                currentDate = calendar.timeInMillis
                currentDate.let {
                    binding.etDate.setText(
                        getLocalFormattedDate(
                            it,
                            this@AddGameActivity
                        )
                    )
                }
            }

            else -> throw IllegalArgumentException("Invalid mode when receiving DateSelectorDialog result")
        }
    }

    private fun getLocalFormattedDate(dateInMillis: Long, context: Context): String {
        val dateFormat = DateFormat.getDateFormat(context)
        return dateFormat.format(dateInMillis)
    }

    private fun getTimeOfDayInMillis(dayInMillis: Long, hour: Int, minute: Int): Long {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = dayInMillis
        calendar.set(GregorianCalendar.HOUR_OF_DAY, hour)
        calendar.set(GregorianCalendar.MINUTE, minute)
        return calendar.timeInMillis

    }

    private fun validateGameInput(): Boolean {

        var isValid = true

        if(binding.spFirstTeam.selectedItem == binding.spSecondTeam.selectedItem) {
            isValid = false
            Toast.makeText(this, getString(R.string.pick_valid_teams), Toast.LENGTH_SHORT).show()
        }

        binding.etHour.apply {
            if (text?.isEmpty() == true || text.toString().toInt() > 24) {
                error = getString(R.string.valid_hour_required)
                requestFocus()
                isValid = false
            }
        }

        binding.etMinute.apply {
            if (text?.isEmpty() == true || text.toString().toInt() > 60) {
                error = getString(R.string.valid_minute_required)
                requestFocus()
                isValid = false
            }
        }

        return isValid
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentDate.let { outState.putLong(SELECTED_DATE, it) }
    }

}