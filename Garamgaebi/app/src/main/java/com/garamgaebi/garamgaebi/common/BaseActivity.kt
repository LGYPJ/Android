package com.garamgaebi.garamgaebi.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.garamgaebi.garamgaebi.util.LoadingDialog

// 액티비티의 기본을 작성, 뷰 바인딩 활용
abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B
        private set
    lateinit var mLoadingDialog: LoadingDialog
    lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    val networkValid : MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    private val networkCallback = NetworkConnectionCallback()
    // 뷰 바인딩 객체를 받아서 inflate해서 화면을 만들어줌.
    // 즉 매번 onCreate에서 setContentView를 하지 않아도 됨.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNetworkCallback(this)
        binding = inflate(layoutInflater)
        keyboardVisibilityUtils = KeyboardVisibilityUtils(this.window,
            onShowKeyboard = { keyboardHeight ->
            },
            onHideKeyboard = {
                // binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
            }
        )

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

    }
    fun networkAlertDialog(){
        NetworkErrorDialog() { it ->
            when (it) {
                -1 -> {
                }
                1 -> {
                    //(activity as ContainerActivity).onBackPressed()
                }
            }
        }.show(
            supportFragmentManager!!,
            "com.example.garamgaebi.common.NetworkErrorDialog"
        )
    }
    // 홈 로딩 다이얼로그
    // 네트워크가 시작될 때 사용자가 무작정 기다리게 하지 않기 위해 작성.
    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }
    // 띄워 놓은 홈 로딩 다이얼로그를 없앰.
    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        unregisterNetworkCallback(this)
        super.onDestroy()
    }

    // 토스트를 쉽게 띄울 수 있게 해줌.
    fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inner class NetworkConnectionCallback : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("network", "onAvailable")
            networkValid.postValue(true)
        }
        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("network", "onLost")
            networkValid.postValue(false)
        }
    }
    private fun registerNetworkCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun unregisterNetworkCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}