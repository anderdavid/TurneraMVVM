package com.smartappsolutions.turnera


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

import android.view.LayoutInflater



class DialogSettings(mActivity:Activity):DialogFragment(){

    var mActivity =mActivity
    var builder:AlertDialog.Builder=AlertDialog.Builder(mActivity,R.style.StyledDialog)
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
