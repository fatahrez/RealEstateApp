package com.example.guryihii.model.network

data class NetworkProperties(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<RealEstateProperty>
)