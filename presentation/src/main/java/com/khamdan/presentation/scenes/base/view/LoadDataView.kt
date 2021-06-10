package com.khamdan.presentation.scenes.base.view

/**
 * Interface representing a View that will use to load data.
 */
interface LoadDataView<in ViewModel> {

    fun render(viewModel: ViewModel)

}
