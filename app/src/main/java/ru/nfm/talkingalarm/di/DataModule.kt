package ru.nfm.talkingalarm.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nfm.talkingalarm.data.database.dao.AlarmDao
import ru.nfm.talkingalarm.data.database.AlarmDatabase
import ru.nfm.talkingalarm.data.database.dao.SentenceDao
import ru.nfm.talkingalarm.data.database.repository.AlarmRepositoryImpl
import ru.nfm.talkingalarm.data.database.repository.SentenceRepositoryImpl
import ru.nfm.talkingalarm.domain.repository.AlarmRepository
import ru.nfm.talkingalarm.domain.repository.SentenceRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindAlarmRepository(impl: AlarmRepositoryImpl): AlarmRepository

    @Binds
    @ApplicationScope
    fun bindSentenceRepository(impl: SentenceRepositoryImpl): SentenceRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideAlarmDao(
            application: Application
        ): AlarmDao {
            return AlarmDatabase.getInstance(application).alarmDao()
        }

        @Provides
        @ApplicationScope
        fun provideSentenceDao(
            application: Application
        ): SentenceDao {
            return AlarmDatabase.getInstance(application).sentenceDao()
        }
    }
}