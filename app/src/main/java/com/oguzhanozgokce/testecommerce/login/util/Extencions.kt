package com.oguzhanozgokce.testecommerce.login.util

import android.view.View
import androidx.navigation.Navigation
import com.oguzhanozgokce.testecommerce.R

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|


fun Navigation.loginPage(it : View, id : Int) {
    findNavController(it).navigate(id)
}