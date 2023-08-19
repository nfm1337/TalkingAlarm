package ru.nfm.talkingalarm.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nfm.talkingalarm.databinding.AlarmItemBinding
import ru.nfm.talkingalarm.domain.model.Alarm
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class AlarmListAdapter @Inject constructor() : ListAdapter<Alarm, AlarmItemViewHolder>(AlarmItemDiffCallback) {

    var onAlarmItemClickListener: ((Alarm) -> Unit)? = null

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
        Log.d("AlarmListAdapter", alarm.toString())
        val binding = holder.binding
        with(binding) {
            switchAlarm.isChecked = alarm.isActive
            tvAlarmSetTime.text = getFormattedTime(alarm.timeInMillis)
            if (alarm.repeatDays.isEmpty()) {
                tvAlarmType.text = "111"
            } else {
                tvAlarmType.text = alarm.repeatDays.toString()
            }
        }
        binding.root.setOnClickListener {
            onAlarmItemClickListener?.invoke(alarm)
        }

        binding.switchAlarm.setOnCheckedChangeListener { _, isChecked ->
            alarm.isActive = !alarm.isActive
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isActive) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    private fun getFormattedTime(timeInMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

//    private fun getFormattedRepeatDays(context: Context, repeatDays: List<Int>): String {
//        val daysOfWeek = context.resources.getStringArray(R.array.days_of_week_short)
//        val selectedDays = repeatDays.map { daysOfWeek[it] }
//        return selectedDays.joinToString(" ")
//    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
        private const val LOG_TAG = "AlarmListAdapter"
    }
}