package dadm.hsingh.horoscopoapp.di

import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeDataSource
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeDataSourceImpl
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeRepositoryImpl
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.MonthlyHoroscopeDataSource
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.MonthlyHoroscopeDataSourceImpl
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.MonthlyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.MonthlyHoroscopeRepositoryImpl
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeDataSource
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeDataSourceImpl
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeRepositoryImpl
import dadm.hsingh.horoscopoapp.data.ranking.RankingDataSource
import dadm.hsingh.horoscopoapp.data.ranking.RankingDataSourceImpl
import dadm.hsingh.horoscopoapp.data.ranking.RankingRepository
import dadm.hsingh.horoscopoapp.data.ranking.RankingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HoroscopeBinderModule {

    @Binds
    abstract fun bindDailyHoroscopeRepository(impl: DailyHoroscopeRepositoryImpl): DailyHoroscopeRepository

    @Binds
    abstract fun bindDailyHoroscopeDataSource(impl: DailyHoroscopeDataSourceImpl): DailyHoroscopeDataSource
    @Binds
    abstract fun bindWeeklyHoroscopeRepository(impl: WeeklyHoroscopeRepositoryImpl): WeeklyHoroscopeRepository

    @Binds
    abstract fun bindWeeklyHoroscopeDataSource(impl: WeeklyHoroscopeDataSourceImpl): WeeklyHoroscopeDataSource

    @Binds
    abstract fun bindMonthlyHoroscopeRepository(impl: MonthlyHoroscopeRepositoryImpl): MonthlyHoroscopeRepository

    @Binds
    abstract fun bindMonthlyHoroscopeDataSource(impl: MonthlyHoroscopeDataSourceImpl): MonthlyHoroscopeDataSource

    @Binds
    abstract fun bindRankingDataSource(impl: RankingDataSourceImpl): RankingDataSource

    @Binds
    abstract fun bindRankingRepository(impl: RankingRepositoryImpl): RankingRepository
}