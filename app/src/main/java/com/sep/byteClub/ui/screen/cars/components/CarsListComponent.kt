package com.sep.byteClub.ui.screen.cars.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sep.byteClub.domain.entity.cars.CarEntity
import com.sep.byteClub.ui.designSystem.theme.ByteClubTheme

@Composable
fun CarsListComponent(modifier: Modifier = Modifier, list: List<CarEntity>) {

}


@Composable
fun CarsListItemComponent(modifier: Modifier = Modifier, carEntity: CarEntity) {
    Text(modifier = modifier, text = "sepehr testing")
}


private val mockCarEntity = CarEntity(0, "", 0, 0, 0.0, 0, 0, "", "", "", "", "")

@Preview
@Composable
private fun CarsListComponentPreview() {
    ByteClubTheme {
        CarsListComponent(list = listOf(mockCarEntity))
    }
}

@Preview
@Composable
private fun CarsListItemComponentPreview() {
    ByteClubTheme {
        CarsListItemComponent(carEntity = mockCarEntity)
    }
}