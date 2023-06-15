package fetskovich.evgeny.components.tabs

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import fetskovich.evgeny.presentation.components.R
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

private const val TAB_INDICATOR_LABEL = "Tab indicator"
private const val BORDER_COLOR_LABEL = "Border color"
private const val RIGHT_INDICATOR_LABEL = "Indicator right"
private const val LEFT_INDICATOR_LABEL = "Indicator left"

@Composable
fun TabsComponent(
  currentTab: Int,
  tabsList: List<TabConfiguration>,
  onTabSelected: (TabConfiguration, Int) -> Unit,
  modifier: Modifier
) {
  TabRow(
    selectedTabIndex = currentTab,
    modifier = modifier,
    backgroundColor = ApplicationTheme.colors.background,
    indicator = { tabPositions ->
      TabIndicator(
        tabPositions = tabPositions,
        tabPage = currentTab
      )
    },
  ) {
    tabsList.forEachIndexed { index, tabConfiguration ->
      Tab(
        configuration = tabConfiguration,
        onClick = { config ->
          onTabSelected(config, index)
        }
      )
    }
  }
}

@Composable
private fun Tab(
  configuration: TabConfiguration,
  onClick: (TabConfiguration) -> Unit,
) {
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .clickable { onClick(configuration) }
      .padding(all = dimensionResource(id = R.dimen.tabs_component_tab_padding))
  ) {
    configuration.icon?.let {
      Icon(
        tint = ApplicationTheme.colors.primary,
        painter = painterResource(id = configuration.icon),
        contentDescription = null,
        modifier = Modifier
          .size(dimensionResource(id = R.dimen.tabs_component_tab_image_size))
      )

      Spacer(
        modifier = Modifier.width(dimensionResource(id = R.dimen.tabs_component_tab_content_space))
      )
    }

    Text(
      text = configuration.title,
      style = MaterialTheme.typography.body1,
      color = ApplicationTheme.colors.primary
    )
  }
}

@Preview
@Composable
private fun TabPreview() {
  BasicTheme {
    Tab(
      configuration = TabConfiguration(
        icon = null,
        title = "Home"
      ),
      onClick = {}
    )
  }
}

@Composable
private fun TabIndicator(
  tabPositions: List<TabPosition>,
  tabPage: Int
) {

  val transition = updateTransition(
    targetState = tabPage,
    label = TAB_INDICATOR_LABEL
  )

  val indicatorLeft = transitionFromLeftToRight(
    transition = transition,
    tabPositions = tabPositions,
    currentPage = tabPage
  )

  val indicatorRight = transitionFromRightToLeft(
    transition = transition,
    tabPositions = tabPositions,
    currentPage = tabPage
  )

  val color = borderColor(transition = transition, currentPage = tabPage)

  Box(
    Modifier
      .fillMaxSize()
      .wrapContentSize(align = Alignment.BottomStart)
      .offset(x = indicatorLeft)
      .width(indicatorRight - indicatorLeft)
      .padding(dimensionResource(id = R.dimen.tabs_component_tab_indicator_padding))
      .fillMaxSize()
      .border(
        BorderStroke(
          width = dimensionResource(id = R.dimen.tabs_component_tab_indicator_border_size),
          color = color
        ),
        MaterialTheme.shapes.small
      )
  )
}

@Composable
private fun transitionFromLeftToRight(
  transition: Transition<Int>,
  tabPositions: List<TabPosition>,
  currentPage: Int
): Dp {
  val indicatorLeft by transition.animateDp(
    transitionSpec = {
      if (currentPage isTransitioningTo (currentPage + 1)) {
        spring(stiffness = Spring.StiffnessVeryLow)
      } else {
        spring(stiffness = Spring.StiffnessMedium)
      }
    },
    label = LEFT_INDICATOR_LABEL
  ) { page ->
    tabPositions[page].left
  }

  return indicatorLeft
}

@Composable
private fun transitionFromRightToLeft(
  transition: Transition<Int>,
  tabPositions: List<TabPosition>,
  currentPage: Int
): Dp {
  val indicatorRight by transition.animateDp(
    transitionSpec = {
      if (currentPage isTransitioningTo currentPage - 1) {
        spring(stiffness = Spring.StiffnessMedium)
      } else {
        spring(stiffness = Spring.StiffnessVeryLow)
      }
    },
    label = RIGHT_INDICATOR_LABEL
  ) { page ->
    tabPositions[page].right
  }

  return indicatorRight
}

@Composable
private fun borderColor(
  transition: Transition<Int>,
  currentPage: Int
): Color {
  val color by transition.animateColor(
    label = BORDER_COLOR_LABEL
  ) { page ->
    if (page == currentPage) {
      ApplicationTheme.colors.primary
    } else {
      ApplicationTheme.colors.secondary
    }
  }
  return color
}

@Preview
@Composable
private fun TabsComponentPreview() {
  BasicTheme {
    var tabPage by remember { mutableStateOf(0) }
    TabsComponent(
      currentTab = tabPage,
      tabsList = listOf(
        TabConfiguration(
          icon = null,
          "Home"
        ),
        TabConfiguration(
          icon = null,
          title = "Add"
        )
      ),
      onTabSelected = { config, index ->
        tabPage = index
      },
      modifier = Modifier
        .fillMaxWidth()
    )
  }
}