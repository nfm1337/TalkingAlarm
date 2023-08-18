package ru.nfm.talkingalarm.data.mapper

import ru.nfm.talkingalarm.data.database.model.AlarmDbModel
import ru.nfm.talkingalarm.domain.Alarm
import javax.inject.Inject

class AlarmMapper @Inject constructor() {

    fun mapDbModelToDomain(dbModel: AlarmDbModel): Alarm {
        return Alarm(
            id = dbModel.id,
            timeInMillis = dbModel.time,
            repeatDays = decodeBitFieldToRepeatDays(dbModel.repeatDays),
            soundUri = dbModel.soundUri,
            isActive = dbModel.isActive
        )
    }

    // Helper function to decode bit field to list of repeat days
    private fun decodeBitFieldToRepeatDays(bitField: Int): List<Int> {
        val repeatDays = mutableListOf<Int>()
        for (dayOfWeek in 1..7) {
            if ((bitField and (1 shl dayOfWeek)) != 0) {
                repeatDays.add(dayOfWeek)
            }
        }
        return repeatDays
    }

    fun mapDomainToDbModel(entity: Alarm): AlarmDbModel {
        return AlarmDbModel(
            id = entity.id,
            time = entity.timeInMillis,
            soundUri = entity.soundUri,
            repeatDays = encodeRepeatDaysToBitField(entity.repeatDays),
            isActive = entity.isActive
        )
    }

    // Helper function to encode list of repeat days to single integer
    private fun encodeRepeatDaysToBitField(repeatDays: List<Int>): Int {
        var bitField = 0
        repeatDays.forEach { day ->
            bitField = bitField or (1 shl day)
        }
        return bitField
    }

    fun mapListDbModelToDomainList(list: List<AlarmDbModel>): List<Alarm> = list.map {
        mapDbModelToDomain(it)
    }




}