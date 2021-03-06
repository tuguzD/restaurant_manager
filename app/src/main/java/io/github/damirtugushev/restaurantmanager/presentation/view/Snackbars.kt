package io.github.damirtugushev.restaurantmanager.presentation.view

import android.view.View
import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Makes a [Snackbar] with short duration.
 *
 * @see Snackbar.LENGTH_SHORT
 */
@CheckResult
internal inline fun snackbarShort(view: View, text: () -> CharSequence, ): Snackbar =
    Snackbar.make(view, text(), Snackbar.LENGTH_SHORT)

@CheckResult
internal inline fun Fragment.snackbarShort(text: () -> CharSequence) =
    snackbarShort(view = requireView(), text)
