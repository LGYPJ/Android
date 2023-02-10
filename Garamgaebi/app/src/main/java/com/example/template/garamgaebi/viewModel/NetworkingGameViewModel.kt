package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.text.SimpleDateFormat
import java.util.*

class NetworkingGameViewModel: ViewModel() {
    //private val TAG = NetworkingGameViewModel::class.java.simpleName

    private val SOCKET_URL = "ws://garamgaebi.shop:8080/ws/game/websocket" // http = ws로 시작하며 https = wss로 시작
    //private val MSSAGE_DESTINATION = "/socket/message" // 소켓 주소

    private lateinit var mStompClient: StompClient
    private val gson = Gson()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    fun connectStomp(room: String) {

        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, SOCKET_URL)
        val stompConnection: Disposable = mStompClient.lifecycle().subscribe { lifecycleEvent: LifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> Log.i(
                    "socket",
                    "Stomp connection opened"
                )
                LifecycleEvent.Type.ERROR -> { Log.i(
                    "socket", "Error",
                    lifecycleEvent.exception
                )
                    connectStomp(room)
                }
                LifecycleEvent.Type.CLOSED -> Log.i(
                    "socket",
                    "Stomp connection closed"
                )
                LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> Log.i(
                    "socket",
                    "FAILED_SERVER_HEARTBEAT"
                )
            }
        }
        /*stompConnection= mStompClient.topic(MSSAGE_DESTINATION + "/" + room)
            .subscribe { stompMessage ->
                Log.d(TAG, "receive messageData :" + stompMessage.payload)
            }*/
        mStompClient.connect()


    }

    /*fun sendMessage(name: String, content: String, room: String) {   // 구독 하는 방과 같은 주소로 메세지 전송
        val time = SimpleDateFormat("k:mm").format(Date(System.currentTimeMillis()))
        //mStompClient.send(MSSAGE_DESTINATION + "/" + room, messageJson).subscribe()

    }*/

    fun disconnectStomp(){
        mStompClient.disconnect()
    }
}