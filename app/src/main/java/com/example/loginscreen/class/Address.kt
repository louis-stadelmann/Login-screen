package com.example.apirestcall.`class`

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("street")
    val street: String,

    @SerializedName("suite")
    val suite: String,

    @SerializedName("title")
    val city: String,

    @SerializedName("zipCode")
    val zipCode: String)
