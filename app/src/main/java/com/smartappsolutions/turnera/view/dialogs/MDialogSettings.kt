package com.smartappsolutions.turnera.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smartappsolutions.turnera.R
import com.smartappsolutions.turnera.viewModel.DialogSettingsViewModel


class MDialogSettings:DialogFragment() {

    val TAG ="MDialogSettings"

    lateinit var rg:RadioGroup

    private lateinit var model: DialogSettingsViewModel


    fun newInstance(): MDialogSettings {
        Log.d(TAG,"newInstance")

        val frag = MDialogSettings()

        return frag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")

        model = activity?.run {
            ViewModelProviders.of(this).get(DialogSettingsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        model.data.observe(this, Observer<String> { item ->
            Toast.makeText(context,item.toString(),Toast.LENGTH_SHORT).show()
        })


    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG,"onCreateDialog")

        return activity?.let {

            val builder = AlertDialog.Builder(it,R.style.StyledDialog)

            val inflater = activity?.layoutInflater
            val vi = inflater?.inflate(R.layout.dialog_setting, null)
            builder.setView(vi)

            val checkOther =vi?.findViewById<RadioButton>(R.id.other)
            val edtCheckOther =vi?.findViewById<EditText>(R.id.other_backend)

            checkOther?.setOnCheckedChangeListener(
                CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    if(buttonView.isChecked()){
                        edtCheckOther?.setEnabled(true)
                    }else{
                        edtCheckOther?.setEnabled(false)
                    }
                }
            )

            model.firstGlobal.observe(this, Observer {

                var id =R.id.produccion
                when(it.backend){
                    getString(R.string.production)->{
                        id =R.id.produccion
                    }
                    getString(R.string.centos_laravel)->{
                        id =R.id.centos_laravel
                    }
                    getString(R.string.centos_apache)->{
                        id =R.id.centos_apache
                    }
                    getString(R.string.other)->{
                        id =R.id.other
                    }
                    else ->{
                        id =R.id.other
                        edtCheckOther?.setText(it.backend)
                    }

               }
                rg.check(id)
            })

           rg = vi!!.findViewById(R.id.rg)

            builder.setTitle(R.string.dialog_setting_title);
            builder.setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->


                        var radioButton:RadioButton=vi.findViewById(rg.checkedRadioButtonId)
                        var backend = radioButton.text.toString()
                        if(rg.checkedRadioButtonId ==R.id.other){

                            val backend_txt =vi.findViewById<EditText>(R.id.other_backend).text.toString()

                            if(backend_txt.isEmpty()){
                                Toast.makeText(context,"Backend Invalido",Toast.LENGTH_LONG).show()
                                backend=""

                            }else{
                                backend =backend_txt
                            }
                        }


                        Log.d(TAG,"backend: $backend")

                        model.firstGlobal.observe(this, Observer {
                            if(!backend.isEmpty()){
                                it.backend =backend
                                model.updateFirstGlobal(it)
                            }
                        })


                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}