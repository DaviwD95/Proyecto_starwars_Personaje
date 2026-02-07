package com.example.starwarspersonaje.ui.dl

import android.content.Context
import android.content.res.Resources
import com.example.specie.data.dao.PersonajeDao
import com.example.specie.data.dao.StartWarsDatabase
import com.example.starwarspersonaje.ui.data.dao.FilmDAO
import com.example.starwarspersonaje.ui.data.dao.PlanetDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context: Context): Resources{
        return context.resources

    }

    @Provides
    @Singleton
    fun provideStarWarsDataBase(@ApplicationContext context: Context): StartWarsDatabase{
        return StartWarsDatabase.getDatabase(context)

    }

    @Provides
    @Singleton
    fun providePersonajeDao(startWarsDatabase: StartWarsDatabase): PersonajeDao{
        return startWarsDatabase.getPersonajeDao()

    }

    /**
     * @Provides
     *     @Singleton
     *     fun provideFilmDao(startWarsDatabase: StartWarsDatabase): FilmDAO {
     *         return startWarsDatabase.getFilmDao()
     *     }
     */

    /**
     * @Provides
     *     @Singleton
     *     fun providePlanetDao(startWarsDatabase: StartWarsDatabase): PlanetDao {
     *         return startWarsDatabase.getPlanetDao()
     *     }
     */


}
