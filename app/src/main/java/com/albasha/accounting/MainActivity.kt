
package com.albasha.accounting

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.widget.TextView
import android.graphics.Color

class MainActivity : Activity() {

    private var webView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(23, 59, 50)
        window.navigationBarColor = Color.rgb(23, 59, 50)

        try {
            val browser = WebView(this)
            webView = browser

            browser.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                allowFileAccess = true
                allowContentAccess = false
                allowFileAccessFromFileURLs = false
                allowUniversalAccessFromFileURLs = false
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
            }

            browser.webViewClient = WebViewClient()
            browser.webChromeClient = WebChromeClient()

            setContentView(browser)
            browser.loadUrl("file:///android_asset/index.html")

        } catch (e: Exception) {
            val message = TextView(this)
            message.text = "تعذر تشغيل البرنامج:\n${e.message}"
            message.textSize = 18f
            message.setPadding(30, 30, 30, 30)
            setContentView(message)
        }
    }

    @Deprecated("Deprecated in Android")
    override fun onBackPressed() {
        val browser = webView
        if (browser != null && browser.canGoBack()) {
            browser.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView?.apply {
            stopLoading()
            destroy()
        }
        webView = null
        super.onDestroy()
    }
}
