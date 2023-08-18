package ru.nfm.talkingalarm.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nfm.talkingalarm.R
import ru.nfm.talkingalarm.databinding.AlarmItemBinding
import ru.nfm.talkingalarm.domain.Alarm
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmItemAdapter(
    private val context: Context
) : ListAdapter<Alarm, AlarmItemViewHolder>(AlarmItemDiffCallback){

    var onClickListener: OnAlarmClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmItemViewHolder {
        val binding = AlarmItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlarmItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        val alarm = getItem(position)
        with(holder.binding) {
            tvAlarmSetTime.text = getFormattedTime(alarm.timeInMillis)
            if (alarm.repeatDays.isEmpty()) {
                tvAlarmType.text = context.resources.getString(R.string.single_signal)
            } else {
                tvAlarmType.text = getFormattedRepeatDays(context, alarm.repeatDays)
            }
        }

        holder.itemView.setOnClickListener {
            onClickListener?.onItemClick(alarm)
        }
    }

    private fun getFormattedTime(timeInMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun getFormattedRepeatDays(context: Context, repeatDays: List<Int>): String {
        val daysOfWeek = context.resources.getStringArray(R.array.days_of_week_short)
        val selectedDays = repeatDays.map { daysOfWeek[it] }
        return selectedDays.joinToString(" ")
    }

    interface OnAlarmClickListener {

        fun onItemClick(alarmItem: Alarm)
    }
}