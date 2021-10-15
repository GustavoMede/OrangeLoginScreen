package com.example.orangeloginscreen.presentation.ui.loadingdialog

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.orangeloginscreen.R

class LoadingDialog(
    val activity: AppCompatActivity
) {

    private val dialog by lazy {
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setCancelable(false)
        builder.create()
    }

    private val inflater by lazy {
        activity.layoutInflater
    }

    private val view by lazy {
        inflater.inflate(R.layout.custom_loading_dialog, null)
    }


    fun startLoadingDialog(state: String){
        configureViewData(state)
        configureDialog()
    }

    fun finalizeLoadingDialog(){
        dialog.cancel()
    }

    private fun configureDialog() {

        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()
    }

    private fun configureViewData(state: String) {
        val loadingName = view.findViewById<TextView>(R.id.loading_name)
        loadingName.text = state
    }
}