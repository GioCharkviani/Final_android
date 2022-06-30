package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName

//{
//            "id": 7,
//            "email": "michael.lawson@reqres.in",
//            "first_name": "Michael",
//            "last_name": "Lawson",
//            "avatar": "https://reqres.in/img/faces/7-image.jpg"
//        },
//"page": 2,
//    "per_page": 6,
//    "total": 12,
//    "total_pages": 2,

data class ReqResData<T>(
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    val data: T?
)

data class User(
    val id: Long?,
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val avatar: String?,
)