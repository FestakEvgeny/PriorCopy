package fetskovich.evgeny.components.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fetskovich.evgeny.components.toolbar.items.BackToolbarItem
import fetskovich.evgeny.components.toolbar.items.IconToolbarItem
import fetskovich.evgeny.components.toolbar.items.TitleToolbarItem
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.presentation.components.R

@Composable
fun BaseToolbar(
    modifier: Modifier = Modifier,
    leftComponent: (@Composable () -> Unit)? = null,
    rightComponent: (@Composable () -> Unit)? = null,
    centerComponent: (@Composable () -> Unit)? = null,
    bottomComponent: (@Composable () -> Unit)? = null,
) {
    TopAppBar(
        backgroundColor = ApplicationTheme.colors.primary,
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val (leftRef, centerRef, rightRef, bottomRef) = createRefs()

            // Left container
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        start = dimensionResource(id = R.dimen.toolbar_container_margin),
                    )
                    .constrainAs(leftRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                leftComponent?.invoke()
            }

            // Center container
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        start = dimensionResource(id = R.dimen.toolbar_center_container_margin),
                        end = dimensionResource(id = R.dimen.toolbar_center_container_margin)
                    )
                    .constrainAs(centerRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(leftRef.end)
                        end.linkTo(rightRef.start)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
            ) {
                centerComponent?.invoke()
            }

            // Right container
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        end = dimensionResource(id = R.dimen.toolbar_container_margin)
                    )
                    .constrainAs(rightRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                rightComponent?.invoke()
            }

            // Bottom container
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                bottomComponent?.invoke()
            }
        }
    }
}

@Preview
@Composable
private fun BaseToolbarPreview() {
    BasicTheme {
        BaseToolbar(
            leftComponent = {
                BackToolbarItem(onClick = {})
            },
            centerComponent = {
                TitleToolbarItem(title = "Test title", onClick = {})
            },
            rightComponent = {
                IconToolbarItem(
                    onClick = { },
                    imageVector = Icons.Default.Search
                )
            }
        )
    }
}
