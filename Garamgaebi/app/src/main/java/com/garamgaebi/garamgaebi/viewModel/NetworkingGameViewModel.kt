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
    //currentIdx
    //private val currentIdx = GaramgaebiApplication.sSharedPreferences.getInt("currentIdx", 0)

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

    private val _getImg = MutableLiveData<List<String>>()
    val getImg : LiveData<List<String>>
    get() = _getImg

    private val _getMember = MutableLiveData<List<GameMemberGetResult>>()
    val getMember : LiveData<List<GameMemberGetResult>>
    get() = _getMember

    private val _getMemberReq = MutableLiveData<GameMemberGetRequest>()
    val getMemberReq : LiveData<GameMemberGetRequest>
    get() = _getMemberReq

    private val _getMemberRe = MutableLiveData<List<GameMemberGetResultRe>>()
    val getMemberRe : LiveData<List<GameMemberGetResultRe>>
    get() = _getMemberRe

    private val _patchCurrent = MutableLiveData<GameCurrentIdxResponse>()
    val patchCurrent : LiveData<GameCurrentIdxResponse>
    get() = _patchCurrent

    //private var index = GaramgaebiApplication.sSharedPreferences.getInt("currentIdx", 0)


    // room 조회
    fun getRoomId(){
        viewModelScope.launch(Dispatchers.Main){
            val response = gameRepository.getGameRoom(20)
            if(response.isSuccessful){
                _getRoom.value = response.body()
            }
            else{
                Log.d("error", response.message())
            }
        }
    }

    // 게임방 유저 등록 post (game/member)
    fun postGameMember(){
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameMemberPostRequest(it, memberIdx ) }
                ?.let { gameRepository.postGameMember(it) }
            if (response != null) {
                if(response.isSuccessful){
                    _postMember.value = response.body()
                    // currrentIdx 보내는 거
                    /*response.body()?.result?.currentImgIdx?.let {
                        GaramgaebiApplication.sSharedPreferences
                            .edit().putInt("currentIdx", it)
                            .apply()
                    }*/
                } else{
                    Log.d("error", response.message())
                }
            }
        }
    }

    //delete
    fun postDeleteMember(){
        viewModelScope.launch(Dispatchers.Main){
            val response = roomId?.let { GameMemberDeleteRequest(it, memberIdx) }
                ?.let { gameRepository.deleteGameMember(it) }
            if (response != null) {
                if(response.isSuccessful){
                    _deleteMember.value = response.body()
                } else{
                    Log.d("error", response.message())
                }
            }
        }
    }

    //image
    fun getImage(){
        viewModelScope.launch(Dispatchers.Main) {
            val response = gameRepository.getGameImage(20)
            Log.d("img", response.body()?.result.toString())
            if(response.isSuccessful){
                _getImg.value = response.body()?.result
            }else{
                Log.d("error", response.message())
            }
        }
    }

    //current-idx
    fun patchGameCurrentIdx(){
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameCurrentIdxRequest(it) }
                ?.let { gameRepository.patchGameCurrentIdx(it) }
            if (response != null) {
                if(response.isSuccessful){
                    _patchCurrent.value = response.body()
                }
                else{
                    Log.d("error", response.message())
                }
            }
        }
    }

    // 현재 유저 조회 post  (game/members)
    fun getGameMember(){
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameMemberGetRequest(it) }
                ?.let { gameRepository.getGameMember(it) }

            if (response != null) {
                if(response.isSuccessful){
                    _getMember.value = response.body()?.result
                    Log.d("gameMember", response.body()?.result.toString())
                    //_profile.postValue(false)
                    //number.value = number.value?.plus(1)
                } else{
                    Log.d("error", response.message())
                }
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
        // 구독
        val stompSubscribe: Disposable = mStompClient.topic("/topic/game/room" + "/" + GaramgaebiApplication.sSharedPreferences.getString("roomId", null))
            .subscribe {stompMessage ->
                //val messageV0 = gson.fromJson(stompMessage.payload, MessageV0::class.java)
                getGameMember()

            }
    }

    /*fun connectStomp1(){
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

        // 구독
        val stompSubscribe: Disposable = mStompClient.topic("/topic/game/room" + "/" + GaramgaebiApplication.sSharedPreferences.getString("roomId", null))
            .subscribe {stompMessage ->
                //val messageV0 = gson.fromJson(stompMessage.payload, MessageV0::class.java)
                patchGameCurrentIdx()
            }
    }*/


    fun sendMessage() {   // 구독 하는 방과 같은 주소로 메세지 전송
        val messageVO = roomId?.let { MessageV0("ENTER", it,"zzangu", "","") }
        val messageJson: String = gson.toJson(messageVO)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")
    }

    fun sendDeleteMessage() {   // 구독 하는 방과 같은 주소로 메세지 전송
        val messageVO = roomId?.let { MessageV0("EXIT", it,"zzangu", "","") }
        val messageJson: String = gson.toJson(messageVO)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")
    }

    fun sendCurrentIdxMessage(){
        val messageVO = roomId?.let { MessageV0("TALK", it,"zzangu", "NEXT","") }
        val messageJson: String = gson.toJson(messageVO)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")
    }

    fun disconnectStomp(){
        mStompClient.disconnect()
        Log.d("disconnect", "wow")
    }


}