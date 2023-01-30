package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentNetworkingBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentNetworkingGameSelectBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingActivity

class NetworkingGameSelectFragment: BaseFragment<FragmentNetworkingGameSelectBinding>(FragmentNetworkingGameSelectBinding::bind, R.layout.fragment_networking_game_select) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    //private lateinit var viewModel: ItemViewModel

    private var networkGameSelectList: ArrayList<NetworkingGameSelect> = arrayListOf(
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("AI 공학관"),
        NetworkingGameSelect("비전타워"),
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("제3기숙사"),
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("글로벌 센터"),
        NetworkingGameSelect("가천관")

    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkingGameSelectAdapter = NetworkingGameSelectAdapter(networkGameSelectList)
        binding.activityNetworkGameRv.apply {
            adapter = networkingGameSelectAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(NetworkingGameSelectVerticalItemDecoration())
        }

        //viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        networkingGameSelectAdapter.setOnItemClickListener(object : NetworkingGameSelectAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                containerActivity!!.openFragmentOnFrameLayout(8)
                val temp = networkGameSelectList[position].place
                containerActivity!!.networkingPlace(temp)

            }

        })


    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity


    }

}