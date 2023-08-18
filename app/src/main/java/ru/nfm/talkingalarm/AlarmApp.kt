package ru.nfm.talkingalarm

import android.app.Application
import ru.nfm.talkingalarm.di.DaggerApplicationComponent

class AlarmApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}