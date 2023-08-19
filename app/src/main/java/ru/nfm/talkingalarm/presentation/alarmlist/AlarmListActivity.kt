package ru.nfm.talkingalarm.presentation.alarmlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.nfm.talkingalarm.AlarmApp
import ru.nfm.talkingalarm.databinding.AlarmListActivityBinding
import ru.nfm.talkingalarm.di.ApplicationComponent
import ru.nfm.talkingalarm.presentation.ViewModelFactory
import ru.nfm.talkingalarm.presentation.adapters.AlarmListAdapter
import ru.nfm.talkingalarm.presentation.alarmitem.AlarmItemActivity
import javax.inject.Inject

class AlarmListActivity : AppCompatActivity() {

    @Inject lateinit var alarmListAdapter: AlarmListAdapter
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AlarmListViewModel


    private val component: ApplicationComponent by lazy {
        (application as AlarmApp).component
    }

    private val binding by lazy {
        AlarmListActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[AlarmListViewModel::class.java]

        viewModel.alarmList.observe(this) {
            alarmListAdapter.submitList(it)
        }
        binding.addButton.setOnClickListener {
            launchAlarmItemActivityAddMode()
        }

    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            adapter = alarmListAdapter
            recycledViewPool.setMaxRecycledViews(
                AlarmListAdapter.VIEW_TYPE_ENABLED,
                AlarmListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                AlarmListAdapter.VIEW_TYPE_DISABLED,
                AlarmListAdapter.MAX_POOL_SIZE
            )
            setupClickListener()
            setupSwipeListener(this)
        }
    }

    private fun setupClickListener() {
        alarmListAdapter.onAlarmItemClickListener = {
            val intent = AlarmItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupSwipeListener(rvAlarmList: RecyclerView) {
        val callback = object :ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item =  alarmListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteAlarmItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvAlarmList)
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