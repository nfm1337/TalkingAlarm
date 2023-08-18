package ru.nfm.talkingalarm.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nfm.talkingalarm.data.database.dao.AlarmDao
import ru.nfm.talkingalarm.data.database.AlarmDatabase
import ru.nfm.talkingalarm.data.database.repository.AlarmRepositoryImpl
import ru.nfm.talkingalarm.domain.AlarmRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindAlarmRepository(impl: AlarmRepositoryImpl): AlarmRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideAlarmDao(
            application: Application
        ): AlarmDao {
            return AlarmDatabase.getInstance(application).alarmDao()
        }
    }
}