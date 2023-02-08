package com.example.guryihii.model.domain

import java.math.BigDecimal

data class Property(
    val city: String,
    val country: String,
    val id: Int,
    val price: BigDecimal,
    val profile_photo: String,
    val slug: String,
    val street_address: String,
    val photo1: String,
    val title: String,
)
