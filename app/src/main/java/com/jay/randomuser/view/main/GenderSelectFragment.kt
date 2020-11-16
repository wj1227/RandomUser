package com.jay.randomuser.view.main

import com.jay.randomuser.R
import com.jay.randomuser.databinding.FragmentGenderSelectBinding
import com.jay.randomuser.view.base.BaseDialogFragment
import com.jay.randomuser.view.base.DialogFragmentLauncher
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

class GenderSelectFragment(

) : BaseDialogFragment<FragmentGenderSelectBinding, MainViewModel>(
    layoutResId = R.layout.fragment_gender_select
) {
    override val viewModel: MainViewModel by sharedViewModel()

    companion object: DialogFragmentLauncher<GenderSelectFragment> {
        override val fragmentClass: KClass<GenderSelectFragment>
            get() = GenderSelectFragment::class

        override fun newInstance(): GenderSelectFragment {
            return GenderSelectFragment()
        }
    }
}