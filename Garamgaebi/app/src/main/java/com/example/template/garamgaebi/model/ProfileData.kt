package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.config.BaseResponse

data class BooleanResponse (
    @SerializedName("result") val result : Boolean
):BaseResponse()

//SNS 추가
data class SNSData (
    @SerializedName("memberIdx") val memberIdx : Int = 0,
    @SerializedName("address") val address : String
)
data class AddSNSDataResponse(
    @SerializedName("result") val result : Boolean
):BaseResponse()

//고객센터 QnA신청
data class QnAData (
    @SerializedName("memberIdx") val memberIdx : Int = 0,
    @SerializedName("email") val email : String,
    @SerializedName("category") val category : String,
    @SerializedName("content") val content : String
)
data class QnADataResponse(
    @SerializedName("result") val result : Boolean
):BaseResponse()

//교육 추가
data class EducationData (
    @SerializedName("memberIdx") val memberIdx : Int = 0,
    @SerializedName("institution") val institution : String,
    @SerializedName("major") val major : String,
    @SerializedName("isLearning") val isLearning : String,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String
)
data class AddEducationDataResponse(
    @SerializedName("result") val result : Boolean
):BaseResponse()

//프로필 편집
data class ProfileData (
    @SerializedName("memberIdx") val memberIdx : Int = 0,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("belong") val belong : String,
    @SerializedName("profileEmail") var profileEmail : String,
    @SerializedName("content") val content : String,
    @SerializedName("profileUrl") val profileUrl : String
)
data class EditProfileDataResponse(
    @SerializedName("result") val result : Boolean
):BaseResponse()

//경력 추가
data class CareerData (
    @SerializedName("memberIdx") val memberIdx : Int = 0,
    @SerializedName("company") val company : String,
    @SerializedName("position") val position : String,
    @SerializedName("isWorking") val isWorking : String,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String
)
data class AddCareerDataResponse(
    @SerializedName("result") val result : Boolean
):BaseResponse()

//프로필 확인
data class ProfileDataResponse(
    @SerializedName("result") val result : ProfileData
):BaseResponse()

//SNS 확인
data class SNSDataResponse (
    @SerializedName("result") val result: ArrayList<SNSData>
) : BaseResponse()

//프로필 10명 추천
data class RecommendProfileData (
    @SerializedName("memberIdx") val memberIdx : Int = 0,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("profileUrl") val profileUrl : String,
    @SerializedName("belong") val belong : String
)
data class RecommendProfileDataResponse(
    @SerializedName("result") val result: ArrayList<RecommendProfileData>
) : BaseResponse()

//교육 조회
data class EducationDataResponse(
    @SerializedName("result") val result: ArrayList<EducationData>
) : BaseResponse()

//경력 조회
data class CareerDataResponse(
    @SerializedName("result") val result: ArrayList<CareerData>
) : BaseResponse()