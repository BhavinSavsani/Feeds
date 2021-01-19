package com.bhavinpracticalinfosys.model


import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("title")
    val title: String?,
    @SerializedName("rows")
    val rows: List<Row?>?
) {
    data class Row(
        @SerializedName("title")
        val title: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("imageHref")
        val imageHref: String?
    )
}