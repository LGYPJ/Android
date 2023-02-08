package com.example.template.garamgaebi.src.main.networking_game

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.NetworkingGameSelectAdapter
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentNetworkingGameSelectBinding
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.NetworkingGameViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.garamgaebi.viewModel.SeminarViewModel

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

        //웹소켓 구현
        val viewModel = ViewModelProvider(this)[NetworkingGameViewModel::class.java]

        viewModel.connectStomp("cindy")
        viewModel.message.observe(viewLifecycleOwner, Observer { it ->

        })


    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity


    }

}