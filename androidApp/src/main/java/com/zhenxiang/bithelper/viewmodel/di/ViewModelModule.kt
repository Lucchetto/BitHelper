package com.zhenxiang.bithelper.viewmodel.di

import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import com.zhenxiang.bithelper.viewmodel.impl.AccountDetailsViewModelImpl
import com.zhenxiang.bithelper.viewmodel.impl.WithdrawPageViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<AccountDetailsViewModel> { AccountDetailsViewModelImpl(get(), get()) }
    viewModel<WithdrawPageViewModel> { WithdrawPageViewModelImpl(get(), get()) }
}
