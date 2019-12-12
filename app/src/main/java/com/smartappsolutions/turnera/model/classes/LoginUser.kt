package com.smartappsolutions.turnera.model.classes

import android.util.Patterns;

class LoginUser(mEmailAdress:String,mPassword:String) {

    val emailAdress:String =mEmailAdress
    val password =mPassword

    fun isEmailValid():Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(emailAdress).matches();
    }

        fun isPasswordLengthGreaterThan5():Boolean{
        return password.length >5
    }

}