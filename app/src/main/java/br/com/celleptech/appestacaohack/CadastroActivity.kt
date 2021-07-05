package br.com.celleptech.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //criando uma lista de opções para o spinner
        val listaGenero = arrayListOf("Selecione o gênero", "Masculino", "Feminino", "Não binário")

        //criando um adaptador para o spinner
        val generoAdapter = ArrayAdapter(
            this,                                       //contexto
                android.R.layout.simple_spinner_dropdown_item,  //layout
                listaGenero                                     //dados da lista
        )

        //plugar o adaptador no spinner
        spnCadastroGenero.adapter = generoAdapter

        //executar o clique do botão CADASTRAR
        btnCadastroCadastrar.setOnClickListener {

            //capturar os dados digitados
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            //validação dos campos
            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0]){
                //apresentar uma mensagem de erro ao usuário
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }else{
                //todos os campos preenchidos

                //criando ou acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //criar um editor para o arquivo
                val editPrefs = sharedPrefs.edit()

                //preparando os dados para serem acessados
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                //salvando os dados no Shared Preferences
                editPrefs.apply()

                //abrindo o MainActivity
                val mIntent = Intent(this, MainActivity::class.java)

                //passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                //tirando as telas do empilhamento
                finishAffinity()
            }

        }
    }
}