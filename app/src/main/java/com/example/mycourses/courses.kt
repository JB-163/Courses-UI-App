package com.example.mycourses

import androidx.annotation.DrawableRes

data class Courses(
    val rating : Double,
    val title : String,
    @DrawableRes val image : Int,
    val desc : String
)

val course1 = Courses(
    4.6,
    "Complete Kotlin development masterclass",
    R.drawable.course8,
    "Master the fundamentals and advanced features of Kotlin development"
)
val course2 = Courses(
    4.7,
    "The Complete Java Development Bootcamp",
    R.drawable.course4,
    "Become an Experienced Java Developer with Just One Course. Fully Updated with 100+ Coding Challenges!"
)
val course3 = Courses(
    4.8,
    "Android Firebase Masterclass - Master Google Firebase",
    R.drawable.course5,
    "Create Cloud based Android applications using Google Firebase and expand your career options"
)
val course4 = Courses(
    4.4,
    "Android Studio Masterclass: Conquer the Android IDE",
    R.drawable.course6,
    "Android Studio Masterclass: Learn Android Studio from Top to Bottom and Become a Better Android Developer!"
)
val course5 = Courses(
    4.5,
    "Time Management Mastery: Do More, Stress Less",
    R.drawable.course7,
    "Take control of your time, your to-do list, and your overwhelm. Learn how to get it all done, without the stress."
)
val course6 = Courses(
    4.5,
    "Jetpack Compose Crash course for Android with Kotlin",
    R.drawable.course2,
    "Modern Android apps with Jetpack Compose and integrations: MVVM, Coroutines, ViewModel, LiveData, Retrofit, Navigation"
)
val course7 = Courses(
    4.6,
    "The Complete Python Development",
    R.drawable.course1,
    "How to become a Python 3 Developer and get hired! Build 12+ projects, learn Web Development, Machine Learning + more!"
)
val course8 = Courses(
    4.6,
    "Digital Marketing Agency | Start a Social Media Business",
    R.drawable.course3,
    "Build a Digital Marketing Agency Business with Social Media (SMMA), ChatGPT, Facebook Ads, Instagram, WordPress, SEO"
)

val allCourses = listOf(
    course1,
    course2,
    course3,
    course4,
    course5,
    course6,
    course7,
    course8
)
