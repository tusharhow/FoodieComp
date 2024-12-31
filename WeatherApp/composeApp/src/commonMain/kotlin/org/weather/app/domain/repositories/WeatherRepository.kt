package org.weather.app.domain.repositories

import org.weather.app.data.ApiService
import org.weather.app.models.Weather

interface WeatherRepository {
    suspend fun getWeatherByLatLng(lat: Long, lng: Long): Weather
}