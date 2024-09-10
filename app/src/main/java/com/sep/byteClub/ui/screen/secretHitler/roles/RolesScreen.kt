package com.sep.byteClub.ui.screen.secretHitler.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerRole
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme

@Composable
fun RolesScreen(
    modifier: Modifier = Modifier,
    viewModel: RolesViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val players = viewModel.players.collectAsStateWithLifecycle()
        val currentPlayerIndex = remember { mutableIntStateOf(0) }
        val roleState = remember { mutableStateOf(RoleRevealState.NOT_REVEALED) }
        when (roleState.value) {
            RoleRevealState.NOT_REVEALED -> {
                HiddenRoleComponent(onClick = {
                    roleState.value = RoleRevealState.REVEALED
                })
            }

            RoleRevealState.REVEALED -> {
                RevealRoleComponent(
                    player = players.value[currentPlayerIndex.intValue],
                    onClick = {
                        roleState.value = RoleRevealState.READY_FOR_NEXT
                    }
                )
            }

            RoleRevealState.READY_FOR_NEXT -> {
                ReadyForNextRoleComponent(
                    onClick = {
                        currentPlayerIndex.intValue += 1
                        roleState.value = RoleRevealState.NOT_REVEALED
                    }
                )
            }
        }
    }
}

@Composable
private fun ReadyForNextRoleComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier) {

    }
}

@Composable
private fun HiddenRoleComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier) {

    }
}

@Composable
private fun RevealRoleComponent(
    modifier: Modifier = Modifier,
    player: SecretHitlerPlayerEntity,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {

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
        HiddenRoleComponent(onClick = {})
    }
}

@Preview
@Composable
private fun RolesScreenPreview() {
    ByteClubTheme {
        RolesScreen()
    }
}