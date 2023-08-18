package ru.nfm.talkingalarm.domain

data class Alarm(
    val id: Long = 0,
    val timeInMillis: Long,
    val repeatDays: List<Int>,
    val soundUri: String,
    val isActive: Boolean
)