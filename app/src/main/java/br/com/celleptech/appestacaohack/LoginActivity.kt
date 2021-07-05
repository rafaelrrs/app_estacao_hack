package br.com.celleptech.appestacaohack

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //executar o clique do botão ENTRAR
        btnLoginEntrar.setOnClickListener {
            //capturar os dados digitados pelo usuário
            //o metodo terim() remove os espaços no incio e no fim da escrita
            val email = edtLoginUsuario.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            //validação dos campos
            if(email.isEmpty()){
                edtLoginUsuario.error = "Campo obrigatório!"
                edtLoginUsuario.requestFocus()
            }else if(senha.isEmpty()){
                edtLoginSenha.error = "Campo obrigatório"
                edtLoginSenha.requestFocus()
            } else {
                //acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //vamos recuperar email e senha
                val emailPrefs = sharedPrefs.getString("EMAIL", "Chave não encontrada!")
                val senhaPrefs = sharedPrefs.getString("SENHA", "Chave não encontrada!")

                //verificando email e senha
                if(email == emailPrefs && senha == senhaPrefs) {

                    Toast.makeText(this, "Usuário Logado!", Toast.LENGTH_LONG).show()

                    //abrindo a MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)

                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()

            }else{
                //apresentar uma mensagem de erro ao usuário
                Toast.makeText(this, "E-mail ou senha inválidos!", Toast.LENGTH_LONG).show()
            }
            }
        }

        //executar clique do botão cadastrar
        btnLoginCadastrar.setOnClickListener {
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }
}