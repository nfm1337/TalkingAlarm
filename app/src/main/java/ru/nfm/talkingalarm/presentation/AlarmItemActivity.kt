package ru.nfm.talkingalarm.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.nfm.talkingalarm.AlarmApp
import ru.nfm.talkingalarm.databinding.AlarmItemActivityBinding
import javax.inject.Inject

class AlarmItemActivity : AppCompatActivity() {

    private lateinit var alarmItemViewModel: AlarmItemViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        alarmItemViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[AlarmItemViewModel::class.java]

    }



    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ALARM_ID = "extra_alarm_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"

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