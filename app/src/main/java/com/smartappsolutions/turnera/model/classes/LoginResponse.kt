package com.smartappsolutions.turnera.model.classes

import okhttp3.ResponseBody
import retrofit2.Response

class LoginResponse(
    var isSuccefull:Boolean,
    var message:String,
    var response: String?){
}