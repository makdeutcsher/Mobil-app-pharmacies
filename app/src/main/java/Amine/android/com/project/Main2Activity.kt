package Amine.android.com.project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main2.*

import Amine.android.com.project.entity.Client
import Amine.android.com.project.retrofit.RetrofitService
import org.jetbrains.anko.intentFor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Math


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
                signUpBtn.setOnClickListener() {

                    if (nss.text.toString().isNotEmpty() && nom.text.toString().isNotEmpty() && prenom.text.toString().isNotEmpty() && mobileNumber.text.toString().isNotEmpty() && location.text.toString().isNotEmpty()  ){
                        var test =   nss.text.toString()
                        var test1 = nom.text.toString()
                        var test2 =prenom.text.toString()
                        var test3 =mobileNumber.text.toString()
                        var test4 =location.text.toString()
                        var alea: Double = Math.random()
                        var test5 = (alea*100000000).toInt().toString()
                        var test6=0
                        var client1:Client
                        client1=Client(nss =test,nom = test1,prenom = test2,num_tel_client= test3,adresse_client= test4,motdepasse = test5 ,log=test6)

                        val call= RetrofitService.endpoint.Register(client1)

                        call.enqueue(object : Callback<String> {
                            override fun onFailure(call: Call<String>, t: Throwable) {

                                Toast.makeText(this@Main2Activity,t.toString()+"test", Toast.LENGTH_LONG).show()

                            }

                            override fun onResponse(call: Call<String>, response: Response<String>) {
                                if (response.isSuccessful){

                                    Toast.makeText(this@Main2Activity,"ajouté avec succes, entrez vos cordonnes pour continuez", Toast.LENGTH_LONG).show()
                                    startActivity(intentFor<MainActivity>())

                                }
                                else{
                                    Toast.makeText(this@Main2Activity,"erreur", Toast.LENGTH_LONG).show()
                                }
                            }







                        })
                    }

                }
        }

    }


