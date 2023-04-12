package com.garamgaebi.garamgaebi.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
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
    // 서버 주소
    val API_URL = "https://garamgaebi.shop/"

    companion object {
        private lateinit var appInstance: GaramgaebiApplication
        fun getApplication() = appInstance

        lateinit var sSharedPreferences: SharedPreferences
        lateinit var myDataStore: DataStore<Preferences>
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")
        val gameOut : MutableLiveData<Boolean> = MutableLiveData(false)
        const val testEmail = "garamgaebiMaster2"
        const val testPW = "000000"

        // 네트워크 감지
        val networkValid : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
        private val networkCallback = NetworkConnectionCallback()

        // JWT Token Header 키 값
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val X_REFRESH_TOKEN = "X_REFRESH_TOKEN"
        var myMemberIdx = 0

        var getProfile = true
        var getSNS = true
        var getCareer = true
        var getEdu = true

        var bitmap: Bitmap? = null

        //초기 해쉬맵 객체 선언
        var fragmentHashMap = HashMap<Int, String>()

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit
    }
    suspend fun saveStringToDataStore(key: String, value: String) {
        val stringKey = stringPreferencesKey(key) // String 타입 저장 키값
        dataStore.edit { preferences ->
            preferences[stringKey] = value
        }
    }
    suspend fun saveIntToDataStore(key: String, value: Int) {
        val intKey = intPreferencesKey(key) // String 타입 저장 키값
        dataStore.edit { preferences ->
            preferences[intKey] = value
        }
    }

    suspend fun saveBooleanToDataStore(key: String, value: Boolean) {
        val booleanKey = booleanPreferencesKey(key) // String 타입 저장 키값
        dataStore.edit { preferences ->
            preferences[booleanKey] = value
        }
    }
    suspend fun loadStringData(key: String): String? {
        val stringKey = stringPreferencesKey(key) // String 타입 저장 키값
        val preferences = dataStore.data.first()
        return preferences[stringKey]
    }

    suspend fun loadIntData(key: String): Int? {
        val intKey = intPreferencesKey(key) // String 타입 저장 키값
        val preferences = dataStore.data.first()
        return preferences[intKey]
    }
    suspend fun loadBooleanData(key: String): Boolean? {
        val booleanKey = booleanPreferencesKey(key) // String 타입 저장 키값
        val preferences = dataStore.data.first()
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
        registerNetworkCallback(this)
        sSharedPreferences =
            applicationContext.getSharedPreferences("GARAMGAEBI_APP", MODE_PRIVATE)
        KakaoSdk.init(this, "${BuildConfig.KAKAO_API_KEY}")
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()
        settingScreenPortrait()
        val dataStore = applicationContext.dataStore
        setHashMap()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
    override fun onTerminate() {
        unregisterNetworkCallback(applicationContext)
        super.onTerminate()
    }
    fun setHashMap(){
        //put 데이터 추가 실시
        fragmentHashMap[SEMINAR] = "세미나"
        fragmentHashMap[SEMINAR_APPLY_FREE] = "세미나"
        fragmentHashMap[SEMINAR_APPLY_CHARGED] = "세미나"
        fragmentHashMap[NETWORKING] = "네트워킹"
        fragmentHashMap[NETWORKING_APPLY_FREE] = "네트워킹"

        fragmentHashMap[NETWORKING_APPLY_CHARGED] = "네트워킹"
        fragmentHashMap[CANCEL] = "신청 취소"
        fragmentHashMap[ICEBREAKING] = "아이스브레이킹"
        fragmentHashMap[PROFILE_EDIT] = "프로필 편집"
        fragmentHashMap[SNS_ADD] = "SNS 추가하기"

        fragmentHashMap[SNS_EDIT] = "SNS 편집하기"
        fragmentHashMap[CAREER_ADD] = "경력 추가하기"
        fragmentHashMap[CAREER_EDIT] = "경력 편집하기"
        fragmentHashMap[EDU_ADD] = "교육 추가하기"
        fragmentHashMap[EDU_EDIT] = "교육 편집하기"

        fragmentHashMap[SERVICE_CENTER] = "고객 센터"
        fragmentHashMap[WITHDRAWAL] = "회원 탈퇴"
        fragmentHashMap[USER_PROFILE] = "프로필"
        fragmentHashMap[NOTIFICATION] = "알림"


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

    class NetworkConnectionCallback : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkValid.postValue(true)
        }
        override fun onLost(network: Network) {
            super.onLost(network)
            networkValid.postValue(false)
        }
    }
    private fun registerNetworkCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback as ConnectivityManager.NetworkCallback)
    }

    private fun unregisterNetworkCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.unregisterNetworkCallback(networkCallback as ConnectivityManager.NetworkCallback)
    }
}