package br.com.celleptech.appestacaohack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    // fun onCreate é um metodo presente no ciclo de vida de uma Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // abrindo a LoginActivity após 3 segundos

        Handler(Looper.getMainLooper()).postDelayed({
            //iniciar a telaq de login
            val mIntent = Intent(this, LoginActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 3000

        )

    }
}