package de.wirvsvirus.abee.data

import android.content.SharedPreferences
import de.wirvsvirus.abee.MyApplication

object SettingsManager {
    val sharedPreferences: SharedPreferences = MyApplication.appContext.getSharedPreferences("de.wirvsvirus.abee", 0)

    inline fun <reified T> savePreference(key: String, value: T) {
        val editor = sharedPreferences.edit()

        when(T::class) {
            Boolean::class -> editor.putBoolean(key, value as Boolean)
            Float::class -> editor.putFloat(key, value as Float)
            Int::class -> editor.putInt(key, value as Int)
            Long::class -> editor.putLong(key, value as Long)
            String::class -> editor.putString(key, value as String)
            else -> {
                if (value is Set<*>) {
                    editor.putStringSet(key, value as Set<String>)
                }
            }
        }

        editor.apply()
    }

    inline fun <reified T> getPreference(key: String, defaultValue: T): T {
        when(T::class) {
            Boolean::class -> return sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            Float::class -> return sharedPreferences.getFloat(key, defaultValue as Float) as T
            Int::class -> return sharedPreferences.getInt(key, defaultValue as Int) as T
            Long::class -> return sharedPreferences.getLong(key, defaultValue as Long) as T
            String::class -> return sharedPreferences.getString(key, defaultValue as String) as T
            else -> {
                if (defaultValue is Set<*>) {
                    return sharedPreferences.getStringSet(key, defaultValue as Set<String>) as T
                }
            }
        }
        return defaultValue
    }
}