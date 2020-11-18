package com.jay.randomuser.view.detail.mapper

import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.view.detail.model.UserDetailModel
import com.jay.randomuser.view.mapper.Mapper

object UserDetailMapper : Mapper<UserResponse, UserDetailModel> {
    override fun mapper(from: UserResponse): UserDetailModel {
        return UserDetailModel(
            profileImagePath = from.picture.thumbnail,
            name = "이름: ${from.name.title}. ${from.name.first} ${from.name.last}",
            email = "이메일(클릭시 이동) ${from.email}",
            cellPhone = "휴대전화(클릭시 이동): ${from.cell}",
            phone = "전화(클릭시 이동): ${from.phone}",
            address = "주소: ${from.nat} ${from.location.state} ${from.location.city} ${from.location.street.name} ${from.location.street.number}"
        )
    }
}