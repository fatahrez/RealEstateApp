package com.example.guryihii.feature_refresh_token.data.remote.dto

import com.example.guryihii.feature_refresh_token.domain.model.Token

data class TokenDTO(
    val access: String
) {
    fun toToken(): Token {
        return Token(
            access = access
        )
    }
}
