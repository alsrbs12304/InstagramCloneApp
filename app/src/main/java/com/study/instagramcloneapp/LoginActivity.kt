package com.study.instagramcloneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        email_login_bt.setOnClickListener {
            signinAndSignup()
        }
    }

    fun signinAndSignup() {
        auth?.createUserWithEmailAndPassword(
            email_edit.text.toString(),
            password_edit.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Creating a user account
                    moveMainPage(task.result?.user)
                } else if (task.exception?.message.isNullOrEmpty()) {
                    // Show the error message
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                } else {
                    // Login if you have account
                    signinEnail()
                }
            }
    }

    fun signinEnail() {
        auth?.signInWithEmailAndPassword(email_edit.text.toString(), password_edit.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    // Creating a user account
                    moveMainPage(task.result?.user)
                } else {
                    // Login if you have account
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                }
            }
    }
    fun moveMainPage(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}