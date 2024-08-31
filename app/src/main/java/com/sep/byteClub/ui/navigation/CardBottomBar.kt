package com.sep.byteClub.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.sep.byteClub.ui.designSystem.theme.Regular_10


@Composable
internal fun ByteClubBottomBar(
    modifier: Modifier = Modifier,
    destinations: List<BottomBarEntity>,
    onNavigateToDestination: (BottomBarEntity) -> Unit,
    currentDestination: NavDestination?,
    needToShowTopBar: MutableState<Boolean>,
) {
    ByteClubNavigationBar(
        modifier = modifier.height(70.dp),
    ) {

        destinations.forEach { destination ->
            val selected =
                (currentDestination.isSelectedDestinationInHierarchy(destination) && (currentDestination?.route == destination.destination))
            if (selected) {
                needToShowTopBar.value = destination.id.needTopBar
            }

            ByteClubNavigationBarItem(
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                    needToShowTopBar.value = destination.id.needTopBar
                },
                icon = {
                    Icon(
                        painter = painterResource(destination.id.outlinedIconRes),
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.id.filledIconRes),
                        contentDescription = null,
                    )
                },
                label = { Text(destination.title, style = Regular_10) },
            )
        }
    }
}


@Composable
fun ByteClubNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = ByteClubNavigationDefaults.navigationContentColor(),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 0.dp,
        content = content,
    )
}


@Composable
fun RowScope.ByteClubNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ByteClubNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = ByteClubNavigationDefaults.navigationUnselectedContentColor(),
            selectedTextColor = ByteClubNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = ByteClubNavigationDefaults.navigationUnselectedContentColor(),
            indicatorColor = ByteClubNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

private fun NavDestination?.isSelectedDestinationInHierarchy(destination: BottomBarEntity) =
    this?.hierarchy?.any {
        //not sure here
        it.route?.contains(destination.destination, true) ?: false
    } ?: false


object ByteClubNavigationDefaults {
    @Composable
    fun navigationContentColor() = Color.Transparent

    @Composable
    fun navigationUnselectedContentColor() = Color.White

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.primary

    @Composable
    fun navigationIndicatorColor() = Color.Transparent
}
