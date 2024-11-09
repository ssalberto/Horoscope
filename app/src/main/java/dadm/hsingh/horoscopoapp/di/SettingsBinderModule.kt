package dadm.hsingh.horoscopoapp.di

import dadm.hsingh.horoscopoapp.data.settings.SettingsDataSource
import dadm.hsingh.horoscopoapp.data.settings.SettingsDataSourceImpl
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {
    @Binds
    abstract fun bindSettingsDataSource(impl: SettingsDataSourceImpl) : SettingsDataSource

    @Binds
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl) : SettingsRepository
}