package com.antor.filtermytodos.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.antor.filtermytodos.base.BaseFragment
import com.antor.filtermytodos.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>
    (FragmentHomeBinding::inflate)
{

    private val viewModel: HomeViewModel by viewModels()

    override fun onVewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getUserListData()
        initGetUserListObserver()

        viewModel.getAllTodoListData()
        initGetTodoListObserver()


    }


    private fun initGetUserListObserver() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.getUserListData.collect {
                if (it.isLoading) {
                    Log.d("TAG", "initUserOtoObserver: Loading....")
                }
                if (it.error.isNotBlank()) {
                    Log.d("TAG", "initUserOtoObserver: Error !!! ${it.error}")
                }

                Log.d("TAG", "initUserOtoObserver: Success ${it.userList}")
            }
        }

    }

    private fun initGetTodoListObserver() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.getAllTodoListData.collect {
                if (it.isLoading) {
                    Log.d("TAG", "initTODOObserver: Loading....")
                }
                if (it.error.isNotBlank()) {
                    Log.d("TAG", "initTODOObserver: Error !!! ${it.error}")
                }

                Log.d("TAG", "initTODOObserver: Success ${it.userList}")
            }
        }

    }


}