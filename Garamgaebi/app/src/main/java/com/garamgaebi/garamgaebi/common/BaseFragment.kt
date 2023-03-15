package com.garamgaebi.garamgaebi.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.garamgaebi.garamgaebi.util.LoadingDialog
import io.reactivex.rxjava3.disposables.CompositeDisposable

// Fragment의 기본을 작성, 뷰 바인딩 활용
abstract class BaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes val layoutResId: Int
) : Fragment(layoutResId) {
    private var _binding: B? = null
    //abstract val layoutResId: Int
    lateinit var mLoadingDialog: LoadingDialog
    lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils

    protected val binding get() = _binding!!
    var disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //_binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
            },
            onHideKeyboard = { ->
                // binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
            }
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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
    override fun onStop() {
        super.onStop()
        if (disposables.size() > 0) {
            disposables.clear()
        }
    }
    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        super.onDestroy()
    }

}