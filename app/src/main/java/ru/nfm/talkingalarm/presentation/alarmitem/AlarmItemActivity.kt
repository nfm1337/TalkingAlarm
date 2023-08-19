package ru.nfm.talkingalarm.presentation.alarmitem

import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.nfm.talkingalarm.AlarmApp
import ru.nfm.talkingalarm.databinding.AlarmItemActivityBinding
import ru.nfm.talkingalarm.domain.model.Alarm
import ru.nfm.talkingalarm.presentation.ViewModelFactory
import java.util.Calendar
import javax.inject.Inject

class AlarmItemActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AlarmItemViewModel
    private var screenMode = MODE_UNKNOWN
    private var alarmId = Alarm.UNDEFINED_ID

    private val component by lazy {
        (application as AlarmApp).component
    }

    private val binding by lazy {
        AlarmItemActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseIntent()
        launchRightMode()
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[AlarmItemViewModel::class.java]
        binding.timepicker.setIs24HourView(true)
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode: $mode")
        }

        screenMode = mode
        if (mode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_ALARM_ID)) {
                throw RuntimeException("Param alarm id is absent")
            }
            alarmId = intent.getLongExtra(EXTRA_ALARM_ID, Alarm.UNDEFINED_ID)
        }
    }

    private fun launchRightMode() {
        val activity = when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
            else -> throw RuntimeException("Unknown mode")
        }
    }

    private fun launchEditMode() {
        viewModel.getAlarmItem(alarmId)
        binding.saveButton.setOnClickListener {
            val calendar = Calendar.getInstance().apply {
                set(Calendar.MILLISECOND, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MINUTE, binding.timepicker.minute)
                set(Calendar.HOUR_OF_DAY, binding.timepicker.hour)
            }

            val timestamp = calendar.timeInMillis
            val repeatDays = emptyList<Int>()
            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString()
            val name = binding.etAlarmName.toString()
            val isActive = true
            val alarm = Alarm(
                timeInMillis = timestamp,
                repeatDays = repeatDays,
                name = name,
                soundUri = soundUri,
                isActive = isActive
            )
            viewModel.editAlarm(alarm)
            finish()
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            val calendar = Calendar.getInstance().apply {
                set(Calendar.MILLISECOND, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MINUTE, binding.timepicker.minute)
                set(Calendar.HOUR_OF_DAY, binding.timepicker.hour)
            }
            val timestamp = calendar.timeInMillis
            val repeatDays = emptyList<Int>()
            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString()
            val name = binding.etAlarmName.toString()
            val isActive = true
            val alarm = Alarm(
                timeInMillis = timestamp,
                repeatDays = repeatDays,
                name = name,
                soundUri = soundUri,
                isActive = isActive
            )
            viewModel.addAlarm(alarm)
            finish()
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ALARM_ID = "extra_alarm_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, AlarmItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Long): Intent {
            val intent = Intent(context, AlarmItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ALARM_ID, id)
            return intent
        }
    }
}