import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidweatherforecast.Database.WeatherRepository
import com.example.weatherforecast.view.WeatherViewModel

/*
class WeatherViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
