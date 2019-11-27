package com.smartappsolutions.turnera

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar: Toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)

        /*val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_login)*/
        /*val navView: NavigationView = findViewById(R.id.nav_view)*/

       /* val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )*/
       /* drawerLayout.addDrawerListener(toggle)*/
       /* toggle.syncState()*/

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

        val dialogSettings = DialogSettings(this)
        dialogSettings.showDialog()




    /*    val builder = AlertDialog.Builder(this)
        builder.setTitle("You want to add a new item?");
        builder.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(applicationContext,"hello",Toast.LENGTH_LONG).show()
            })


        builder.show()*/



        return true

    }
}


