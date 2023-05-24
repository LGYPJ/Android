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

/**
 <NetworkingGameSelectFragment>
 화면기능 : 아이스브레이킹 방 리스트 보여주는 공간

 클릭한 방의 게임이 이미 진행 O -> 이미 게임 시작한 방이라는 다이얼로그 띄움
                              참가하기 버튼 누르면 방에 들어가짐 -> 방 안의 카드는 시작하기가 나오지 않고 현재 차례인 카드가 나옴

 클릭한 방의 게임이 진행 X (처음 시작) -> 다이얼로그 없음
                                    방 안의 카드는 시작하기가 나옴

 */
class NetworkingGameSelectFragment: BaseFragment<FragmentNetworkingGameSelectBinding>(FragmentNetworkingGameSelectBinding::bind, R.layout.fragment_networking_game_select) {

    //화면전환
    var containerActivity: ContainerActivity? = null

    //뷰모델
    private val viewModel by viewModels<NetworkingGameViewModel>()

    private var networkGameSelectList: ArrayList<NetworkingGameSelect> = arrayListOf(
        NetworkingGameSelect(getString(R.string.game_place1)),
        NetworkingGameSelect(getString(R.string.game_place2)),
        NetworkingGameSelect(getString(R.string.game_place3)),
        NetworkingGameSelect(getString(R.string.game_place4)),
        NetworkingGameSelect(getString(R.string.game_place5)),
        NetworkingGameSelect(getString(R.string.game_place6)),
        NetworkingGameSelect(getString(R.string.game_place7)),
        NetworkingGameSelect(getString(R.string.game_place8))

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
    }

    override fun onDetach() {
        super.onDetach()
//        callback.handleOnBackPressed()
//        callback.remove()
    }

}