package de.softdeveloper.jsondemo

import com.google.gson.annotations.SerializedName

data class Phone(
    val mobile: String?,
    val office: String?,
    @SerializedName(value = "private")
    val private_: String?
)