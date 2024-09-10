package com.sep.byteClub.ui.screen.secretHitler.roles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sep.byteClub.R
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerRole
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.Medium_12
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_32
import kotlinx.coroutines.delay

@Composable
fun RolesScreen(
    modifier: Modifier = Modifier,
    viewModel: RolesViewModel = hiltViewModel(),
    onNavigateToBoard: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val players = viewModel.players.collectAsStateWithLifecycle()
        val currentPlayerIndex = remember { mutableIntStateOf(0) }
        val roleState = remember { mutableStateOf(RoleRevealState.NOT_REVEALED) }
        if (currentPlayerIndex.intValue == players.value.size - 1) {
            onNavigateToBoard.invoke()
        }
        if (players.value.isNotEmpty()) {
            when (roleState.value) {
                RoleRevealState.NOT_REVEALED -> {
                    HiddenRoleComponent(
                        player = players.value[currentPlayerIndex.intValue],
                        onClick = {
                            roleState.value = RoleRevealState.REVEALED
                        })
                }

                RoleRevealState.REVEALED -> {
                    RevealRoleComponent(
                        player = players.value[currentPlayerIndex.intValue],
                        onClick = {
                            roleState.value = RoleRevealState.NOT_REVEALED
                            currentPlayerIndex.intValue += 1
                        }
                    )
                }

            }
        }
    }
}


@Composable
private fun HiddenRoleComponent(
    modifier: Modifier = Modifier,
    player: SecretHitlerPlayerEntity,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = player.name, style = Bold_20, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(padding_32))
        Text(
            text = stringResource(id = R.string.hidden_player),
            style = Medium_12,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun RevealRoleComponent(
    modifier: Modifier = Modifier,
    player: SecretHitlerPlayerEntity,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var visible by remember { mutableStateOf(true) }

        LaunchedEffect(key1 = player.role.name) {
            while (true) {
                visible = false
                delay(500)
                visible = true
                delay(500)
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = TweenSpec(durationMillis = 1000)),
            exit = fadeOut(animationSpec = TweenSpec(durationMillis = 1000))
        ) {
            Text(
                text = player.role.name,
                style = Bold_20,
                color = player.role.color,
            )
        }
        Spacer(modifier = Modifier.height(padding_32))
        Text(
            text = stringResource(id = R.string.revealed_player),
            style = Medium_12,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

private val mockPlayer = SecretHitlerPlayerEntity(
    name = "Sepi",
    role = SecretHitlerRole.LIBERAL
)

@Preview
@Composable
private fun RevealRoleComponentPreview() {
    ByteClubTheme {
        RevealRoleComponent(player = mockPlayer, onClick = {})
    }
}

@Preview
@Composable
private fun HiddenRoleComponentPreview() {
    ByteClubTheme {
        HiddenRoleComponent(onClick = {}, player = mockPlayer)
    }
}

@Preview
@Composable
private fun RolesScreenPreview() {
    ByteClubTheme {
        RolesScreen(onNavigateToBoard = {})
    }
}