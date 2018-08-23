package com.shan.android.squire.Utils

import android.content.Context
import android.widget.Toast

class DisplayUtils(){
    companion object {
        fun showToast(context: Context, text: String){
            Toast.makeText(context, text, Toast.LENGTH_LONG ).show();
        }
    }
}