package ru.nfm.talkingalarm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.nfm.talkingalarm.data.database.dao.AlarmDao
import ru.nfm.talkingalarm.data.database.model.AlarmDbModel
import ru.nfm.talkingalarm.di.ApplicationScope

@ApplicationScope
@Database(entities = [AlarmDbModel::class], version = 2, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

    companion object {

        private var db: AlarmDatabase? = null
        private const val DB_NAME = "alarm.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AlarmDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AlarmDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun alarmDao(): AlarmDao
}