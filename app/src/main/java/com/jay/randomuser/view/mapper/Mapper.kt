package com.jay.randomuser.view.mapper

interface Mapper<T, R> {
    fun mapper(from: T): R
}