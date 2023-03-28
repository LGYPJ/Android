package com.garamgaebi.garamgaebi.viewModel

import android.service.autofill.LuhnChecksumValidator
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
import kotlinx.coroutines.runBlocking
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent


class NetworkingGameViewModel: ViewModel() {


    private val SOCKET_URL = "ws://garamgaebi.shop:8080/ws/game/websocket"
    //private val MSSAGE_DESTINATION = "/topic/game/room" // 소켓

    // roomId
    private val roomId = GaramgaebiApplication.sSharedPreferences.getString("roomId", null)

    //memberIdx
    private val memberIdx = GaramgaebiApplication.myMemberIdx
    //currentIdx
    //private val currentIdx = GaramgaebiApplication.sSharedPreferences.getInt("currentIdx", 0)
    //programIdx

    private val gameRepository = GameRepository()

    private lateinit var mStompClient: StompClient
    private val gson = Gson()

    private val _message = MutableLiveData<MessageV0>()
    val message: LiveData<MessageV0>
        get() = _message

    private val _patchMessage = MutableLiveData<MessageV0>()
    val patchMessage: LiveData<MessageV0>
        get() = _patchMessage

    private val _deleteMessage = MutableLiveData<MessageV0>()
    val deleteMessage : LiveData<MessageV0>
        get() = _deleteMessage

    private val _startMessage = MutableLiveData<MessageV0>()
    val startMessage : LiveData<MessageV0>
        get() = _startMessage

    private val _postMember = MutableLiveData<GameMemberPostResponse>()
    val postMember: LiveData<GameMemberPostResponse>
        get() = _postMember

    private val _postMemberReq = MutableLiveData<GameMemberPostRequest>()
    val postMemberReq: LiveData<GameMemberPostRequest>
        get() = _postMemberReq

    private val _deleteMember = MutableLiveData<GameMemberDeleteResponse>()
    val deleteMember: LiveData<GameMemberDeleteResponse>
        get() = _deleteMember

    private val _deleteMemberReq = MutableLiveData<GameMemberDeleteRequest>()
    val deleteMemberReq: LiveData<GameMemberDeleteRequest>
        get() = _deleteMemberReq

    private val _getRoom = MutableLiveData<GameRoomResponse>()
    val getRoom: LiveData<GameRoomResponse>
        get() = _getRoom

    private val _getImg = MutableLiveData<List<String>>()
    val getImg: LiveData<List<String>>
        get() = _getImg

    private val _getMember = MutableLiveData<List<GameMemberGetResult>>()
    val getMember: LiveData<List<GameMemberGetResult>>
        get() = _getMember

    private val _getDeleteMember = MutableLiveData<List<GameMemberGetResult>>()
    val getDeleteMember: LiveData<List<GameMemberGetResult>>
        get() = _getDeleteMember

    private val _getMemberIndex = MutableLiveData<GameMemberGetResult>()
    val getMemberIndex: LiveData<GameMemberGetResult>
        get() = _getMemberIndex


    private val _getMemberReq = MutableLiveData<GameMemberGetRequest>()
    val getMemberReq: LiveData<GameMemberGetRequest>
        get() = _getMemberReq

    private val _patchCurrent = MutableLiveData<GameCurrentIdxResponse>()
    val patchCurrent: LiveData<GameCurrentIdxResponse>
        get() = _patchCurrent

    private val _postGameIsStarted = MutableLiveData<GameIsStartedResponse>()
    val postGameIsStarted: LiveData<GameIsStartedResponse>
        get() = _postGameIsStarted

    private val _patchGameStart = MutableLiveData<GameStartGameResponse>()
    val patchGameStart: LiveData<GameStartGameResponse>
        get() = _patchGameStart

    private val patchCurrentReq: GameCurrentIdxRequest? = null


    //index증가
    private val _index = MutableLiveData<Int?>()
    val index: MutableLiveData<Int?>
        get() = _index

    private val indexIncrease: Int? = null


    //private var index = GaramgaebiApplication.sSharedPreferences.getInt("currentIdx", 0)


    // room 조회
    fun getRoomId() {
        viewModelScope.launch(Dispatchers.Main) {
            /*var programIdx = 0
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!*/

            val response = gameRepository.getGameRoom(2)
            if (response.isSuccessful) {
                _getRoom.value = response.body()
            } else {
                Log.d("error", response.message())
            }
        }
    }

