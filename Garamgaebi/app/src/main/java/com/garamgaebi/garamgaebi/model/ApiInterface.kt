package com.garamgaebi.garamgaebi.model

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

//    suspend fun <T> sendRequest(apiCall: suspend () -> T): T {
//        return try {
//            apiCall()
//        } catch (e: TokenExpiredException) {
//            renewAccessToken()
//            apiCall()
//        }
//    }
//    private suspend fun renewAccessToken() {
//        val accessToken = tokenApi.renewAccessToken(tokenStore.refreshToken)
//        tokenStore.accessToken = accessToken
//    }


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


    // EmailController 이메일 컨트롤러
    @POST("/email/verify")
    suspend fun postEmailVerify(@Body request: RegisterEmailVerifyRequest) : Response<RegisterEmailResponse>

    @POST("/email/sendEmail")
    suspend fun postSendEmail(@Body request : RegisterSendEmailRequest) : Response<RegisterEmailResponse>

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
        @Body request: AddSNSData
    ): Response<AddSNSDataResponse>

    //SNS 수정
    @PATCH("/profile/sns")
    suspend fun patchSNS(
        //@Header("accessToken") accessToken : String,
        @Body request: SNSData
    ): Response<BooleanResponse>

    //SNS 삭제
    @DELETE("/profile/sns/{snsIdx}")
    suspend fun deleteSNS(
        //@Header("accessToken") accessToken : String,
        @Path("snsIdx") snsIdx: Int
    ): Response<BooleanResponse>

    //고객센터 문의 요청
    @POST("/profile/qna")
    suspend fun getCheckQnA(
        @Body request: QnAData
    ): Response<QnADataResponse>

    //로그아웃
    @POST("/member/logout")
    suspend fun getCheckLogout(
        @Body request : LogoutToken
    ):Response<LogOutResponse>

    //회원탈퇴
    @POST("/member/member-inactived")
    suspend fun getCheckWithdrawal(
        @Body request : InactiveMember
    ):Response<WithdrawalResponse>

    //프로필 편집 저장/수정
    @Multipart
    @POST("/profile/edit")
    suspend fun getCheckEditProfile(
        @Part("info")info : RequestBody,
        @Part image : MultipartBody.Part?
    ):Response<EditProfileDataResponse>

    //교육 추가
    @POST("/profile/education")
    suspend fun getCheckAddEducation(
        @Body request: AddEducationData
    ): Response<AddEducationDataResponse>

    //교육 수정
    @PATCH("/profile/education")
    suspend fun patchEducation(
        //@Header("accessToken") accessToken : String,
        @Body request: EducationData
    ): Response<BooleanResponse>

    //교육 삭제
    @DELETE("/profile/education/{educationIdx}")
    suspend fun deleteEducation(
        //@Header("accessToken") accessToken : String,
        @Path("educationIdx") educationIdx: Int
    ): Response<BooleanResponse>

    //경력 추가
    @POST("/profile/career")
    suspend fun getCheckAddCareer(
        @Body request: AddCareerData
    ): Response<AddCareerDataResponse>

    //경력 수정
    @PATCH("/profile/career")
    suspend fun patchCareer(
        //@Header("accessToken") accessToken : String,
        @Body request: CareerData
    ): Response<BooleanResponse>

    //경력 삭제
    @DELETE("/profile/career/{careerIdx}")
    suspend fun deleteCareer(
        //@Header("accessToken") accessToken : String,
        @Path("careerIdx") careerIdx: Int
    ): Response<BooleanResponse>


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
    @POST("/member/post")
    suspend fun postMember(@Body registerRequest: RegisterRequest) : Response<RegisterResponse>

    //멤버 활동중 수정
    //@PATCH("/member/post")


    //MemberController
    @POST("/member/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest) : Response<LoginResponse>
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


    //game member post
    @POST("/game/member")
    suspend fun postGameMember(@Body gameMemberPostRequest: GameMemberPostRequest) : Response<GameMemberPostResponse>

    //game member delete
    @HTTP(method = "DELETE", path="/game/member", hasBody = true)
    suspend fun deleteGameMember(@Body gameMemberDeleteRequest: GameMemberDeleteRequest) : Response<GameMemberDeleteResponse>

    //game room get
    @GET("/game/{programIdx}/rooms")
    suspend fun getGameRoom(@Path("programIdx") programIdx : Int) :Response<GameRoomResponse>

    //game image get
    @GET("/game/{programIdx}/images")
    suspend fun getGameImage(@Path("programIdx") programIdx : Int) : Response<GameImagesResponse>

    //game member post
    @POST("/game/members")
    suspend fun getGameMember(@Body gameMemberGetRequest: GameMemberGetRequest) : Response<GameMemberGetResponse>

    // game patch current-idx
    @PATCH("/game/current-idx")
    suspend fun patchGameCurrentIdx(@Body gameCurrentIdxRequest: GameCurrentIdxRequest) : Response<GameCurrentIdxResponse>
}