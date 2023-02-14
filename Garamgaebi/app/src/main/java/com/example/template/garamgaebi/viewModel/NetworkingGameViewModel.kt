package com.example.template.garamgaebi.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.GameRepository
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.text.SimpleDateFormat
import java.util.*

class NetworkingGameViewModel: ViewModel() {


    private val SOCKET_URL = "ws://garamgaebi.shop:8080/ws/game/websocket"
    //private val MSSAGE_DESTINATION = "/topic/game/room" // 소켓

    // roomId
    private val roomId = GaramgaebiApplication.sSharedPreferences.getString("roomId", null)

    private val gameRepository = GameRepository()

    private lateinit var mStompClient: StompClient
    private val gson = Gson()

    private val _message = MutableLiveData<MessageV0>()
    val message: LiveData<MessageV0>
        get() = _message

    private val _postMember = MutableLiveData<GameMemberPostResponse>()
    val postMember : LiveData<GameMemberPostResponse>
    get() = _postMember

    private val _postMemberReq = MutableLiveData<GameMemberPostRequest>()
    val postMemberReq : LiveData<GameMemberPostRequest>
    get() = _postMemberReq

    private val _deleteMember = MutableLiveData<GameMemberDeleteResponse>()
    val deleteMember : LiveData<GameMemberDeleteResponse>
    get() = _deleteMember

    private val _deleteMemberReq = MutableLiveData<GameMemberDeleteRequest>()
    val deleteMemberReq : LiveData<GameMemberDeleteRequest>
    get() = _deleteMemberReq

    private val _getRoom = MutableLiveData<GameRoomResponse>()
    val getRoom : LiveData<GameRoomResponse>
    get() = _getRoom

    private val _getImg = MutableLiveData<GameImagesResponse>()
    val getImg : LiveData<GameImagesResponse>
    get() = _getImg

    private val _getMember = MutableLiveData<GameMemberGetResponse>()
    val getMember : LiveData<GameMemberGetResponse>
    get() = _getMember

    private val _getMemberReq = MutableLiveData<GameMemberGetRequest>()
    val getMemberReq : LiveData<GameMemberGetRequest>
    get() = _getMemberReq


    // room 조회
    fun getRoomId(){
        viewModelScope.launch(Dispatchers.IO){
            val response = gameRepository.getGameRoom(20)
            if(response.isSuccessful){
                _getRoom.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }
        }
    }

    // post

    fun connectStomp() {
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, SOCKET_URL)
            mStompClient.connect()
            val stompConnection: Disposable = mStompClient.lifecycle().subscribe { lifecycleEvent: LifecycleEvent ->
                when (lifecycleEvent.type) {
                    LifecycleEvent.Type.OPENED -> Log.i(
                        TAG,
                        "Stomp connection opened"
                    )
                    LifecycleEvent.Type.ERROR -> { Log.i(
                        TAG, "Error",
                        lifecycleEvent.exception
                    )
                    }
                    LifecycleEvent.Type.CLOSED -> Log.i(
                        TAG,
                        "Stomp connection closed"
                    )
                    LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> Log.i(
                        TAG,
                        "FAILED_SERVER_HEARTBEAT"
                    )
                }
            }

            val stompSubscribe: Disposable = mStompClient.topic("/topic/game/room" + "/" + GaramgaebiApplication.sSharedPreferences.getString("roomId", null))
                .subscribe { stompMessage ->
                    Log.i("subscribe", "receive messageData :" + stompMessage.payload)
                    /*val messageV0 = gson.fromJson(stompMessage.payload, MessageV0::class.java)
                    _message.postValue(messageV0)*/
                }



    }

    fun sendMessage() {   // 구독 하는 방과 같은 주소로 메세지 전송
        val messageVO = roomId?.let { MessageV0("ENTER", it,"zzangu", "","") }
        val messageJson: String = gson.toJson(messageVO)
        mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")
    }

    fun disconnectStomp(){
        mStompClient.disconnect()
    }


}