package com.example.fimarketplace

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShopScreen(
    onMarketplaceClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Shop",
            fontSize = 28.sp
        )

        ShopOption(
            title = "Top Brands",
            subtitle = "Explore products from top brands"
        )

        ShopOption(
            title = "Nearby Stores",
            subtitle = "Find stores near you"
        )

        ShopOption(
            title = "1Fi Marketplace",
            subtitle = "Shop products with easy EMI",
            onClick = onMarketplaceClick
        )
    }
}

@Composable
fun ShopOption(
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp
            )

            Text(
                text = subtitle,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}