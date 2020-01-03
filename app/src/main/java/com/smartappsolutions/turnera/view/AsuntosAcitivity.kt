package com.smartappsolutions.turnera.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.smartappsolutions.turnera.R

class AsuntosAcitivity : AppCompatActivity() {

    val TAG ="AsuntosAcitivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asuntos)
        Log.d(TAG,"onCreate()")
    }
}
