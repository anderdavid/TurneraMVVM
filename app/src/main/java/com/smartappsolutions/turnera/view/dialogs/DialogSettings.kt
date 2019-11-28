package com.smartappsolutions.turnera.view.dialogs


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.DialogFragment

import com.smartappsolutions.turnera.R


class DialogSettings(mActivity:Activity):DialogFragment(){

    var mActivity =mActivity
    var builder:AlertDialog.Builder=AlertDialog.Builder(mActivity, R.style.StyledDialog)
    var dialog: AlertDialog? = builder.create()


    fun showDialog(){
        val inflater = mActivity.layoutInflater
        val vi = inflater.inflate(R.layout.dialog_setting, null)
        builder.setView(vi)
        builder.setTitle(R.string.dialog_setting_title);

        builder.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialog, id ->
               /* Toast.makeText(applicationContext,"hello", Toast.LENGTH_LONG).show()*/
            })

        dialog =builder.create()
        dialog!!.show()

    }

    fun dimmissDialog(){
        dialog!!.cancel()
    }

}
