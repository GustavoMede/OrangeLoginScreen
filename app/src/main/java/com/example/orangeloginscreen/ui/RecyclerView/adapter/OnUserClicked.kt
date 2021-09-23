package com.example.orangeloginscreen.ui.RecyclerView.adapter

import com.example.orangeloginscreen.model.User

interface OnUserClicked {

    fun oneUserSelected(user: User, position: Int)
}