package com.muhammad.erispumayoga.a.praktikum11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCreateAccount.setOnClickListener(this)
        auth = Firebase.auth
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCreateAccount -> {
                createAccount(binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString())
            }
        }
    }
    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Create User Success.",
                        Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivityForResult(intent, 1)
            finish()
        }
    }

}