package org.weather.app.data.repository

import org.weather.app.data.ApiService
import org.weather.app.domain.repositories.WeatherRepository
import org.weather.app.models.Weather

class WeatherRepoImpl : WeatherRepository {
    private val apiService = ApiService()
    override suspend fun getWeatherByLatLng(lat: Long, lng: Long): Weather {
        return apiService.fetchWeatherByLatLng(lat, lng)
    }
}