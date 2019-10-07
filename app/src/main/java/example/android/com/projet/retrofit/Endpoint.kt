package example.android.com.projet.retrofit


import example.android.com.projet.entity.Client
import example.android.com.projet.entity.Commande
import example.android.com.projet.entity.Pharmacie
import retrofit2.Call
import retrofit2.http.*
import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Multipart




interface Endpoint {

    @GET("getlistclient")
    fun getClients():Call<List<Client>>

    @GET("getlistpharmacie")
    fun getPharmacies():Call<List<Pharmacie>>

    @GET("getlistpharmaciebyville/{ville}")
    fun getPharmaciesByVille(@Path("ville") ville:String):Call<List<Pharmacie>>


    @GET("getclientdetail/{id}")
    fun getDetailClient(@Path("id") id:Int):Call<Client>

    @GET("getpharmaciedetail/{id}")
    fun getDetailPharmacie(@Path("id") id:Int):Call<Pharmacie>

    @GET("getpass/{nss}")
    fun getpass(@Path("nss") nss:String):Call<List<Client>>

    @POST("Register")
    fun Register(@Body client:Client):Call<String>

    @POST("addclient")
    fun addclient(@Body client:Client):Call<String>

    @POST("addcommande")
    fun addcommande(@Body commande:Commande):Call<String>

    @Multipart
    @POST("upload")
    fun upload(@Part file: MultipartBody.Part, @Part("name") requestBody: RequestBody): Call<String>

    @PUT("setpass")
    fun setpass(@Body client:Client):Call<String>
}