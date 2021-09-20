package com.stjepanbarisic.a3hnlistok.repositories

import com.stjepanbarisic.a3hnlistok.R
import com.stjepanbarisic.a3hnlistok.data.database.storage.FootballTeamStorage
import com.stjepanbarisic.a3hnlistok.data.models.Location
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam
import com.stjepanbarisic.a3hnlistok.data.models.wrappers.FootballTeamListItem

class FootballTeamRepository(
    private val footballTeamStorage: FootballTeamStorage
) {

    fun getFootballTeamsLiveData() = footballTeamStorage.getFootballTeamsLiveData()

    suspend fun getAllFootballTeamsAsynchronous(): List<FootballTeam>? {
        return footballTeamStorage.getAllFootballTeamsAsynchronous()
    }

    suspend fun insertFootballTeam(footballTeam: FootballTeam) {
        footballTeamStorage.insertFootballTeam(footballTeam)
    }

    suspend fun updateFootballTeam(footballTeam: FootballTeam) {
        footballTeamStorage.updateFootballTeam(footballTeam)
    }

    suspend fun insertFootballTeams(footballTeams: List<FootballTeam>) {
        footballTeamStorage.insertFootballTeams(footballTeams)
    }


    companion object {

        private val slogaNovaGradiska = FootballTeam(
            name = "Sloga Nova Gradiška",
            image = R.drawable.sloga_nova_gradiska,
            location = Location(45.2639466154947, 17.37713276562144)
        )

        private val oriolik = FootballTeam(
            name = "Oriolik",
            image = R.drawable.oriolik,
            location = Location(45.16355395704699, 17.748903255820892)
        )

        private val NASK = FootballTeam(
            name = "NAŠK",
            image = R.drawable.nask,
            location = Location(45.48585685948307, 18.090630969324465)
        )

        private val granicarZupanja = FootballTeam(
            name = "Graničar Županja",
            image = R.drawable.granicar_zupanja,
            location = Location(45.07652003473748, 18.692456960362104)
        )

        private val slavonija = FootballTeam(
            name = "Slavonija",
            image = R.drawable.slavonija,
            location = Location(45.33682646493307, 17.672993201349414)
        )

        private val cepin = FootballTeam(
            name = "Čepin",
            image = R.drawable.cepin,
            location = Location(45.52889890079592, 18.563480769325768)
        )

        private val kutjevo = FootballTeam(
            name = "Kutjevo",
            image = R.drawable.kutjevo,
            location = Location(45.416688207154415, 17.880745326993114)
        )

        private val vuteksSloga = FootballTeam(
            name = "Vuteks Sloga",
            image = R.drawable.vuteks_sloga,
            location = Location(45.34225502180561, 19.00219705582628)
        )

        private val bedem = FootballTeam(
            name = "Bedem",
            image = R.drawable.bedem,
            location = Location(45.28426431218714, 18.691623613495224)
        )

        private val darda = FootballTeam(
            name = "Darda",
            image = R.drawable.darda,
            location = Location(45.62327884836337, 18.68564885583481)
        )

        private val marsonia = FootballTeam(
            name = "Marsonia",
            image = R.drawable.marsonia,
            location = Location(45.150483210350195, 18.021674471162058)
        )

        private val slavijaPleternica = FootballTeam(
            name = "Slavija Pleternica",
            image = R.drawable.slavija_pleternica,
            location = Location(45.28878319306123, 17.803203903697096)
        )

        private val slavonacBukovlje = FootballTeam(
            name = "Slavonac Bukovlje",
            image = R.drawable.slavonac_bukovlje,
            location = Location(45.18995344731502, 18.061880938590555)
        )

        private val omladinacGornjaVrba = FootballTeam(
            name = "Omladinac Gornja Vrba",
            image = R.drawable.omladinac_gornja_vrba,
            location = Location(45.15246312220827, 18.063514371124473)
        )

        private val belisce = FootballTeam(
            name = "Belišće",
            image = R.drawable.belisce,
            location = Location(45.68598099490515, 18.40882386274242)
        )

        private val zrinskiJurjevac = FootballTeam(
            name = "Zrinski Jurjevac",
            image = R.drawable.zrinski_jurjevac,
            location = Location(45.44515544185734, 18.444815555528134)
        )

        private val vukovar1991 = FootballTeam(
            name = "Vukovar 1991",
            image = R.drawable.vukovar1991,
            location = Location(45.380338509939946, 18.965397315345907)
        )

        private val croatiaDakovo = FootballTeam(
            name = "Croatia Đakovo",
            image = R.drawable.croatia_dakovo,
            location = Location(45.32123827858722, 18.408441032566955)
        )

        val hnlTeams = listOf(
            slogaNovaGradiska,
            oriolik,
            NASK,
            granicarZupanja,
            slavonija,
            cepin,
            kutjevo,
            vuteksSloga,
            bedem,
            darda,
            marsonia,
            slavijaPleternica,
            slavonacBukovlje,
            omladinacGornjaVrba,
            belisce,
            zrinskiJurjevac,
            vukovar1991,
            croatiaDakovo
        )

        val resultsTableHeaderAsFootballTeamItem = FootballTeamListItem(
            id = -1,
            name = "Klub",
            totalGamesPlayed = "Uk",
            wins = "Pob",
            draw = "Ner",
            losses = "Por",
            totalPoints = "Bod",
            image = -1,
            location = Location(0.0, 0.0)
        )

    }

}