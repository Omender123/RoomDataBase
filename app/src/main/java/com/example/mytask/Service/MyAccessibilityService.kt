package com.example.mytask.Service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService : AccessibilityService() {
    val Tag = "MyAccessibilityService"
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.e(Tag, "onAccessibilityEvent :  ")
        var packageName: String = p0?.packageName.toString()

        var packageManager = this.packageManager
        try {

            var applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            var applicationLevel = packageManager.getApplicationLabel(applicationInfo)
            Log.e(Tag, "app name is  :  " + applicationLevel)

            Toast.makeText(this, "" + applicationLevel, Toast.LENGTH_SHORT).show()
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(Tag, "error:  " + e)
        }
    }

    override fun onInterrupt() {
        Log.e(Tag, "onInterrupt :  something went wrong ")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        var info: AccessibilityServiceInfo = AccessibilityServiceInfo()
        info.apply {

            eventTypes =
                AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED

            //   packageNames = arrayOf("com.whatsapp")

            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            notificationTimeout = 100
        }

        this.serviceInfo = info

        Log.e(Tag, "onServiceConnected : ")
    }
}