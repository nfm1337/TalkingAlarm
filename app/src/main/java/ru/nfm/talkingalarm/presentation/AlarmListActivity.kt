package ru.nfm.talkingalarm.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.nfm.talkingalarm.AlarmApp
import ru.nfm.talkingalarm.databinding.AlarmListActivityBinding
import ru.nfm.talkingalarm.domain.Alarm
import ru.nfm.talkingalarm.presentation.adapters.AlarmItemAdapter
import javax.inject.Inject

class AlarmListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var alarmListViewModel: AlarmListViewModel

    private val component by lazy {
        (application as AlarmApp).component
    }

    private val binding by lazy {
        AlarmListActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        alarmListViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[AlarmListViewModel::class.java]
        val adapter = AlarmItemAdapter(this)

        adapter.onClickListener = object : AlarmItemAdapter.OnAlarmClickListener {
            override fun onItemClick(alarmItem: Alarm) {
                launchAlarmItemActivityEditMode(alarmItem.id)
            }
        }

        binding.addButton.setOnClickListener {
            launchAlarmItemActivityAddMode()
        }

        binding.recyclerView.adapter = adapter
    }

    private fun launchAlarmItemActivityAddMode() {
        val intent = AlarmItemActivity.newIntentAddItem(this@AlarmListActivity)
        startActivity(intent)
    }

    private fun launchAlarmItemActivityEditMode(id: Long) {
        val intent = AlarmItemActivity.newIntentEditItem(this@AlarmListActivity, id)
        startActivity(intent)
    }
}