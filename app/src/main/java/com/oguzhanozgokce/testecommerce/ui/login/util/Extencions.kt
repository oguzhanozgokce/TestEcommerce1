package com.oguzhanozgokce.testecommerce.ui.login.util

import android.view.View
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar


fun Navigation.loginPage(it : View, id : Int) {
    findNavController(it).navigate(id)
}

//snackbar
fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

//toast
fun View.toast(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}