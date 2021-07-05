package br.com.celleptech.appestacaohack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        //kabilitando a execução do Javascript
        wvbWeb.settings.javaScriptEnabled = true

        //carregando endereço web
        wvbWeb.loadUrl("http://br.cellep.com/escataohack")

        //definindo o navegador padrão
        wvbWeb.webViewClient = WebViewClient()
    }
}