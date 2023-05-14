package com.garamgaebi.garamgaebi.src.main.profile

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.garamgaebi.garamgaebi.databinding.FragmentOrderBottomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class OrderBottomDialogFragment (val array : Array<String>, val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentOrderBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            fragmentDialogTvTitle.text = array[0]
            fragmentDialogTvQuestion.text = array[1]
            fragmentDialogTvReport.text = array[2]
            fragmentDialogTvService.text = array[3]
            fragmentDialogTvEtc.text = array[4]

        }
        binding.fragmentDialogTvQuestion.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvReport.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvService.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvEtc.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}