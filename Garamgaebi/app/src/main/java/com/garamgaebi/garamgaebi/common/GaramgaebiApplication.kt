package com.garamgaebi.garamgaebi.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import androidx.lifecycle.MutableLiveData
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.model.User
import com.garamgaebi.garamgaebi.BuildConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.TimeUnit

// 앱이 실행될때 1번만 실행이 됩니다.
class GaramgaebiApplication : Application() {
    //val API_URL = "https://dev.garamgaebi.shop/"
    // 실 서버 주소
     val API_URL = "https://garamgaebi.shop/"

    // 코틀린의 전역변수 문법
    companion object {
        private lateinit var appInstance: GaramgaebiApplication
        fun getApplication() = appInstance
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var myDataStore: DataStore<Preferences>
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")
        val gameOut : MutableLiveData<Boolean> = MutableLiveData(false)

        // JWT Token Header 키 값
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val X_REFRESH_TOKEN = "X_REFRESH_TOKEN"
        var myMemberIdx = 0

        var getProfile = true
        var getSNS = true
        var getCareer = true
        var getEdu = true

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit
    }
    suspend fun saveStringToDataStore(key: String, value: String) {
        val stringKey = stringPreferencesKey(key) // String 타입 저장 키값
        Log.d("dataStoreSaveString", "$key:$value")
        dataStore.edit { preferences ->
            preferences[stringKey] = value
        }
    }
    suspend fun saveIntToDataStore(key: String, value: Int) {
        val intKey = intPreferencesKey(key) // String 타입 저장 키값
        Log.d("dataStoreSaveInt", "$key:$value")
        dataStore.edit { preferences ->
            preferences[intKey] = value
        }
    }

    suspend fun saveBooleanToDataStore(key: String, value: Boolean) {
        val booleanKey = booleanPreferencesKey(key) // String 타입 저장 키값
        Log.d("dataStoreSaveBoolean", "$key:$value")
        dataStore.edit { preferences ->
            preferences[booleanKey] = value
        }
    }
    suspend fun loadStringData(key: String): String? {
        val stringKey = stringPreferencesKey(key) // String 타입 저장 키값
        val preferences = dataStore.data.first()
        Log.d("dataStoreLoadString", "$key:" + preferences[stringKey])
        return preferences[stringKey]
    }

    suspend fun loadIntData(key: String): Int? {
        val intKey = intPreferencesKey(key) // String 타입 저장 키값
        val preferences = dataStore.data.first()
        Log.d("dataStoreLoadInt", "$key:" + preferences[intKey])
        return preferences[intKey]
    }
    suspend fun loadBooleanData(key: String): Boolean? {
        val booleanKey = booleanPreferencesKey(key) // String 타입 저장 키값
        val preferences = dataStore.data.first()
        Log.d("dataStoreLoadBoolean", "$key:" + preferences[booleanKey])
        return preferences[booleanKey]
    }

    suspend fun clearDataStore() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    // 앱이 처음 생성되는 순간, SP를 새로 만들어주고, 레트로핏 인스턴스를 생성합니다.
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
            applicationContext.getSharedPreferences("GARAMGAEBI_APP", MODE_PRIVATE)
        KakaoSdk.init(this, "${BuildConfig.KAKAO_API_KEY}")
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()
        settingScreenPortrait()
        val dataStore = applicationContext.dataStore

    }
    private fun settingScreenPortrait() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            @SuppressLint("SourceLockedOrientationActivity")
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                // 화면 세로모드
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            override fun onActivityStarted(p0: Activity) {}
            override fun onActivityResumed(p0: Activity) {}
            override fun onActivityPaused(p0: Activity) {}
            override fun onActivityStopped(p0: Activity) {}
            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
            override fun onActivityDestroyed(p0: Activity) {}
        })
    }
    // 레트로핏 인스턴스를 생성하고, 레트로핏에 각종 설정값들을 지정해줍니다.
    // 연결 타임아웃시간은 5초로 지정이 되어있고, HttpLoggingInterceptor를 붙여서 어떤 요청이 나가고 들어오는지를 보여줍니다.
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}