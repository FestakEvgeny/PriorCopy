package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.addproduct

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun AddMyProductBottomSheetComponent() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(16.dp)
    ) {

        Text(text = "Оформить карту TODO")

        Text(text = "Добавить карту другого банка")

        Text(text = "Открыть онлайн-депозит TODO")

        Text(text = "Подать заявку на кредит TODO")
    }
}

@Preview
@Composable
private fun AddMyProductBottomSheetComponentPreview() {
    BasicTheme {
        AddMyProductBottomSheetComponent()
    }
}