package com.khamdan.data.di.modules

import com.khamdan.data.extensions.api
import com.khamdan.data.mapper.RepoMapper
import com.khamdan.data.repository.PostDataRepository
import com.khamdan.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun providePostDataRepository(
        retrofit: Retrofit,
        repoMapper: RepoMapper
    ): PostRepository = PostDataRepository(retrofit.api(), repoMapper)
}
