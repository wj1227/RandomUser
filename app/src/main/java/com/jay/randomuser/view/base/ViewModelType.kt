package com.jay.randomuser.view.base

interface ViewModelType<Input, Output> {
    val input: Input
    val output: Output

    interface Input

    interface Output
}