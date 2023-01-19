package com.softsquared.template.Garamgaebi.src.main.networking_game


import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingGameSelectBinding
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingActivity

class NetworkingGameSelectActivity : BaseActivity<ActivityNetworkingGameSelectBinding>(ActivityNetworkingGameSelectBinding::inflate) {


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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkingGameSelectAdapter = NetworkingGameSelectAdapter(networkGameSelectList)
        binding.activityNetworkGameRv.apply {
            adapter = networkingGameSelectAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(NetworkingGameSelectVerticalItemDecoration())
        }

        networkingGameSelectAdapter.setOnItemClickListener(object : NetworkingGameSelectAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@NetworkingGameSelectActivity, NetworkingGamePlaceActivity::class.java)
                val temp = networkGameSelectList[position].place
                intent.putExtra("game_place", temp)
                startActivity(intent)
            }
            
        })

        //뒤로가기 버튼 누르면 네트워킹 메인 화면으로
        binding.activityGameBackBtn.setOnClickListener {
            val intent = Intent(this@NetworkingGameSelectActivity, NetworkingActivity::class.java)
            startActivity(intent)
        }

    }
}