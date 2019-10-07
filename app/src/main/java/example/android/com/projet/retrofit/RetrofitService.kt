package example.android.com.projet.retrofit

import example.android.com.projet.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    val endpoint: Endpoint by lazy {
        Retrofit.Builder().baseUrl(baseUrl).
            addConverterFactory(GsonConverterFactory.create()).build()
            .create(Endpoint::class.java)

    }
}