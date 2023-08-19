package ru.nfm.talkingalarm.domain.model

data class Alarm(
    val id: Long = UNDEFINED_ID,
    val timeInMillis: Long,
    val repeatDays: List<Int> = emptyList(),
    val soundUri: String,
    val name: String = EMPTY_STRING,
    var isActive: Boolean
) {
    val hasRepeatDays: Boolean
        get() = repeatDays.isNotEmpty()

    companion object {
        const val UNDEFINED_ID = 0L
        private const val EMPTY_STRING = ""
    }
}