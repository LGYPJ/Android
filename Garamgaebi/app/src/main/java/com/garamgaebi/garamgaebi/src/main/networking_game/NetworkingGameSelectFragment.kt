package com.garamgaebi.garamgaebi.src.main.networking_game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.NetworkingGameSelectAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingGameSelectBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.NetworkingGameViewModel
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.model.GameIsStartedRequest

class NetworkingGameSelectFragment: BaseFragment<FragmentNetworkingGameSelectBinding>(FragmentNetworkingGameSelectBinding::bind, R.layout.fragment_networking_game_select) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    //private lateinit var viewModel: ItemViewModel
    //뷰모델
    private val viewModel by viewModels<NetworkingGameViewModel>()

    //private lateinit var callback: OnBackPressedCallback

    private var networkGameSelectList: ArrayList<NetworkingGameSelect> = arrayListOf(
        NetworkingGameSelect("이길여 총장님"),
        NetworkingGameSelect("AI 공학관"),
        NetworkingGameSelect("비전타워"),
        NetworkingGameSelect("스타덤 광장"),
        NetworkingGameSelect("바람개비 동산"),
        NetworkingGameSelect("무당이"),
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("무한대 동상")

    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getRoomId()
        viewModel.getRoom.observe(viewLifecycleOwner, Observer{ it ->
            val networkingGameSelectAdapter = NetworkingGameSelectAdapter(networkGameSelectList)
            binding.activityNetworkGameRv.apply {
                adapter = networkingGameSelectAdapter
                layoutManager = GridLayoutManager(context, 2)
                //addItemDecoration(NetworkingGameSelectVerticalItemDecoration())
            }
            networkingGameSelectAdapter.setOnItemClickListener(object : NetworkingGameSelectAdapter.OnItemClickListener {
                override fun onClick(position: Int) {
                    val why = it.result[position].roomId
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putString("roomId", why)
                        .apply()

                    val roomId = GaramgaebiApplication.sSharedPreferences.getString("roomId", null)
                    roomId?.let { it1 -> GameIsStartedRequest(it1) }
                        ?.let { it2 -> viewModel.postGameIsStarted(it2) }

                    viewModel.postGameIsStarted.observe(viewLifecycleOwner, Observer {game ->
                        if(game.result){
                            NetworkingGameDialog().show(requireActivity().supportFragmentManager, "icebreaking")
                            val why = it.result[position].roomId
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putString("roomId", why)
                                .apply()
                            val temp = networkGameSelectList[position].place
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putString("placeName", temp)
                                .apply()

                        }
                        else{
                            containerActivity!!.openFragmentOnFrameLayout(8)
                            val temp = networkGameSelectList[position].place
                            //roomId 부여
                            val why = it.result[position].roomId
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putString("roomId", why)
                                .apply()
                            Log.d("roomId", why)
                            //val temp = networkGameSelectList[position]
                            Log.d("placeName", temp)
                            containerActivity!!.networkingPlace(temp)
                        }
                    })


                }

            })

        })




    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
//        callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                (activity as ContainerActivity).openFragmentOnFrameLayout(5)
//                (activity as ContainerActivity).supportFragmentManager.beginTransaction().remove(NetworkingGameSelectFragment()).commit()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this,callback)
    }

    override fun onDetach() {
        super.onDetach()
//        callback.handleOnBackPressed()
//        callback.remove()
    }

}