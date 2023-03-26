package com.garamgaebi.garamgaebi.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseBindingFragment<T: ViewDataBinding>(@LayoutRes private val layoutId: Int): Fragment() {
    protected lateinit var binding: T
    private lateinit var callback: OnBackPressedCallback
    var disposables = CompositeDisposable()
    lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    val networkValid : MutableLiveData<Boolean> = MutableLiveData()
    private val networkCallback = NetworkConnectionCallback()
    init { networkValid.value = true}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }
    fun networkAlertDialog(){
        NetworkErrorDialog() {
            when (it) {
                -1 -> {
                }
                1 -> {
                    //(activity as ContainerActivity).onBackPressed()
                }
            }
        }.show(
            activity?.supportFragmentManager!!,
            "com.example.garamgaebi.common.NetworkErrorDialog"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        initListener()
        registerNetworkCallback(requireContext())
        afterViewCreated()
    }

    protected open fun initView() {}
    protected open fun initViewModel() {}
    protected open fun initListener() {}
    protected open fun afterViewCreated() {}

    override fun onStop() {
        super.onStop()
        if (disposables.size() > 0) {
            disposables.clear()
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = {
            },
            onHideKeyboard = {
                // binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
            }
        )
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
    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        unregisterNetworkCallback(requireContext())
        super.onDestroy()
    }
}