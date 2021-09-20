package com.stjepanbarisic.a3hnlistok.data.database.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stjepanbarisic.a3hnlistok.data.models.Location
import java.lang.reflect.Type

class LocationTypeConverter {

    private val type: Type = object : TypeToken<Location>() {}.type
    private val gson = Gson()

    @TypeConverter
    fun locationToString(location: Location): String {
        return gson.toJson(location, type)
    }

    @TypeConverter
    fun stringToLocation(locationString: String): Location {
        return gson.fromJson(locationString, type)
    }

}