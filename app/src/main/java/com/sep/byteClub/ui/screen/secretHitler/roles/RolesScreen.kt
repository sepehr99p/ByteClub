package com.sep.byteClub.ui.screen.secretHitler.roles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sep.byteClub.R
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerPlayerEntity
import com.sep.byteClub.domain.entiry.secretHitler.SecretHitlerRole
import com.sep.byteClub.ui.designSystem.components.button.ButtonComponent
import com.sep.byteClub.ui.designSystem.components.button.ButtonStyle
import com.sep.byteClub.ui.designSystem.theme.Bold_20
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme
import com.sep.byteClub.ui.designSystem.theme.Medium_12
import com.sep.byteClub.ui.designSystem.theme.dimen.padding_16

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


@Composable
private fun HiddenRoleComponent(
    modifier: Modifier = Modifier,
    player: SecretHitlerPlayerEntity,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = player.name, style = Medium_12)
        ButtonComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding_16),
            onclick = onClick,
            titleRes = R.string.hidden_player,
            buttonStyle = ButtonStyle.Dismiss
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
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick.invoke() },
            text = player.role.name,
            style = Bold_20,
            color = player.role.color
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
        RolesScreen()
    }
}