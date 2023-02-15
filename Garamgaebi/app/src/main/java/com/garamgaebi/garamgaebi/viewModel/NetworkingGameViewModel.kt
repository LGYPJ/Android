package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.GameRepository
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent

class NetworkingGameViewModel: ViewModel() {


    private val SOCKET_URL = "ws://garamgaebi.shop:8080/ws/game/websocket"
    //private val MSSAGE_DESTINATION = "/topic/game/room" // 소켓

    // roomId
    private val roomId = GaramgaebiApplication.sSharedPreferences.getString("roomId", null)
    //memberIdx
    private val memberIdx = GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0)

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

    private val _getMember = MutableLiveData<List<GameMemberGetResult>>()
    val getMember : LiveData<List<GameMemberGetResult>>
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

    // 게임방 유저 등록 post (game/member)
    fun postGameMember(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = roomId?.let { GameMemberPostRequest(it, memberIdx ) }
                ?.let { gameRepository.postGameMember(it) }
            if (response != null) {
                if(response.isSuccessful){
                    _postMember.postValue(response.body())
                } else{
                    Log.d("error", response.message())
                }
            }
        }
    }

    // 현재 유저 조회 post  (game/members)
    fun getGameMember(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = roomId?.let { GameMemberGetRequest(it) }
                ?.let { gameRepository.getGameMember(it) }

            if (response != null) {
                if(response.isSuccessful){
                    _getMember.postValue(response.body()?.result)
                } else{
                    Log.d("error", response.message())
                }
            }
        }
    }
    // 유저입장 비동기 처리
    fun gameJoin(){
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                connectStomp()
            }
            launch {
                postGameMember()
            }
            launch {
                getGameMember()
            }
            launch {
                sendMessage()
            }
        }
    }

    fun connectStomp() {
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, SOCKET_URL)
            mStompClient.connect()
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

            /*val stompSubscribe: Disposable = mStompClient.topic("/topic/game/room" + "/" + GaramgaebiApplication.sSharedPreferences.getString("roomId", null))
                .subscribe { stompMessage ->
                    Log.i("subscribe", "receive messageData :" + stompMessage.payload)
                    /*val messageV0 = gson.fromJson(stompMessage.payload, MessageV0::class.java)
                    _message.postValue(messageV0)*/
                }*/

        // 구독
        val stompSubscribe: Disposable = mStompClient.topic("/topic/game/room" + "/" + GaramgaebiApplication.sSharedPreferences.getString("roomId", null))
            .subscribe {stompMessage ->
                //val messageV0 = gson.fromJson(stompMessage.payload, MessageV0::class.java)
                getGameMember()
            }



    }

    fun sendMessage() {   // 구독 하는 방과 같은 주소로 메세지 전송
        val messageVO = roomId?.let { MessageV0("ENTER", it,"zzangu", "","") }
        val messageJson: String = gson.toJson(messageVO)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")
    }

    fun disconnectStomp(){
        mStompClient.disconnect()
    }


}