    // 게임방 유저 등록 post (game/member)
    fun postGameMember() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameMemberPostRequest(it) }
                ?.let { gameRepository.postGameMember(it) }
            if (response != null) {
                if (response.isSuccessful) {
                    if(response.body()?.result?.currentImgIdx != null){
                        _postMember.value = response.body()
                        Log.d("gameMember2", response.body()?.result.toString())
                    }
                } else {
                    Log.d("error", response.message())
                }
            }
        }
    }

    //delete
    fun postDeleteMember(gameMemberDeleteRequest: GameMemberDeleteRequest) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gameRepository.deleteGameMember(gameMemberDeleteRequest)
            Log.d("deletepostMember", response.body().toString())
            if (response != null) {
                if (response.isSuccessful) {
                    Log.d("deletepostMemberwhy", "why")
                    _deleteMember.value = response.body()
                } else {
                    Log.d("deleteerror", response.message())
                }
            }
        }
    }

    //image
    fun getImage() {
        viewModelScope.launch(Dispatchers.Main) {
            /*var programIdx = 0
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!*/

            val response = gameRepository.getGameImage(2)
            Log.d("img", response.body()?.result.toString())
            if (response.isSuccessful) {
                _getImg.value = response.body()?.result
            } else {
                Log.d("error", response.message())
            }
        }
    }

    //current-idx
    fun patchGameCurrentIdx(gameCurrentIdxRequest: GameCurrentIdxRequest) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gameRepository.patchGameCurrentIdx(gameCurrentIdxRequest)
            if (response != null) {
                if (response.isSuccessful) {
                    _patchCurrent.value = response.body()
                } else {
                    Log.d("error", response.message())
                }
            }
        }
    }

    // 현재 유저 조회 post  (game/members)
    fun getGameMember() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameMemberGetRequest(it) }
                ?.let { gameRepository.getGameMember(it) }

            if (response != null) {
                if (response.isSuccessful) {
                    _getMember.value = response.body()?.result
                    Log.d("gameMember", response.body()?.result.toString())
                    //_profile.postValue(false)
                    //number.value = number.value?.plus(1)
                } else {
                    Log.d("error", response.message())
                }
            }
        }
    }

    fun getDeleteGameMember() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameMemberGetRequest(it) }
                ?.let { gameRepository.getGameMember(it) }

            if (response != null) {
                if (response.isSuccessful) {
                    _getDeleteMember.value = response.body()?.result
                    Log.d("gameDeleteMember", response.body()?.result.toString())
                    //_profile.postValue(false)
                    //number.value = number.value?.plus(1)
                } else {
                    Log.d("error", response.message())
                }
            }
        }
    }

    // 게임방 진행중 유무 조회
    fun postGameIsStarted() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameIsStartedRequest(it) }
                ?.let { gameRepository.postGameIsStarted(it) }

            if (response != null) {
                if (response.isSuccessful) {
                    _postGameIsStarted.value = response.body()
                } else {
                    Log.d("error", response.message())
                }
            }

        }
    }

    // 게임방 상태 진행중으로 변경
    fun patchGameStart() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = roomId?.let { GameStartGameRequest(it) }
                ?.let { gameRepository.patchGameStart(it) }

            if (response != null) {
                if (response.isSuccessful) {
                    _patchGameStart.value = response.body()
                } else {
                    Log.d("error", response.message())
                }
            }
        }
    }

    //index 증가 함수
    fun indexIncrease() {
        viewModelScope.launch(Dispatchers.Main) {
            _index.postValue(indexIncrease)

        }
    }


    fun connectStomp() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, SOCKET_URL)
        mStompClient.connect()
        val stompConnection: Disposable =
            mStompClient.lifecycle().subscribe { lifecycleEvent: LifecycleEvent ->
                when (lifecycleEvent.type) {
                    LifecycleEvent.Type.OPENED -> Log.i(
                        "socket",
                        "Stomp connection opened"
                    )
                    LifecycleEvent.Type.ERROR -> {
                        Log.i(
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
            .subscribe { stompMessage ->
                Log.d("whywhywhy", "whywhywhy")
                val messageV0 = gson.fromJson(stompMessage.payload, MessageV0::class.java)
                Log.d("messagesend", messageV0.type)
                if(messageV0.type == "ENTER"){
                    _message.postValue(messageV0)
                    getGameMember()
                }
                if(messageV0.type == "EXIT"){
                    Log.d("deletemessageEXIT", "deletemessageEXIT")
                    _deleteMessage.postValue(messageV0)
                    getGameMember()
                }
                if(messageV0.type == "NEXT"){
                    Log.d("deletemessageNext", "deletemessageNext")
                    Log.d("indexpatch", "why")
                    _patchMessage.postValue(messageV0)

                }
                if(messageV0.type == "START"){
                    Log.d("deletemessageSTART", "deletemessageSTART")
                    _startMessage.postValue(messageV0)
                    getGameMember()
                }

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
                val message = gson.fromJson(stompMessage.payload, Message::class.java)
                _patchMessage.postValue(message)
                if (patchCurrentReq != null) {
                    patchGameCurrentIdx(patchCurrentReq)
                }
            }
    }*/


    fun sendMessage() {   // 구독 하는 방과 같은 주소로 메세지 전송
        val messageVO = roomId?.let { MessageV0("ENTER", it, "zzangu", "", "") }
        val messageJson: String = gson.toJson(messageVO)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")

    }

    fun sendStartMessage() {   // 구독 하는 방과 같은 주소로 메세지 전송
        val message3 = roomId?.let { MessageV0("START", it, "zzangu", "", "") }
        val messageJson: String = gson.toJson(message3)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")

    }

    fun sendDeleteMessage() {
        val message2 = roomId?.let { MessageV0("EXIT", it, "zzangu", memberIdx.toString(), "") }
        val messageJson: String = gson.toJson(message2)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")

    }

    fun sendCurrentIdxMessage(next: Int) {
        // userList에서 자신의 memberIdx를 찾고 그 다음 사람을 message에..!
        val message = roomId?.let { MessageV0("NEXT", it, "zzangu", next.toString(), "") }
        val messageJson: String = gson.toJson(message)
        val stompSend: Disposable = mStompClient.send("/app/game/message", messageJson).subscribe()
        Log.i("send", "send messageData : $messageJson")

    }


    fun disconnectStomp() {
        mStompClient.disconnect()
        Log.d("disconnect", "wow")
    }


}