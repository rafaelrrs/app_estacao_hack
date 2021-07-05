package br.com.celleptech.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recuperar o email passado por meio de Intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        //acessar o arquivo de preferências compartilhadas
        val sharedPrefs = getSharedPreferences(
               "cadastro_$email", //Nome do arquivo
                Context.MODE_PRIVATE     //Modo de acesso
        )

        //recuperar dados no arquivo de preferências compartilhadas
        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val genero = sharedPrefs.getString("GENERO", "")

        //exibir os dados na tela
        txvMainNome.text = "$nome $sobrenome"
        txvMainEmail.text = email
        txvMainGenero.text = genero

        //escutar o clique do botão SAIR
        btnMainSair.setOnClickListener {
            //criar caixa de alerta de diálogo
            val alert = AlertDialog.Builder(this)

            //definir o título da caixa de diálogo
            alert.setTitle("App Curso EH")

            //definir corpo da mensagem
            alert.setMessage("Deseja sair?")

            //definindo o rótulo de botão e executar
            alert.setPositiveButton("Sair"){_,_ ->
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)

                //eliminar as telas daa pilha
                finishAffinity()
            }

            //definir o rótulo do botão e escutando o seu clique
            alert.setNegativeButton("Não"){_,_ -> }

            //exibir a caixa de dialogo
            alert.show()
        }

        //escutar o clique do botão SITE CELLEP
        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }
}