package com.stjepanbarisic.a3hnlistok.data.database.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stjepanbarisic.a3hnlistok.data.models.GameGoals
import java.lang.reflect.Type
import java.util.ArrayList

class GameGoalsTypeConverter {

    private val type: Type = object : TypeToken<ArrayList<GameGoals>>() {}.type
    private val gson = Gson()

    @TypeConverter
    fun goalsToString(goals: ArrayList<GameGoals>?): String {
        return gson.toJson(goals, type)
    }

    @TypeConverter
    fun stringToGoals(goalsString: String?): ArrayList<GameGoals>? {
        return gson.fromJson(goalsString, type)
    }

}