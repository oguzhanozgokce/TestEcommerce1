package com.oguzhanozgokce.testecommerce.ui.login.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.oguzhanozgokce.testecommerce.entitiy.User
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
    fun saveUserLogin(user: User) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_USERNAME, user.username)
        editor.putString("name", user.name)
        editor.putString("surname", user.surname)
        editor.putInt("userId", user.userId)  // Save the userId when logging in
        editor.apply()  // Using apply() to save changes asynchronously
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

    fun getUserName(): String? {
        return prefs.getString("name", "")
    }

    fun getUserSurname(): String? {
        return prefs.getString("surname", "")
    }

    fun getCurrentUserId(): Int {
        val userId = prefs.getInt("userId", -1)  // Default to -1 to indicate not found
        Log.e("UserSessionManager", "Retrieved userId: $userId")
        return userId
    }


    fun getSelectedProductId(): Int {
        val productId = prefs.getInt("productId", -1)  // Default to -1 to indicate not found
        Log.e("UserSessionManager", "Retrieved productId: $productId")
        return productId
    }

    /**
     * Kullanıcı oturumunu sonlandır
     */
    fun logoutUser() {
        editor.clear()
        editor.commit()
    }
    fun saveSelectedProductId(productId: Int) {
        editor.putInt("productId", productId)
        editor.apply()
    }
}
