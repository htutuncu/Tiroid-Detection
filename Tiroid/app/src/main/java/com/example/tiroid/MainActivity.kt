package com.example.tiroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tiroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val dbHelper : DBHelper = DBHelper(this)
    lateinit var binding : ActivityMainBinding
    lateinit var user : User
    lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
    }

    fun signIn(view: View) : Unit {
        if (binding.llSignInEdittexts.visibility == View.VISIBLE) {
            val userName : String = binding.tilMailSignIn.editText?.text.toString()
            val password :String = binding.tilPasswordSignIn.editText?.text.toString()

            if (userName == "" || password =="") {
                Toast.makeText(this,"Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
            }
            else {
                if (dbHelper.checkUserName(userName)) {
                    Toast.makeText(this,"Giriş başarılı.", Toast.LENGTH_LONG).show()
                    val intent : Intent = Intent(this,TiroidActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this,"Hatalı giriş. Lütfen tekrar deneyiniz.", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            binding.llSignInEdittexts.visibility = View.VISIBLE
            binding.llSignUp.visibility = View.GONE
        }
    }

    fun signUp(view:View) : Unit {
        if (binding.llSignUpEdittexts.visibility == View.VISIBLE){
            val userName = binding.tilMail.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()
            val nameSurname = binding.tilName.editText?.text.toString()
            val age = binding.tilAge.editText?.text.toString()

            if (userName == "" || password == "") {
                Toast.makeText(this,"Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
            }
            else {
                if (dbHelper.checkUserName(userName)) {
                    Toast.makeText(this,"Kullanıcı zaten kayıtlı.", Toast.LENGTH_LONG).show()
                }
                else {
                    val bool = dbHelper.insertData(userName,password)
                    if (bool) {
                        Toast.makeText(this,"Kayıt başarılı.", Toast.LENGTH_LONG).show()
                        val editor = sp.edit()
                        editor.putString("name",nameSurname)
                        editor.putString("age",age)
                        editor.putString("userName",userName)
                        editor.putString("password",password)
                        editor.apply()
                        binding.llSignInEdittexts.visibility = View.VISIBLE
                        binding.llSignUpEdittexts.visibility = View.GONE
                        binding.llSignUp.visibility = View.GONE
                        binding.llSignIn.visibility = View.VISIBLE
                    }
                    else
                        Toast.makeText(this,"Kayıt edilemedi.", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            binding.llSignUpEdittexts.visibility = View.VISIBLE
            binding.llSignIn.visibility = View.GONE
        }
    }
}