package com.khamdan.data.di.components

import android.content.Context
import com.khamdan.data.di.modules.NetModule
import com.khamdan.data.di.modules.RepositoryModule
import com.khamdan.domain.repository.PostRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (RepositoryModule::class)])
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }

    // Exposed to sub-graphs
    fun providePostRepository(): PostRepository

}