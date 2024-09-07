package com.sep.byteClub.ui.screen.secretHitler.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.core.cartesian.marker.ColumnCartesianLayerMarkerTarget
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

    }
}

@Composable
private fun HiddenRoleComponent(modifier: Modifier = Modifier) {
    
}

@Composable
private fun RevealRoleComponent(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun RevealRoleComponentPreview() {
    ByteClubTheme {
        RevealRoleComponent()
    }
}

@Preview
@Composable
private fun HiddenRoleComponentPreview() {
    ByteClubTheme {
        HiddenRoleComponent()
    }
}

@Preview
@Composable
private fun RolesScreenPreview() {
    ByteClubTheme {
        RolesScreen()
    }
}