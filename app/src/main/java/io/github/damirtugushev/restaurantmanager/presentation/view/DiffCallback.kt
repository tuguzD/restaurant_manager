package io.github.damirtugushev.restaurantmanager.presentation.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import io.github.damirtugushev.restaurantmanager.domain.model.Identifiable

/**
 * Utility class for item comparing used by [DiffUtil].
 *
 * @see DiffUtil.ItemCallback
 */
class DiffCallback<out I, T : Identifiable<I>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.nanoId == newItem.nanoId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
