package com.garamgaebi.garamgaebi.util

import android.os.Bundle
import android.view.View
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkDisconnectedBinding

class NetworkDisconnectedFragment : BaseFragment<FragmentNetworkDisconnectedBinding>(
    FragmentNetworkDisconnectedBinding::bind,
    R.layout.fragment_network_disconnected) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}