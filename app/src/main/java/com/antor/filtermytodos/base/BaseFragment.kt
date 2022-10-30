package com.antor.filtermytodos.base

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {
    private var _binding: VB? = null

    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw IllegalArgumentException("Binding cannot be null")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onVewCreated(requireView(), savedInstanceState)
    }

    abstract fun onVewCreated(view: View, savedInstanceState: Bundle?)


    fun showMessageSnackbar(message: String?) {
        val imm: InputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "" + message,
            Snackbar.LENGTH_LONG
        )
            .show()
    }

    fun showProgressBar(isLoading: Boolean, view: View) {
        if (isLoading) {
            view.visibility = View.VISIBLE
            requireActivity().window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            view.visibility = View.GONE
            requireActivity().window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    fun show(view: View) {
       view.visibility=View.VISIBLE
    }

    fun hide(view: View) {
        view.visibility=View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // region navigation
    protected fun navigateToDestination(navDirections: NavDirections) {
        try {
            findNavController().navigate(navDirections)
        } catch (e: Exception) {
            Log.d("TAG", "navigateToDestination Exception: ${e.message}")
        }
    }

    protected fun navigateBack() {
        try {
            findNavController().navigateUp()
        } catch (e: Exception) {
            Log.d("TAG", "navigateToBack Exception: ${e.message}")
        }
    }


    protected fun navigateToDestinationWithBundleNavOptions(
        destinationId: Int,
        bundle: Bundle? = null,
        navOptions: NavOptions? = null
    ) {
        try {
            findNavController().navigate(destinationId, bundle, navOptions)
        } catch (e: Exception) {
            Log.d("TAG", "navigateToDestinationWithBundleNavOptions Exception: ${e.message}")
        }
    }

    protected fun navigateToDestinationWithBundleNavOptions(
        navController: NavController,
        destinationId: Int,
        bundle: Bundle? = null,
        navOptions: NavOptions? = null
    ) {
        try {
            navController.navigate(destinationId, bundle, navOptions)
        } catch (e: Exception) {
            Log.d("TAG", "navigateToDestinationWithBundleNavOptions: ${e.message} ")
        }
    }


}