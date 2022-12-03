package com.zhenxiang.bithelper.pages.withdraw

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dsc.form_builder.FormState
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.zhenxiang.bithelper.component.NavBackButton
import com.zhenxiang.bithelper.form.StringTransformations
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.foundation.*
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.res.SharedRes
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import com.zhenxiang.bithelper.viewmodel.preview.WithdrawPageViewModelPreview
import dev.icerock.moko.resources.format

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun WithdrawPage(rootNavController: NavHostController, viewModel: WithdrawPageViewModel) {

    val withdrawMethodsState = viewModel.withdrawMethodsFlow.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopBar(rootNavController, viewModel.assetTicker) }
    ) {
        Content(
            padding = it,
            formState = viewModel.formState,
            assetTicker = viewModel.assetTicker,
            state = withdrawMethodsState,
            onWithdraw = { viewModel.withdraw() },
            onCancel = { rootNavController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(navController: NavHostController, assetTicker: String) {
    MediumTopAppBar(
        title = { Text(SharedRes.strings.withdraw_page_title.format(assetTicker).composeResource()) },
        navigationIcon = {
            NavBackButton {
                navController.popBackStack()
            }
        }
    )
}

@Composable
private fun Content(
    padding: PaddingValues,
    assetTicker: String,
    formState: FormState<*>,
    state: State<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>,
    onWithdraw: () -> Unit,
    onCancel: () -> Unit,
) {

    val value by state
    val selectedWithdrawMethod = formState.getState<TypedChoiceState<WithdrawMethod>>(WithdrawPageViewModel.WITHDRAW_METHOD_FORM_FIELD).value

    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = MaterialTheme.spacing.level5)
            .fillMaxWidth()
    ) {
        item {
            FormStateOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                state = formState.getState(WithdrawPageViewModel.RECIPIENT_ADDRESS_FORM_FIELD),
                label = SharedRes.strings.recipient_address_title.composeResource(),
                transform = StringTransformations.REMOVE_WHITESPACES_AND_NEWLINES,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    autoCorrect = false,
                ),
            )
        }
        item {
            FormStateOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                state = formState.getState(WithdrawPageViewModel.RECIPIENT_ADDRESS_MEMO_FORM_FIELD),
                label = SharedRes.strings.memo_title.composeResource(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    autoCorrect = false,
                ),
            )
        }
        item {
            FormStateOutlinedBottomSheetMenu(
                state = formState.getState(WithdrawPageViewModel.WITHDRAW_METHOD_FORM_FIELD),
                enabled = value is ResultWrapper.Success,
                label = SharedRes.strings.withdraw_method_title.composeResource(),
                options = value.let { if (it is ResultWrapper.Success) it.data else listOf() },
                toStringAdapter = { it.name },
                trailingIconOverride = if (value is ResultWrapper.Loading) {
                    {
                        CircularProgressIndicator(
                            modifier = Modifier.size(MaterialTheme.sizing.materialIcon),
                            strokeWidth = CircularProgressIndicatorDefaults.Small.strokeWidth
                        )
                    }
                } else {
                    null
                }
            ) { selected, clicked, value ->
                WithdrawMethodCard(selected, clicked, assetTicker, value)
            }
        }
        selectedWithdrawMethod?.fee?.let {
            item {
                Text(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.level5),
                    text = SharedRes.strings.withdrawal_fee_value.format(it.toStringExpanded(), assetTicker).composeResource(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        item {
            ButtonBar(
                modifier = Modifier.padding(top = MaterialTheme.spacing.level5)
            ) {
                Button(onClick = onWithdraw) {
                    Text(SharedRes.strings.withdraw.composeResource())
                }
                TextButton(onClick = onCancel) {
                    Text(SharedRes.strings.cancel.composeResource())
                }
            }
        }
    }
}

@Composable
private fun WithdrawMethodCard(selected: Boolean, clicked: () -> Unit, assetTicker: String, method: WithdrawMethod) {

    Card(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.level3),
        colors = if (method.available && selected) {
            CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        } else {
            CardDefaults.outlinedCardColors()
        },
        border = CardDefaults.outlinedCardBorder(enabled = method.available)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = method.available, onClick = clicked)
                .padding(MaterialTheme.spacing.level5)
        ) {
            Text(
                method.name,
                style = MaterialTheme.typography.titleMedium
            )
            method.fee?.let {
                Text(
                    if (it.isZero()) {
                        SharedRes.strings.free.composeResource()
                    } else {
                        SharedRes.strings.fee_value_and_unit.format(it.toStringExpanded(), assetTicker).composeResource()
                    }
                )
            }
            method.description?.let {
                Text(
                    it,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun WithdrawPagePreview() = WithdrawPage(
    rootNavController = rememberNavController(),
    viewModel = WithdrawPageViewModelPreview(
        assetTicker = "ETH",
        mockWithdrawMethods = listOf(
            WithdrawMethod(
                name = "Ethereum (ERC20)",
                exchangeInternalId = "ERC20",
                description = "Description of withdraw method",
                hints = "Hints for withdraw method",
                fee = BigDecimal.parseString("0.005"),
                available = true,
                hasMemoField = false
            ),
            WithdrawMethod(
                name = "Polygon",
                exchangeInternalId = "MATIC",
                description = "Description of withdraw method",
                hints = null,
                fee = BigDecimal.parseString("0.00005"),
                available = true,
                hasMemoField = false
            ),
            WithdrawMethod(
                name = "Metis",
                exchangeInternalId = "METIS",
                description = "Maintenance mode",
                hints = null,
                fee = BigDecimal.parseString("0"),
                available = false,
                hasMemoField = false
            )
        )
    )
)

@Preview
@Composable
private fun WithdrawMethodCardPreview() = WithdrawMethodCard(
    selected = false,
    clicked = { },
    assetTicker = "ETH",
    method = WithdrawMethod(
        name = "Ethereum (ERC20)",
        exchangeInternalId = "ERC20",
        description = "Description of withdraw method",
        hints = "Hints for withdraw method",
        fee = BigDecimal.parseString("0.005"),
        available = true,
        hasMemoField = false
    )
)
