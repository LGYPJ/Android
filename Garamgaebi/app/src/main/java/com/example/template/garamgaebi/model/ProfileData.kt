package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse

data class BooleanResponse (
    val result : Boolean
):BaseResponse()

//SNS 추가
data class SNSData (
    val memberIdx : Int = 0,
    val address : String = ""
)
data class AddSNSDataResponse(
    val result : Boolean
):BaseResponse()

//고객센터 QnA신청
data class QnAData (
    val memberIdx : Int = 0,
    val email : String,
    val category : String,
    val content : String
)
data class QnADataResponse(
    val result : Boolean
):BaseResponse()

//교육 추가
data class EducationData (
    val memberIdx : Int = 0,
    val institution : String,
    val major : String,
    val isLearning : String,
    val startDate : String,
    val endDate : String
)
data class AddEducationDataResponse(
    val result : Boolean
):BaseResponse()

//프로필 편집
data class ProfileData (
    val memberIdx : Int = 0,
    val nickName : String,
    val belong : String,
    var profileEmail : String,
    val content : String,
    val profileUrl : String
)
data class EditProfileDataResponse(
    val result : Boolean
):BaseResponse()

//경력 추가
data class CareerData (
    val memberIdx : Int = 0,
    val company : String,
    val position : String,
    val isWorking : String,
    val startDate : String,
    val endDate : String
)
data class AddCareerDataResponse(
    val result : Boolean
):BaseResponse()

//프로필 확인
data class ProfileDataResponse(
    val result : ProfileData
):BaseResponse()

//SNS 확인
data class SNSDataResponse (
    val result: List<SNSData>
) : BaseResponse()

//프로필 10명 추천
data class RecommendProfileData (
    val memberIdx : Int = 0,
    val nickName : String,
    val profileUrl : String,
    val belong : String
)
data class RecommendProfileDataResponse(
    val result: ArrayList<RecommendProfileData>
) : BaseResponse()

//교육 조회
data class EducationDataResponse(
    val result: List<EducationData>
) : BaseResponse()

//경력 조회
data class CareerDataResponse(
    val result: ArrayList<CareerData>
) : BaseResponse()