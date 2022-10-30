package com.antor.filtermytodos.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.antor.domain.model.TodoModel
import com.antor.domain.model.UserModel
import com.antor.filtermytodos.R
import com.antor.filtermytodos.base.BaseFragment
import com.antor.filtermytodos.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>
    (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private var todoList: ArrayList<TodoModel> = arrayListOf()
    var userList: ArrayList<UserModel> = arrayListOf()
    var newList: ArrayList<TodoModel> = arrayListOf()

    private val userListSpinnerAdapter by lazy {
        UserSpinnerAdapter(
            requireContext(),
            R.layout.vh_user_spinner_item,
            userList
        )
    }

    private val todoListAdapter by lazy {
        TodoListAdapter(
            requireContext(),
            newList
        )
    }

    override fun onVewCreated(view: View, savedInstanceState: Bundle?) {
        initUserSpinner()
        initTodoListRecyclerView()

        viewModel.getUserListData()
        initGetUserListObserver()

        viewModel.getAllTodoListData()
        initGetTodoListObserver()


    }

    private fun initTodoListRecyclerView() {
        binding.todoListRV.apply {
            binding.todoListRV.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            binding.todoListRV.adapter = todoListAdapter
        }
    }

    private fun initUserSpinner() {
        binding.spinner.adapter = userListSpinnerAdapter
        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    showToastMessage("noting")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    filterTodoList(userList[position].id)
                }

            }

    }

    private fun filterTodoList(id: Int) {
        newList.clear()
        for (i in 0 until todoList.size) {

            if (todoList[i].userId == id){
                newList.add(todoList[i])
            }
        }
        todoListAdapter.notifyDataSetChanged()


    }


    private fun initGetUserListObserver() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.getUserListData.collect {
                if (it.isLoading) {
                    Log.d("TAG", "initUserOtoObserver: Loading....")
                    showProgressBar(it.isLoading, binding.progressBar)
                }
                if (it.error.isNotBlank()) {
                    Log.d("TAG", "initUserOtoObserver: Error !!! ${it.error}")
                    showProgressBar(it.isLoading, binding.progressBar)
                    showToastMessage(it.error)
                }

                Log.d("TAG", "initUserOtoObserver: Success ${it.userList}")
                showProgressBar(it.isLoading, binding.progressBar)
                userList.clear()
                it.userList?.let { it1 -> userList.addAll(it1) }
                userListSpinnerAdapter.notifyDataSetChanged()


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
                    showToastMessage(it.error)
                }

                Log.d("TAG", "initTODOObserver: Success ${it.todoList}")
                todoList.clear()
                it.todoList?.let { it1 -> todoList.addAll(it1) }
                todoListAdapter.notifyDataSetChanged()
            }
        }

    }


}