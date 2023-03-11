package app.sodeg.sodeg.core.util.jwt

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.nio.charset.StandardCharsets


class Jwt(private val token: String) {

    companion object {
        @JvmStatic
        private val gson = Gson()
    }

    private val userData: JsonObject by lazy {
        val userData = String(Base64.decode(token.split(".")[1], Base64.DEFAULT),
        StandardCharsets.UTF_8)
        JsonParser().parse(userData).asJsonObject
    }

    fun getUserData(): JwtPayload {
        return gson.fromJson(userData, JwtPayload::class.java)
    }

    fun isExpired(): Boolean {
        return userData.asJsonObject.get("exp").asLong < (System.currentTimeMillis() / 1000)
    }
}