package com.smartappsolutions.turnera.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smartappsolutions.turnera.R
import com.smartappsolutions.turnera.databinding.ActivityLoginBinding
import com.smartappsolutions.turnera.viewModel.AsuntosViewModel
import com.smartappsolutions.turnera.viewModel.LoginViewModel

import kotlinx.android.synthetic.main.activity_test_coroutines.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TestCoroutines : AppCompatActivity() {

    val TAG ="Asuntos"

    private lateinit var mViewModel:AsuntosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutines)
        Log.d(TAG,"onCreate() "+TAG)

        mViewModel= ViewModelProviders.of(this).get(AsuntosViewModel::class.java)

        mViewModel.initAsuntos()
        mViewModel.getAsuntos()

        //asuntosObservable()



        CoroutineScope(IO).launch {
            funcionSuspendida()
        }

    }

   /* fun  asuntosObservable(){
        mViewModel.asunto.observe(this, Observer {asuntos->
                if(asuntos!=null){
                    var text=""
                    for(asunto in asuntos){
                        text+=asunto.asunto+"\n"
                    }
                    Toast.makeText(applicationContext,text,Toast.LENGTH_LONG).show()
                }
            }

        )
    }*/

    suspend fun funcionSuspendida(){
        Log.d(TAG,"funcion suspendida");
    }

}

