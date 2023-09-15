package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.DB_NAME
import com.example.myapplication.service.PlayerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //המטרה: יצירת מופע של כל האובייקטים שנרצה לספק לאפליקציה
    //base url provides the base url for the api calling
    //this base url is a parameter from build config in prop.propertiesfile.
    private val baseUrl: String = com.example.myapplication.BuildConfig.Player_BASE_URL
    //
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        //תופס את כל הבקשות ומדפיס ללוג
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

//יוצרת מופע של http client לקריאת http
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
          ): OkHttpClient {
        return OkHttpClient.Builder()
             .addInterceptor(loggingInterceptor)
            .build()
    }

    //implementing http call with the basic url+get request in the playerservice interface.
    @Provides
    fun providePlayerService(
        client: OkHttpClient
    ):PlayerService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PlayerService::class.java)
    }

    @Provides
    //providing app db+the context for the whole application
    fun provideAppDatabase( @ApplicationContext
                            context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

        //יצירת מופע לקבלת מידע מהapi
    @Provides
    fun providePlayerDao(db: AppDatabase) = db.playerDao()


}






