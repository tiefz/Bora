package br.com.tiefenbarher.bora.di

import androidx.room.Room
import br.com.tiefenbarher.bora.data.db.BoraDatabase
import br.com.tiefenbarher.bora.data.repository.BoraRepositoryImpl
import br.com.tiefenbarher.bora.domain.model.repository.BoraRepository
import br.com.tiefenbarher.bora.presentation.view_model.BoraViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val boraModule = module {
    single {
        Room.databaseBuilder(
            get(),
            BoraDatabase::class.java,
            "BoraDatabase"
        ).fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<BoraDatabase>().boraDao()
    }
    single<BoraRepository> {
        BoraRepositoryImpl(get())
    }
    viewModel {
        BoraViewModel(get())
    }
}