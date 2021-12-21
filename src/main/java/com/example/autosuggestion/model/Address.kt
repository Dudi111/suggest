package com.example.autosuggestion.model

data class Address(
    val addressString: String,
    val city: String,
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val pinCode: String,
    val source: String
)