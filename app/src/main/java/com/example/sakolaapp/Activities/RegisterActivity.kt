package com.example.sakolaapp.Activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sakolaapp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth //Setando o Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance() //Pega a instancia atual do Auth

        Registrar.setOnClickListener {  //Método para chamada de registro do usuário
            var email = emailRegister.text.toString()
            var senha = senhaRegister.text.toString()

            createAccount(email, senha)
        }

    }

    private fun createAccount(email: String, password: String) {  //Registrar Novo Usuário

        var confsenha = confSenhaRegister.text.toString() //Recebe a confirmação da senha

        //Verifica se as senhas são iguais
        if (password.equals(confsenha)) {

            //Iniciar registro de novo usuário
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser //Retorna o ID do usuário atual
                        updateUI(user) //Inicia o Aplicativo
                    } else {
                        //Tratamentos de Erros
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        } else { //Senhas não conferem

            Snackbar.make(Constain, "As senhas não coincidem", Snackbar.LENGTH_SHORT).show()
        }
    }


    private fun updateUI(user: FirebaseUser?) {
        // Abrir a homeActivity
        if (user != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}