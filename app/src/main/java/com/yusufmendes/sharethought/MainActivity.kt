package com.yusufmendes.sharethought

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // kayitli kullaniciyi hatirlama

        val currentUser = auth.currentUser
        if (currentUser != null){
            val intent = Intent(this,MainPage::class.java)
            startActivity(intent)
            finish()
        }

    }

    //sign in islemleri
    fun signIn(view : View){

        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            if (email.isNotEmpty() && password.isNotEmpty()){
                if (task.isSuccessful){
                    var intent = Intent(this,MainPage::class.java)
                    startActivity(intent)
                    finish()
                }
            }else{
                Toast.makeText(this,"Please enter your email and password",Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener { exception ->
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

    //sign up islemleri
    fun signUp(view: View){

        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()

        //islem tamamlandı ise yapilacaklar
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            // email ve password editText bos degilse yapilacaklar
            if (email.isNotEmpty() && password.isNotEmpty()){
                //islem basarili ise yapilacaklar
                if (task.isSuccessful){
                    var intent = Intent(this,MainPage::class.java)
                    startActivity(intent)
                    finish()
                }
            }else{
                Toast.makeText(this,"Please enter your email and password",Toast.LENGTH_LONG).show()
            }
        }   //islem basarisiz ise hata mesajini goster
            .addOnFailureListener { exception ->
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
      }
   }
}