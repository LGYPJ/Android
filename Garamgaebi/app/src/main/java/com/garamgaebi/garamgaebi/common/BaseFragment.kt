package com.garamgaebi.garamgaebi.common

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.util.LoadingDialog
import io.reactivex.rxjava3.disposables.CompositeDisposable

// Fragment의 기본을 작성, 뷰 바인딩 활용
@Suppress("UNREACHABLE_CODE")
abstract class BaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes val layoutResId: Int
) : Fragment(layoutResId) {
    private var _binding: B? = null
    //abstract val layoutResId: Int
    lateinit var mLoadingDialog: LoadingDialog

    protected val binding get() = _binding!!
    var disposables = CompositeDisposable()
    val networkValid = MutableLiveData<Boolean>()
    init { networkValid.value = true}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //_binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNetwork()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun observeNetwork(){
        networkValid.observe(viewLifecycleOwner) {
            Log.d("network_check",it.toString())
            if(it == false) {
                NetworkErrorDialog() { it ->
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
        }
    }
    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }
    fun checkNetwork(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo == null || !networkInfo.isConnected) {
            Log.d("network","false")
            return false
        }else {
            Log.d("network","true")
            return true

        }
    }


    override fun onStop() {
        super.onStop()
        if (disposables.size() > 0) {
            disposables.clear()
        }
    }


}