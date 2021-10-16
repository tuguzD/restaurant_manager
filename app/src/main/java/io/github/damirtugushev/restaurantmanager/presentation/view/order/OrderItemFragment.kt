package io.github.damirtugushev.restaurantmanager.presentation.view.order

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderItemBinding
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.view.hasOptionsMenu
import io.github.damirtugushev.restaurantmanager.presentation.view.snackbarShort
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderItemViewModel
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderListViewModel

class OrderItemFragment : Fragment() {
    private val args: OrderItemFragmentArgs by navArgs()
    private val listViewModel: OrderListViewModel by viewModels()
    private val itemViewModel: OrderItemViewModel by viewModels()

    private var _binding: FragmentOrderItemBinding? = null

    // This helper property is only valid between
    // `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderItemBinding.inflate(inflater, container, false)
        hasOptionsMenu = true

        itemViewModel.order = args.order
        val order = args.order
        binding.run {
            tableNumber.text = root.resources.getString(
                R.string.table_number_is, order.tableNumber
            )
            guestsNumber.text = root.resources.getString(
                R.string.guests_number_is, order.guestsNumber
            )

            button.setOnClickListener {
                itemViewModel.order?.let {
                    val open = Intent(Intent.ACTION_VIEW).apply {
                        flags = flags or
                                Intent.FLAG_GRANT_READ_URI_PERMISSION or
                                Intent.FLAG_ACTIVITY_NEW_TASK
                        data = Uri.parse(it.documentUri)
                    }
                    startActivity(Intent.createChooser(open, "Open with..."))
                }
            }

            order.documentUri?.let {
                val fileName = getFileName(Uri.parse(it))
                if (fileName != null) {
                    button.visibility = View.VISIBLE
                    button.text = root.resources.getString(
                        R.string.open_document, fileName
                    )
                }
                return@run
            }
            button.visibility = View.GONE
        }

        binding.fab.setOnClickListener {
            val action = OrderItemFragmentDirections.actionMealAddFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun getFileName(uri: Uri): String? {
        var name: String? = null

        requireActivity().contentResolver.query(
            uri, null, null, null, null
        )?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            name = cursor.getString(nameIndex)
            cursor.close()
        }
        return name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.order_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.order_item_share -> {
            val order = args.order
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                val link = "https://android.restaurant_manager/orders/${order.nanoId}"
                putExtra(Intent.EXTRA_TEXT, link)
                type = "text/plain"
            }
            val title = getString(R.string.share)
            val shareIntent = Intent.createChooser(sendIntent, title)
            startActivity(shareIntent)
            true
        }

        R.id.order_item_document -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val activity = requireActivity()
                activity.activityResultRegistry
                    .register("key", ActivityResultContracts.OpenDocument())
                    {
                        it?.let { uri ->
                            activity.applicationContext.contentResolver.takePersistableUriPermission(
                                uri, Intent.FLAG_GRANT_READ_URI_PERMISSION,
                            )
                            val order = OrderData(
                                args.order.nanoId,
                                args.order.tableNumber,
                                args.order.guestsNumber,
                                uri.toString(),
                            )
                            listViewModel.updateOrder(order)
                            itemViewModel.order = order

                            val fileName = getFileName(uri)
                            if (fileName != null) {
                                binding.button.visibility = View.VISIBLE
                                binding.button.text = binding.root.resources.getString(
                                    R.string.open_document, fileName
                                )
                            }
                        }
                    }.launch(arrayOf(
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                        "application/msword", "text/plain",
                    ))
                true
            } else {
                snackbarShort { "This is supported only on Android devices above 4.4 KitKat" }.show()
                super.onOptionsItemSelected(item)
            }
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
