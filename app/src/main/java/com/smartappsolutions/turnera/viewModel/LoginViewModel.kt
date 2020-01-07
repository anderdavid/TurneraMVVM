package com.smartappsolutions.turnera.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartappsolutions.turnera.model.classes.LoginUser
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.repository.LoginRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

class LoginViewModel(application: Application) : AndroidViewModel(application){

    var A_TAG ="Login"
    val TAG =A_TAG

    var email:String?=null
    var password:String?=null

    val default_backend:String="lagranjadelsaber.com/"


    val repository = LoginRepository(application)

    val validation: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val showProgressBarFlag:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val responseLogin:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val starAsuntosAcitivity:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }



    init {
        Log.d(TAG,"hello world viewmodel")
        initGlobal()
        starAsuntosAcitivity.value=false
        isLogin()
    }

    fun initGlobal(){
        viewModelScope.launch {
            Log.d(TAG,"initGlobal()")
            initGlobalDatabase()
        }
    }


    private  suspend fun initGlobalDatabase() = withContext(IO){
       var existGlobal: Boolean? =repository.validateExistFirstGlobal()
        Log.d(TAG, "existGlobal "+existGlobal.toString())
        if(!existGlobal!!){
            repository.insert(Global(false, default_backend))
        }else{
            val mGlobal = repository.getFirstGlobal()
            Log.d(TAG,"global: "+mGlobal.toString())
        }

        existGlobal=repository.validateExistFirstGlobal()
        Log.d(TAG, "existGlobal "+existGlobal.toString())

    }

    fun isLogin(){
        Log.d(TAG,"isLogin()")
        viewModelScope.launch{
            var mGlobal=repository.getFirstGlobal()
            if (mGlobal != null) {
                if (mGlobal.isLogin){
                    starAsuntosAcitivity.value=true
                }

            }
        }
    }

    fun setLogin(){
        Log.d(TAG,"setLogin()")
        viewModelScope.launch(IO) {
            var mGlobal=repository.getFirstGlobal()
            if(mGlobal !=null){
                mGlobal.isLogin=true
                repository.update(mGlobal)
            }

        }
    }

    private  suspend  fun setResponseLogin(response:String?){
        responseLogin.value = response
    }

    fun onButtonClick(view: View){
        Log.d(TAG,"onclick")

        val loginUser:LoginUser = LoginUser(email.toString(),password.toString())

        if(email.isNullOrEmpty()){
            validation.value="El campo email esta vacio."
        }else if(password.isNullOrEmpty()){
            validation.value="El campo password está vacío."
        }else if(!loginUser.isEmailValid()){
            validation.value="Email invalido."
        }else{

            Log.d(TAG,"isValid()")
            showProgressBarFlag.value=true

            viewModelScope.launch {

                try{
                    val response =  repository.userLogin(email!!,password!!)

                    if(response.isSuccessful){
                        showProgressBarFlag.value=false

                        val res:String? =response.body()?.string()
                        Log.d(TAG,"viewmodel isSucceful"+res)
                        val resJson = JSONObject(res)
                        val status:Boolean =resJson.getString("status").toBoolean()
                        Log.d(TAG,status.toString())

                        if(status){
                            showProgressBarFlag.value=false
                            setLogin()
                            starAsuntosAcitivity.value=true
                        }else{
                            val msg = resJson.getString("msg")
                            setResponseLogin(msg)
                        }

                    }

                }catch (e: HttpException){
                    Log.d(TAG,e.message())
                }catch (e:Throwable){
                    Log.d(TAG,"algo va mal "+e.toString())
                    showProgressBarFlag.value=false
                    responseLogin.value = "Error desconocido"
                }
            }
        }
    }

}