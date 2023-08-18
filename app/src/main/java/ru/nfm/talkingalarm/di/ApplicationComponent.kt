package ru.nfm.talkingalarm.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.nfm.talkingalarm.AlarmApp
import ru.nfm.talkingalarm.presentation.AlarmItemActivity
import ru.nfm.talkingalarm.presentation.AlarmListActivity

@ApplicationScope
@Component(
    modules =
    [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: AlarmListActivity)

    fun inject(activity: AlarmItemActivity)

    fun inject(application: AlarmApp)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}