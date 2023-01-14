package com.softsquared.template.Garamgaebi.src.main.home

import android.os.Bundle
import android.view.View
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentHomeBinding
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingProfile


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var seminarDataList: ArrayList<HomeSeminarItemData> = arrayListOf(
            HomeSeminarItemData(true, "로건 세미나", "xxxx-xx-xx", "pp", 1),
            HomeSeminarItemData(false, "신디 세미나", "xxxx-xx-xx", "pp", 2)
        )
    }
}