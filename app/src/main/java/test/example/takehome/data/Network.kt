package test.example.takehome.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


fun provideOkHttpClient(): OkHttpClient = OkHttpClient
    .Builder()
    .readTimeout(15, TimeUnit.SECONDS)
    .connectTimeout(15, TimeUnit.SECONDS)
    .build()

fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
    .baseUrl("https://url-shortener-nu.herokuapp.com/api")
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()