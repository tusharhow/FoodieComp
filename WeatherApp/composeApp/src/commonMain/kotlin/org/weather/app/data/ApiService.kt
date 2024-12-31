package org.weather.app.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.weather.app.models.Weather

class ApiService {
     val APP_KEY = "508a0119dfeeeb18b66d70800990b101\n"
    private val httpClient =  HttpClient {
        install(ContentNegotiation){
            json(Json{
                encodeDefaults = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchWeatherByLatLng(lat: Long, lng: Long): Weather {
        return httpClient.get("https://api.openweathermap.org/data/2.5/weather?"){
            parameter("lat", lat)
            parameter("lang", lng)
            parameter("appid", APP_KEY)
        }.body()

    }
}