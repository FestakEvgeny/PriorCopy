package fetskovich.evgeny.components.modifiers

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.TabPosition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

fun Modifier.tabFractionOffsetModifier(
    currentTabPosition: TabPosition,
    previousTabPosition: TabPosition,
    tabSwitchFraction: Float,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    var offsetNew: Dp = previousTabPosition.left
    if (tabSwitchFraction > 0) {
        offsetNew = (previousTabPosition.left.value + (previousTabPosition.width.value * tabSwitchFraction)).dp
        // Goes to the right
    } else if (tabSwitchFraction < 0) {
        offsetNew = (previousTabPosition.left.value - (previousTabPosition.width.value * abs(tabSwitchFraction))).dp
    } else {
        // do nothing
    }

    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = spring(stiffness = Spring.StiffnessLow), label = "width"
    )
    val indicatorOffset by animateDpAsState(
        targetValue = offsetNew,
        animationSpec = spring(stiffness = Spring.StiffnessLow), label = "offset"
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}
