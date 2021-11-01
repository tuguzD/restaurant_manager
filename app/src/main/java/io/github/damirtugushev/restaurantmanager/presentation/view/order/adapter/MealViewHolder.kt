package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.damirtugushev.restaurantmanager.databinding.ItemMealBinding
import io.github.damirtugushev.restaurantmanager.presentation.model.MealData

class MealViewHolder(private val binding: ItemMealBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var _meal: MealData
    private inline val meal get() = _meal

    fun bind(meal: MealData) {
        _meal = meal
        binding.run {
            name.text = meal.name
            description.text = meal.description
            Picasso.get()
                .load(meal.imageUrl)
                .into(imageView)
            imageView.visibility = View.VISIBLE
        }
    }
}
