package com.garamgaebi.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.garamgaebi.garamgaebi.databinding.FragmentSnsOrderBottomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SnsOrderBottomDialogFragment (val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentSnsOrderBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSnsOrderBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentDialogTvInstagram.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvBlog.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvGithub.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvUserInput.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}