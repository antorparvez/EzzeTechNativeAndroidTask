package com.antor.filtermytodos.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.antor.domain.model.UserModel

class UserSpinnerAdapter(
    context: Context,
    textViewResourceId: Int,
    private val list: List<UserModel>
) : ArrayAdapter<UserModel>(
    context,
    textViewResourceId,
    list
) {
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = list[position].id!!.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (super.getDropDownView(position, convertView, parent) as TextView).apply {
            text = list[position].name
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (super.getDropDownView(position, convertView, parent) as TextView).apply {
            text = list[position].name
        }
    }
}