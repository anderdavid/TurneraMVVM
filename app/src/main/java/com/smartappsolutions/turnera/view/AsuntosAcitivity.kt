package com.smartappsolutions.turnera.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.smartappsolutions.turnera.R
import androidx.lifecycle.Observer
import com.smartappsolutions.turnera.view.dialogs.MDialogSettings
import com.smartappsolutions.turnera.viewModel.AsuntosViewModel
import com.smartappsolutions.turnera.viewModel.LoginViewModel

class AsuntosAcitivity : AppCompatActivity() {

    val TAG ="Asuntos"
    private lateinit var mViewModel:AsuntosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asuntos)
        Log.d(TAG,"onCreate()")

        mViewModel= ViewModelProviders.of(this).get(AsuntosViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar_asuntos)
        setSupportActionBar(toolbar)

        startLoginActivityObserver()

    }

    fun startLoginActivityObserver(){
        Log.d(TAG,"startLoginActivityObserver()")
        mViewModel.startLoginAcitivity.observe(this, Observer {
            if(it){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_asuntos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_logOut -> logOut()

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logOut(): Boolean {
        //Toast.makeText(applicationContext,"LogOut",Toast.LENGTH_LONG).show()
        mViewModel.logOut()

        return true
    }
}
