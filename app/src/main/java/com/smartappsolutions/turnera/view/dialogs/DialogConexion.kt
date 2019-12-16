package com.smartappsolutions.turnera.view.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import android.content.DialogInterface
import android.R.id.message
import android.app.AlertDialog
import android.util.Log
import com.smartappsolutions.turnera.R


class DialogConexion: DialogFragment() {

    val TAG ="DialogTest"

    var message:String ?=null
    var title:String ?=null

    fun setConfig(ft:FragmentTransaction,mTitle:String,mMessage:String){
        Log.d(TAG,"setConfig()")

        message =mMessage
        title=mTitle
        this.show(ft,"tag")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG," onCreateDialog()")

        return AlertDialog.Builder(activity, R.style.StyledDialog)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener {
                    dialog, which ->

            })
            .create()
    }
}