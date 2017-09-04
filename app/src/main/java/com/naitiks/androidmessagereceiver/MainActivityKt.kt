package com.naitiks.androidmessagereceiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class MainActivityKt : AppCompatActivity() {
    private val MULTI_PER_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val receiveSMS = ContextCompat.checkSelfPermission(this@MainActivityKt,
                Manifest.permission.RECEIVE_SMS)

        val readSMS = ContextCompat.checkSelfPermission(this@MainActivityKt,
                Manifest.permission.READ_SMS)

        if (receiveSMS != PackageManager.PERMISSION_GRANTED || readSMS != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivityKt,
                    arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS),
                    MULTI_PER_CODE)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MULTI_PER_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults.size > 1
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivityKt, "Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivityKt, "Permission not granted", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}
