package com.smartappsolutions.turnera.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smartappsolutions.turnera.R
import com.smartappsolutions.turnera.database.entities.Global
import com.smartappsolutions.turnera.viewModel.SharedViewModel


class MDialogSettings:DialogFragment() {

    val TAG ="MDialogSettings"

    lateinit var rg:RadioGroup

    private lateinit var model: SharedViewModel


    fun newInstance(title: String): MDialogSettings {
        Log.d(TAG,"newInstance")

        val frag = MDialogSettings()
        val args = Bundle()
        args.putString("title", title)
        frag.arguments = args
        return frag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")

        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        model.data.observe(this, Observer<String> { item ->
            Toast.makeText(context,item.toString(),Toast.LENGTH_SHORT).show()
        })
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG,"onCreateDialog")

        val title = arguments!!.getString("title")
        return activity?.let {

            val builder = AlertDialog.Builder(it,R.style.StyledDialog)

            val inflater = activity?.layoutInflater
            val vi = inflater?.inflate(R.layout.dialog_setting, null)
            builder.setView(vi)

           rg = vi!!.findViewById(R.id.rg)

            builder.setTitle(R.string.dialog_setting_title);
            builder.setMessage("Aceptar")
                .setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->


                        var radioButton:RadioButton=vi.findViewById(rg.checkedRadioButtonId)
                        var backend = radioButton.text.toString()

                       /* Toast.makeText(context,"backend: $backend",Toast.LENGTH_SHORT).show()*/
                       /*  model.data.value = backend*/
                        model.saveGlobal(Global(true,backend))

                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}