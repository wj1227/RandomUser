package com.jay.randomuser.utils.diff

interface Identifiable {
    val identifier: Any

    override operator fun equals(other: Any?): Boolean
}