package com.jay.randomuser.view.main.model

import com.jay.randomuser.utils.diff.Identifiable

data class UserUiModel(
    val id: String,
    val profileImagePath: String?,
    val name: String,
    val gender: String,
    val countryFlag: String,
    val onclick: (() -> Unit)
) : Identifiable {

    override val identifier: Any
        get() = id
}