package com.smartappsolutions.turnera.view

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.smartappsolutions.turnera.viewModel.LoginViewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.smartappsolutions.turnera.R
import com.smartappsolutions.turnera.model.database.entities.Global
import com.smartappsolutions.turnera.databinding.ActivityLoginBinding
import com.smartappsolutions.turnera.view.dialogs.DialogConexion
import com.smartappsolutions.turnera.view.dialogs.MDialogSettings
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {


    val TAG ="Login"

    private lateinit var mViewModel:LoginViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d(TAG,"onCreate()")

        mViewModel=ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel=mViewModel

        val toolbar: Toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)

        validationObserver()
        showProgressBarObserver()
        addLoginResponseObserver()
        starMainActivityObserver()

    }

    private fun validationObserver() {

        val observer =Observer<String>{valitation->
            Toast.makeText(applicationContext,valitation.toString(),Toast.LENGTH_SHORT).show()
        }
        mViewModel.validation.observe(this,observer)
    }

    fun showProgressBarObserver(){
        mViewModel.showProgressBarFlag.observe(
            this, Observer {isVisible->
                if(isVisible){
                    this.progress_bar.visibility =View.VISIBLE
                }else{
                    this.progress_bar.visibility =View.GONE
                }
            }
        )
    }

    fun addLoginResponseObserver(){
        Log.d(TAG,"addLoginObserver()")
        mViewModel.responseLogin.observe(this, Observer {loginResponse->
            Log.d(TAG,"Observer()")
            val ft = supportFragmentManager.beginTransaction()
            DialogConexion().setConfig(ft,"Alerta",loginResponse)
        })
    }

    fun starMainActivityObserver(){
        Log.d(TAG,"starMainActivityObserver()")
        mViewModel.starMainAcitivity.observe(this, Observer {
            if(it){
                val intent = Intent(this,AsuntosAcitivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> settings()

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun settings(): Boolean {
       /* Toast.makeText(applicationContext,"settings",Toast.LENGTH_SHORT).show()*/

        val dialogSettings = MDialogSettings().newInstance()
        dialogSettings.show(supportFragmentManager,"dialog")


        return true
    }

    fun showProgressBar(){
        this.progress_bar.visibility =View.VISIBLE
    }
    fun dimissProgressBar(){
        this.progress_bar.visibility=View.GONE
    }

    override fun onStop() {
        super.onStop()
        this.progress_bar.visibility= View.GONE
    }

}




