package com.example.sakolaapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sakolaapp.R
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var user: FirebaseAuth //Instancia do FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            DebugAppCheckProviderFactory.getInstance()
        )

        user = FirebaseAuth.getInstance() //Pegando a instancia atual

        if (user.currentUser != null) { //Verificar se há usuário logado

            //Caso haja usuário logado, irá iniciar a activity Home
            startActivity(Intent(this, HomeActivity::class.java))
        }

        registerScreen.setOnClickListener {  //Abrir activity de cadastro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        Entrar.setOnClickListener {     //Logar com email e senha
            val email = emailLogin.text.toString()      //Retorna email digitado
            val senha = senhaLogin.text.toString()      //Retorna Senha digitada

            logarUsuário(email, senha)   //Chama o método de Login com email e senha
        }
    }

    private fun logarUsuário(email: String, senha: String) { //método de login

        user.signInWithEmailAndPassword(email, senha)
            .addOnSuccessListener { //Se o usuário logar, será enviado para a tela principal
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {

            }
    }
}