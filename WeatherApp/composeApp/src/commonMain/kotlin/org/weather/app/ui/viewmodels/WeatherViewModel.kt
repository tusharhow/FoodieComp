package org.weather.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.weather.app.domain.usecases.GetWeatherUseCase
import org.weather.app.models.Weather
import org.weather.app.utils.Resource

class WeatherViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _weatherState = MutableStateFlow<Resource<Weather>>(Resource.Loading())
    val weatherState: StateFlow<Resource<Weather>> = _weatherState


    fun fetchWeather(lat: Long, lng: Long) {
        viewModelScope.launch {
            try {
                _weatherState.value = Resource.Loading()
                val weatherData = getWeatherUseCase.execute(lat, lng)
                _weatherState.value = Resource.Success(weatherData)
            } catch (e: Exception) {
                _weatherState.value = Resource.Error(e.message ?: "An error occurred")
            }
        }
    }
}
