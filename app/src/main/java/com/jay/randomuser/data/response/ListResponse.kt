package com.jay.randomuser.data.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(

    @field:SerializedName("results")
    val results: List<T>,

    @field:SerializedName("info")
    val info: Info
) {

    data class Info(
        @field:SerializedName("seed")
        val seed: String,

        @field:SerializedName("results")
        val results: Int,

        @field:SerializedName("page")
        val page: Int,

        @field:SerializedName("version")
        val version: String
    )

}