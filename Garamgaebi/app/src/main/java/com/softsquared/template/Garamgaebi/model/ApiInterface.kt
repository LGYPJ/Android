package com.softsquared.template.Garamgaebi.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    //SeminarController 세미나 컨트롤러

    //세미나 발표 리스트 조회
    @GET("/seminars/{seminar-idx}/presentations")
    suspend fun getSeminarsInfo(@Path("seminar-idx") seminaridx: Int): Response<SeminarPresentResponse>

    //세미나 신청자 리스트 조희
    //@GET("/seminars/{seminar-idx}/participants")
    //suspend fun getSeminarsInfo(@Path("seminar-idx") seminaridx: Int) : Response<SeminarPresentResponse>

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

    //마감된 세미나 조회
    @GET("/seminars/closed")
    suspend fun getGatheringSeminarClosed() : Response<GatheringSeminarClosedResponse>

    //NetworkingController 네트워킹 컨트롤러

    //네트워킹 신청자 리스트 조회
    //@GET("/networkings/{networking-idx}/participants")

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
    //@GET("/networkings/info")

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
    //@POST("/profile/sns")

    //고객센터 문의 요청
    //@POST("/profile/qna")

    //교육 추가
    //@POST("/profile/edit/{memberIdx}")

    //프로필 편집
    //@POST("/profile/sns")

    //경력 추가
    //@POST("/profile/career")

    //프로필 정보 조회
    //@GET("/profile/{memberIdx}")

    //SNS 정보 조회
    //@GET("/profile/sns/{memberIdx}")

    //프로필 10명 추천 조회
    @GET("/profile/profiles")
    suspend fun getHomeUser() : Response<HomeUserResponse>

    //프로필 교육 조회
    //@GET("/profile/education/{memberIdx}")

    //프로필 경력 조회
    //@GET("/profile/career/{memberIdx}")

    //MemberController 멤버 컨트롤러

    //멤버 추가
    //@POST("/member/post")

    //멤버 활동중 수정
    //@PATCH("/member/post")

    //EmailController 이메일 컨트롤러

    //이메일 유효성 확인 post
    //@POST("/email/{email}/emailconfirm")

    //ApplyController

    //신청 취소 post
    //@POST("/applies/programs/{id}/leave")

    //신청 등록 post
    //@POST("/applies/programs/{id}/leave")

    //SocialLoginController 소셜 로그인 컨트롤러

    //카카오 로그인 완료 조회
    //@GET("/member/kakao/callback")
}