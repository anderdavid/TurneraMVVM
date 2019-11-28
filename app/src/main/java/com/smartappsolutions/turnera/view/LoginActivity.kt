package com.smartappsolutions.turnera.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.smartappsolutions.turnera.viewModel.LoginViewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.smartappsolutions.turnera.R
import com.smartappsolutions.turnera.database.entities.Global
import com.smartappsolutions.turnera.databinding.ActivityLoginBinding
import com.smartappsolutions.turnera.view.dialogs.MDialogSettings

class LoginActivity : AppCompatActivity() {


    private lateinit var mViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel=ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        binding.viewmodel=mViewModel

        val toolbar: Toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)

        addObserver()
        addGlobalObserver()

    }

    private fun addGlobalObserver(){
        val observer = Observer<List<Global>>{globals->
            if(globals!=null){
                var text=""
                for(global in globals){
                    text+=""+global.isLogin + " "+ global.backend +"\n"
                }
                Toast.makeText(applicationContext,text,Toast.LENGTH_SHORT).show()
            }

        }
        mViewModel.globals.observe(this,observer)
    }

    private fun addObserver() {

        val observer =Observer<String>{valitation->
            Toast.makeText(applicationContext,valitation.toString(),Toast.LENGTH_SHORT).show()
        }
        mViewModel.validation.observe(this,observer)
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
        Toast.makeText(applicationContext,"settings",Toast.LENGTH_SHORT).show()

        /*val dialogSettings = DialogSettings(this)
        dialogSettings.showDialog()*/

        val dialogSettings = MDialogSettings().newInstance()
        dialogSettings.show(supportFragmentManager,"dialog")



        return true

    }
}


