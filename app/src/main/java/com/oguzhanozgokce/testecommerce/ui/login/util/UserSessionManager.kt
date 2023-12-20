package com.oguzhanozgokce.testecommerce.ui.login.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class UserSessionManager @Inject constructor (context: Context) {
    companion object {
        private const val PREF_NAME = "UserSessionPref"
        private const val IS_LOGIN = "IsLoggedIn"
        private const val KEY_USERNAME = "Username"
    }

    private var prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefs.edit()

    /**
     * Kullanıcı giriş durumunu kaydetme
     */
    fun saveUserLogin(username: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_USERNAME, username)
        editor.commit()
    }

    /**
     * Kullanıcı giriş durumunu kontrol etme
     */
    fun isUserLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGIN, false)
    }

    /**
     * Kaydedilmiş kullanıcı adını al
     */
    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }

    /**
     * Kullanıcı oturumunu sonlandır
     */
    fun logoutUser() {
        editor.clear()
        editor.commit()
    }
}
