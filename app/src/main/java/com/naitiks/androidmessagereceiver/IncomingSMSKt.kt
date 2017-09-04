package com.naitiks.androidmessagereceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast

/**
 * Created by Naitik on 9/4/2017.
 */

class IncomingSMSKt : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        var str = ""
        if (bundle != null) {
            val pdus = bundle.get("pdus") as Array<Any>
            for (onePdus : Any in pdus) {
                val oneSMS = SmsMessage.createFromPdu(onePdus as ByteArray)
                str += "SMS from " +oneSMS.originatingAddress
                str += " :"
                str += oneSMS.messageBody.toString()
                str += "\n"
            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }
    }
}
