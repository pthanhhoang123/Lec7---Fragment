package com.example.lec7_fragment

import java.io.Serializable

data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val category: String
) : Serializable
