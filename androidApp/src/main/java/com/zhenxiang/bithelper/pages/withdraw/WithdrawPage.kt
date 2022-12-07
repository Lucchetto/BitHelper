package com.zhenxiang.bithelper.pages.withdraw

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dsc.form_builder.FormState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.zhenxiang.bithelper.component.NavBackButton
import com.zhenxiang.bithelper.component.SuffixTransformation
import com.zhenxiang.bithelper.component.placeholder
import com.zhenxiang.bithelper.component.shimmer
import com.zhenxiang.bithelper.form.StringTransformations
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.foundation.*
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.res.SharedRes
import com.zhenxiang.bithelper.utils.successDataOrNull
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import com.zhenxiang.bithelper.viewmodel.preview.WithdrawPageViewModelPreview
import dev.icerock.moko.resources.format
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawPage(rootNavController: NavHostController, viewModel: WithdrawPageViewModel) {

    Scaffold(
        topBar = { TopBar(rootNavController, viewModel.assetTicker) }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = MaterialTheme.spacing.level5)
                .fillMaxWidth()
        ) {
            AssetBalance(viewModel.assetBalanceFlow)
            Form(viewModel.assetTicker, viewModel.formState, viewModel.withdrawMethodsFlow)
            ActionsBar(
                onWithdraw = { viewModel.withdraw() },
                onCancel = { rootNavController.popBackStack() }
            )
        }
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

@OptIn(ExperimentalLifecycleComposeApi::class)
private fun LazyListScope.AssetBalance(
    flow: StateFlow<ResultWrapper<AssetBalance, ExchangeApiError>>
) {
    item {
        val balance by flow.collectAsStateWithLifecycle()

        balance.successDataOrNull().let {
            Text(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.level3)
                    .placeholder(
                        visible = it == null,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                text = if (it != null) {
                     SharedRes.strings.available_balance_value.format(
                        it.availableBalance,
                        it.ticker
                    )
                } else {
                    SharedRes.strings.available_balance_value.format(
                        AssetBalance.PLACEHOLDER_BALANCE,
                        AssetBalance.PLACEHOLDER_TICKER
                    )
                }.composeResource(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
private fun LazyListScope.Form(
    assetTicker: String,
    formState: FormState<*>,
    flow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>,
) {
    item {
        val selectedWithdrawMethod = formState.getState<TypedChoiceState<WithdrawMethod>>(WithdrawPageViewModel.WITHDRAW_METHOD_FORM_FIELD).value

        DecimalFormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.getState(WithdrawPageViewModel.AMOUNT_FORM_FIELD),
            precision = selectedWithdrawMethod?.decimalPrecision ?: 0,
            enabled = selectedWithdrawMethod != null,
            label = SharedRes.strings.amount_title.composeResource(),
            visualTransformation = SuffixTransformation(
                assetTicker,
                SpanStyle(fontWeight = FontWeight.Bold)
            ),
            imeAction = ImeAction.Next,
        )
    }
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
        val withdrawMethods by flow.collectAsStateWithLifecycle()

        FormStateOutlinedBottomSheetMenu(
            state = formState.getState(WithdrawPageViewModel.WITHDRAW_METHOD_FORM_FIELD),
            enabled = withdrawMethods is ResultWrapper.Success,
            label = SharedRes.strings.withdraw_method_title.composeResource(),
            options = withdrawMethods.let { if (it is ResultWrapper.Success) it.data else listOf() },
            toStringAdapter = { it.name },
            trailingIconOverride = if (withdrawMethods is ResultWrapper.Loading) {
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

    val selectedWithdrawMethod = formState.getState<TypedChoiceState<WithdrawMethod>>(WithdrawPageViewModel.WITHDRAW_METHOD_FORM_FIELD).value

    selectedWithdrawMethod?.fee?.let {
        item {
            Text(
                modifier = Modifier.padding(top = MaterialTheme.spacing.level5),
                text = SharedRes.strings.withdrawal_fee_value.format(it.toStringExpanded(), assetTicker).composeResource(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

private fun LazyListScope.ActionsBar(
    onWithdraw: () -> Unit,
    onCancel: () -> Unit,
) {
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
            method.hints?.let {
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
        mockAssetBalance = AssetBalance("ETH", 0.5),
        mockWithdrawMethods = listOf(
            WithdrawMethod(
                name = "Ethereum (ERC20)",
                exchangeInternalId = "ERC20",
                description = "Description of withdraw method",
                hints = "Hints for the withdraw method",
                fee = "0.005".toBigDecimal(),
                available = true,
                decimalPrecision = 8,
                minAmount = "0.001".toBigDecimal(),
                hasMemoField = false
            ),
            WithdrawMethod(
                name = "Polygon",
                exchangeInternalId = "MATIC",
                description = "Description of withdraw method",
                hints = "Hints for the withdraw method",
                fee = "0.00005".toBigDecimal(),
                available = true,
                decimalPrecision = 8,
                minAmount = "0.001".toBigDecimal(),
                hasMemoField = false
            ),
            WithdrawMethod(
                name = "Metis",
                exchangeInternalId = "METIS",
                description = "Maintenance mode",
                hints = null,
                fee = "0".toBigDecimal(),
                available = false,
                decimalPrecision = 8,
                minAmount = "0.001".toBigDecimal(),
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
        hints = "Hints for the withdraw method",
        fee = "0.005".toBigDecimal(),
        available = true,
        decimalPrecision = 8,
        minAmount = "0.001".toBigDecimal(),
        hasMemoField = false
    )
)
