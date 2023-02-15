package com.garamgaebi.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.model.*
import okhttp3.MultipartBody

class ProfileRepository {
    private val profileClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun getCheckAddSNS(snsData: AddSNSData) = profileClient.getCheckAddSNS(snsData)
    suspend fun patchSNS(snsData: SNSData) = profileClient.patchSNS(snsData)
    suspend fun deleteSNS(snsIdx: Int) = profileClient.deleteSNS(snsIdx)

    suspend fun getCheckQnA(qnsData: QnAData) = profileClient.getCheckQnA(qnsData)
    suspend fun getCheckAddEducation(educationData: AddEducationData) = profileClient.getCheckAddEducation(educationData)
    suspend fun patchEducation(educationData: EducationData) = profileClient.patchEducation(educationData)
    suspend fun deleteEducation(educationIdx: Int) = profileClient.deleteEducation(educationIdx)

    suspend fun getCheckAddCareer(careerData: AddCareerData) = profileClient.getCheckAddCareer(careerData)
    suspend fun patchCareer(careerData: CareerData) = profileClient.patchCareer(careerData)
    suspend fun deleteCareer(careerIdx: Int) = profileClient.deleteCareer(careerIdx)
    suspend fun getProfileInfo(memberIdx: Int) = profileClient.getProfileInfo(memberIdx)
    suspend fun getCheckEditProfileInfo(editProfileData: EditProfileInfoData, image : MultipartBody.Part) = profileClient.getCheckEditProfile(editProfileData,image)
    suspend fun getSNSInfo(memberIdx: Int) = profileClient.getSNSInfo(memberIdx)
    //프로필 추천
    suspend fun getEducationInfo(memberIdx: Int) = profileClient.getEducationInfo(memberIdx)
    suspend fun getCareerInfo(memberIdx: Int) = profileClient.getCareerInfo(memberIdx)
}