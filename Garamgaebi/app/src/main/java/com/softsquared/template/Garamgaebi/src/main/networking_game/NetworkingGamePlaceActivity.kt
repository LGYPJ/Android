package com.softsquared.template.Garamgaebi.src.main.networking_game


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingGamePlaceBinding

class NetworkingGamePlaceActivity : BaseActivity<ActivityNetworkingGamePlaceBinding>(ActivityNetworkingGamePlaceBinding::inflate) {

    private var networkingGameProfileList: ArrayList<NetworkingGameProfile> = arrayListOf(
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "cindy"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "neon"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "이채원"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "신디"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "유진아"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "로건"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "짱구"),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "찹도")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val networkingGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList, false)
        binding.activityGameProfileRv.apply {
            adapter = networkingGameProfile
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NetworkingGameProfileHorizontalItemDecoration())


        }

        /*val networkGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList, true)
        binding.activityGameProfileRv.apply {
            adapter = networkGameProfile
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        }*/


        /*데이터 클래스 자체 변수 만듬 --> 클릭 됨 (index 변수, 현재 선택 유무 데이터 클래스에)*/



        for(item in 0..networkingGameProfileList.size){

            binding.activityGamePlaceCardNextBtn.setOnClickListener {

                val networkGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList , true)
                binding.activityGameProfileRv.apply{
                    adapter = networkGameProfile
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    networkingGameProfile.notifyItemChanged(item)
                }

            }

                /*if(networkingGameProfileList){

                    val networkGameProfile = NetworkingGameProfileAdapter( networkingGameProfileList, false)
                    binding.activityGameProfileRv.apply {
                        adapter = networkGameProfile
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        networkGameProfile.notifyItemChanged(item)


                    }
                }
                else {
                    val networkGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList, true)
                    binding.activityGameProfileRv.apply {
                        adapter = networkGameProfile
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    }
                }

            }*/



        }
        


        /*binding.activityGamePlaceCardNextBtn.setOnClickListener {


            for(item in 0..networkingGameProfileList.size){

                val networkGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList, false)
                binding.activityGameProfileRv.apply {
                    adapter = networkGameProfile
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                }

                val network2GameProfile = NetworkingGameProfileAdapter(networkingGameProfileList, true)
                binding.activityGameProfileRv.apply {
                    adapter = network2GameProfile
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


                }
                break
            }
        }*/

        /*val networkingGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList, false)
        binding.activityGameProfileRv.apply {
            adapter = networkingGameProfile
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NetworkingGameProfileHorizontalItemDecoration())


        }*/


    }
}