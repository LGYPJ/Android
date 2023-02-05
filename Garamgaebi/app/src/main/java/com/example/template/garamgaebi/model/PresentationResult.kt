package com.example.template.garamgaebi.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName


class PresentationResult(
    @SerializedName("presentationIdx") val presentationIdx : Int,
    @SerializedName("title") val title: String,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImgUrl")val profileImgUrl : String,
    @SerializedName("organization") val organization : String,
    @SerializedName("content") val content : String,
    @SerializedName("presentationUrl") val presentationUrl : String

)
