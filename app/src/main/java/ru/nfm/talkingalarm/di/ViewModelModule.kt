package ru.nfm.talkingalarm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nfm.talkingalarm.presentation.AlarmItemViewModel
import ru.nfm.talkingalarm.presentation.AlarmListViewModel
import ru.nfm.talkingalarm.presentation.ViewModelFactory

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlarmListViewModel::class)
    fun bindAlarmListViewModel(viewModel: AlarmListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlarmItemViewModel::class)
    fun bindAlarmItemViewModel(factory: AlarmItemViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}