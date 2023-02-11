package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse
import com.example.template.garamgaebi.src.main.seminar.data.SeminarParticipantsResponse
import com.example.template.garamgaebi.src.main.seminar.data.SeminarPresentResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    //NotificationController 알림 컨트롤러
    @GET("/notification/{member-idx}")
    suspend fun getNotification(
        @Path("member-idx") memberIdx: Int,
        @Query("lastNotificationIdx") lastNotificationIdx: Int) : Response<NotificationResponse>

    @GET("/notification/{member-idx}")
    suspend fun getNotification(
        @Path("member-idx") memberIdx: Int) : Response<NotificationResponse>

    @GET("/notification/unread/{member-idx}")
    suspend fun getNotificationUnread(@Path("member-idx") memberIdx: Int) : Response<NotificationUnreadResponse>

    //SeminarController 세미나 컨트롤러

    //세미나 발표 리스트 조회
    @GET("/seminars/{seminar-idx}/presentations")
    suspend fun getSeminarsInfo(@Path("seminar-idx") seminarIdx: Int): Response<SeminarPresentResponse>

    //세미나 신청자 리스트 조희
    @GET("/seminars/{seminar-idx}/participants")
    suspend fun getSeminarParticipants(@Path("seminar-idx") seminaridx: Int, @Query("member-idx") memberIdx: Int) : Response<SeminarParticipantsResponse>

    //이번 달 세미나 조회
    @GET("/seminars/this-month")
    suspend fun getGatheringSeminarThisMonth() : Response<GatheringSeminarResponse>

    //예정된 세미나 조희
    @GET("/seminars/next-month")
    suspend fun getGatheringSeminarNextMonth() : Response<GatheringSeminarResponse>

    //홈 화면 세미나 조회
    @GET("/seminars/main")
    suspend fun getHomeSeminar() : Response<HomeSeminarResponse>

    //세미나 상세정보 조회
    //@GET("/seminars/info")

    //세미나 상세정보 조회
    @GET("/seminars/{seminar-idx}/info")
    suspend fun getSeminarDetail(@Path("seminar-idx") seminarIdx: Int, @Query("member-idx")memberIdx: Int): Response<SeminarDetailInfoResponse>


    //마감된 세미나 조회
    @GET("/seminars/closed")
    suspend fun getGatheringSeminarClosed() : Response<GatheringSeminarClosedResponse>

    //NetworkingController 네트워킹 컨트롤러

    //네트워킹 신청자 리스트 조회
    @GET("/networkings/{networking-idx}/participants")
    suspend fun getNetworkingParticipants(@Path("networking-idx")networkingIdx : Int, @Query("member-idx") memberIdx: Int) : Response<NetworkingParticipantsResponse>

    //이번 달 네트워킹 조회
    @GET("/networkings/this-month")
    suspend fun getGatheringNetworkingThisMonth() : Response<GatheringNetworkingResponse>

    //예정된 네트워킹 조회
    @GET("/networkings/next-month")
    suspend fun getGatheringNetworkingNextMonth() : Response<GatheringNetworkingResponse>

    //홈 화면 네트워킹 조회
    @GET("/networkings/main")
    suspend fun getHomeNetworking() : Response<HomeNetworkingResponse>

    //네트워킹 상세정보 조회
    @GET("/networkings/{networking-idx}/info")
    suspend fun getNetworkingInfo(@Path("networking-idx")networkingIdx: Int, @Query("member-idx")memberIdx: Int) : Response<NetworkingInfoResponse>

    //마감된 네트워킹 리스트 조회
    @GET("/networkings/closed")
    suspend fun getGatheringNetworkingClosed() : Response<GatheringNetworkingClosedResponse>

    //ProgramController 프로그램 컨트롤러

    //예정된 내 모임 조회
    @GET("/programs/{member-idx}/ready")
    suspend fun getHomeProgram(@Path("member-idx") memberIdx: Int) : Response<HomeProgramResponse>
    @GET("/programs/{member-idx}/ready")
    suspend fun getGatheringProgramReady(@Path("member-idx") memberIdx: Int) : Response<GatheringProgramResponse>

    //지난 내 모임 조회
    @GET("/programs/{member-idx}/close")
    suspend fun getGatheringProgramClosed(@Path("member-idx") memberIdx: Int) : Response<GatheringProgramResponse>

    //ProfileController 프로필 컨트롤라

    //SNS 추가
    @POST("/profile/sns")
    suspend fun getCheckAddSNS(
        //@Header("accessToken") accessToken : String,
        @Body request: SNSData
    ): Response<AddSNSDataResponse>

    //고객센터 문의 요청
    @POST("/profile/qna")
    suspend fun getCheckQnA(
        @Body request: QnAData
    ): Response<QnADataResponse>

    //프로필 사진 저장/수정
    @POST("/profile/images")
    suspend fun getCheckEditProfileImg(
        /*
        {
  "info": {
    "memberIdx": 0,
    "nickName": "string"
  },
  "image": "string"
}
         */
    ):Response<BooleanResponse>

    //교육 추가
    @POST("/profile/education")
    suspend fun getCheckAddEducation(
        @Body request: EducationData
    ): Response<AddEducationDataResponse>

    //프로필 편집
    @POST("/profile/edit/{memberIdx}")
    suspend fun getCheckEditProfile(
        @Body request: ProfileData
    ): Response<EditProfileDataResponse>

    //경력 추가
    @POST("/profile/career")
    suspend fun getCheckAddCareer(
        @Body request: CareerData
    ): Response<AddCareerDataResponse>

    //프로필 정보 조회
    @GET("/profile/{memberIdx}")
    suspend fun getProfileInfo(
        @Path("memberIdx") memberIdx: Int
    ): Response<ProfileDataResponse>

    //SNS 정보 조회
    @GET("/profile/sns/{memberIdx}")
    suspend fun getSNSInfo(
        @Path("memberIdx") memberIdx: Int
    ): Response<SNSDataResponse>

    //프로필 10명 추천 조회
    @GET("/profile/profiles")
    suspend fun getHomeUser() : Response<HomeUserResponse>

    //프로필 교육 조회
    @GET("/profile/education/{memberIdx}")
    suspend fun getEducationInfo(
        @Path("memberIdx") memberIdx: Int
    ): Response<EducationDataResponse>

    //프로필 경력 조회
    @GET("/profile/career/{memberIdx}")
    suspend fun getCareerInfo(
        @Path("memberIdx") memberIdx: Int
    ): Response<CareerDataResponse>


    //MemberController 멤버 컨트롤러

    //멤버 추가
    //@POST("/member/post")

    //멤버 활동중 수정
    //@PATCH("/member/post")

    //EmailController 이메일 컨트롤러

    //이메일 유효성 확인 post
    //@POST("/email/{email}/emailconfirm")

    //MemberController
    @POST("/member/login")
    fun postLogin(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    //ApplyController

    //신청 취소 post
    @POST("/applies/programs/leave")
    suspend fun postCancel(@Body cancelRequest : CancelRequest) : Response<CancelResponse>

    //신청 등록 post
    @POST("/applies/programs/enroll")
    suspend fun postEnroll(@Body enrollRequest : EnrollRequest) : Response<EnrollResponse>

    //SocialLoginController 소셜 로그인 컨트롤러

    //카카오 로그인 완료 조회
    //@GET("/member/kakao/callback")

    //신청 정보 조회
    @GET("/applies/{member-idx}/{program-idx}/info")
    suspend fun getCancel( @Path("member-idx") memberIdx: Int, @Path("program-idx") programIdx: Int) : Response<CancelInfoResponse>
}