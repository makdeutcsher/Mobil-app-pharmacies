package Amine.android.com.project


import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import Amine.android.com.project.entity.Client

import Amine.android.com.project.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var token=getSharedPreferences("numtext", Context.MODE_PRIVATE)
        if(token.getString("loginusername"," ")!=" "){
            var intent=Intent(this,Main5Activity::class.java)
            startActivity(intent)
            finish()
        }
        var idpharmacie = ""

        if (intent.hasExtra("idpharmacie")) { // vérifie qu'une valeur est associée à la clé “edittext”
            idpharmacie = intent.getStringExtra("idpharmacie") // on récupère la valeur associée à la clé

        }
        login.setOnClickListener {
            var numtext = num.text.toString()
            var passwordtext = Password.text.toString()
            if (num.text.toString().isNotEmpty() && Password.text.toString().isNotEmpty()  ){

                    val call= RetrofitService.endpoint.getpass(numtext)

                    call.enqueue(object :Callback<List<Client>>{
                        override fun onFailure(call: Call<List<Client>>, t: Throwable) {

                            Toast.makeText(this@MainActivity,t.toString()+"hnayhbass", Toast.LENGTH_LONG).show()

                        }

                        override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                            if (response.isSuccessful){

                                Toast.makeText(this@MainActivity,"", Toast.LENGTH_LONG).show()
                                val pass= response?.body()
                                val passtest =pass?.get(0)
                                val nb=passtest?.log
                                val passold =passtest?.motdepasse
                                val num=passtest?.num_tel_client
                                val nss=passtest?.nss
                                val username =passtest?.prenom
                                if (passtest?.motdepasse==passwordtext)
                                {
                                    Toast.makeText(this@MainActivity,"loged with success", Toast.LENGTH_LONG).show()
                                    if (nb!! >1){
                                        val intent = Intent(this@MainActivity,Main5Activity::class.java)
                                        intent.putExtra("username",username )
                                        intent.putExtra("idpharmacie", idpharmacie)
                                        intent.putExtra("nss", nss)
                                        finish()
                                        var editor=token.edit()
                                        editor.putString("loginusername",username)
                                        editor.putString("nss",nss)
                                        editor.commit()
                                        startActivity(intent)
                                    }else{
                                        val intent = Intent(this@MainActivity,Main4Activity::class.java)
                                        intent.putExtra("Password", passold)
                                        intent.putExtra("num", num)
                                        startActivity(intent)

                                    }

                                }else  {
                                    Toast.makeText(this@MainActivity,"num tel ou motdepassse erroees", Toast.LENGTH_LONG).show()

                                }

                            }
                            else{
                                Toast.makeText(this@MainActivity,"erreur", Toast.LENGTH_LONG).show()
                            }
                        }







                    })
                }
        }
        signup.setOnClickListener {
            startActivity(intentFor<Main2Activity>())
        }
    }

}


