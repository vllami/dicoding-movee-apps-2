package com.dicoding.submissiondua.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    val espressoTestIdlingResource = CountingIdlingResource("GLOBAL")

    fun increment() = espressoTestIdlingResource.increment()

    fun decrement() = espressoTestIdlingResource.decrement()

}