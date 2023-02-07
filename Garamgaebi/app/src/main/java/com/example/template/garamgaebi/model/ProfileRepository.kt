package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.common.GaramgaebiApplication

class ProfileRepository {
    private val profileClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun getCheckAddSNS(snsData: SNSData) = profileClient.getCheckAddSNS(snsData)
    suspend fun getCheckQnA(qnsData: QnAData) = profileClient.getCheckQnA(qnsData)
    suspend fun getCheckAddEducation(educationData: EducationData) = profileClient.getCheckAddEducation(educationData)
    suspend fun getCheckEditProfile(profileData: ProfileData) = profileClient.getCheckEditProfile(profileData)
    suspend fun getCheckAddCareer(careerData: CareerData) = profileClient.getCheckAddCareer(careerData)
    suspend fun getProfileInfo(memberIdx: Int) = profileClient.getProfileInfo(memberIdx)
    suspend fun getSNSInfo(memberIdx: Int) = profileClient.getSNSInfo(memberIdx)
    //프로필 추천
    suspend fun getEducationInfo(memberIdx: Int) = profileClient.getEducationInfo(memberIdx)
    suspend fun getCareerInfo(memberIdx: Int) = profileClient.getCareerInfo(memberIdx)
}