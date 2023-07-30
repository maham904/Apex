package com.apex.codeassesment.data.model.Response


import com.apex.codeassesment.data.model.User
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(

    @SerializedName("results") var results: List<User> ,
    @SerializedName("info") var info: Info? = Info()

)

data class Info (

    @SerializedName("seed"    ) var seed    : String? = null,
    @SerializedName("results" ) var results : Int?    = null,
    @SerializedName("page"    ) var page    : Int?    = null,
    @SerializedName("version" ) var version : String? = null

)