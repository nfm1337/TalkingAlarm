package ru.nfm.talkingalarm.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.nfm.talkingalarm.domain.Alarm

object AlarmItemDiffCallback : DiffUtil.ItemCallback<Alarm>() {

    override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem == newItem
    }
}