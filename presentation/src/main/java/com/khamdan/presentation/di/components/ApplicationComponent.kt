package com.khamdan.presentation.di.components

import android.app.Application
import com.khamdan.data.di.components.DataComponent
import com.khamdan.presentation.di.PerApplication
import com.khamdan.presentation.di.modules.ApplicationModule
import com.khamdan.presentation.di.modules.UseCaseModule
import dagger.BindsInstance
import dagger.Component

@PerApplication // Constraints this component to one-per-application or unscoped bindings.
@Component(
    dependencies = [(DataComponent::class)],
    modules = [(ApplicationModule::class), (UseCaseModule::class)]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            dataComponent: DataComponent
        ): ApplicationComponent
    }

    fun activityComponent(): ActivityComponent.Factory

}
