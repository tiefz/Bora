package br.com.tiefenbarher.bora.di

import androidx.room.Room
import br.com.tiefenbarher.bora.data.db.BoraDatabase
import org.koin.dsl.module

val boraModule = module {
    single {
        Room.databaseBuilder(
            get(),
            BoraDatabase::class.java,
            "BoraDatabase"
        ).build()
    }
    single {
        get<BoraDatabase>().boraDao()
    }
}