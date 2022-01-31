package com.semixtech.cv_resume_builder.helper

import android.content.Context
import android.content.SharedPreferences

class SharedHelper {

    companion object {
        var sharedPreferences: SharedPreferences? = null
        var editor: SharedPreferences.Editor? = null

        public fun putKey(context: Context, key: String, value: String): String {
            sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
            editor = sharedPreferences!!.edit()
            editor!!.putString(key, value);
            editor!!.commit()

            return key;

        }

        public fun getKey(context: Context, key: String): String? {
            sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
            val value: String? = sharedPreferences!!.getString(key, "")
            return value;
        }
    }
}