package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.damirtugushev.restaurantmanager.presentation.model.MealData
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderItemViewModel : ViewModel() {
    var order: OrderData? = null

    private var list = listOf<MealData>()
    private val mutableLiveData = MutableLiveData(list)

    val allMeals: LiveData<List<MealData>> = mutableLiveData

    fun addMeal(mealData: MealData) {
        viewModelScope.launch {
            list = list + mealData
            withContext(Dispatchers.Main) {
                mutableLiveData.value = list
            }
        }
    }
}
