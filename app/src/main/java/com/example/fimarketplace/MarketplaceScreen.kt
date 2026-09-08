package com.example.fimarketplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MarketplaceScreen(
    onBack: () -> Unit,
    onProductClick: (Product) -> Unit,
    productViewModel: ProductViewModel = viewModel()
) {

    val uiState by productViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FC))
            .padding(horizontal = 20.dp)
    ) {

        // ==============================
        // TOP BAR
        // ==============================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 18.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Proper 48dp Back Button
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onBack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "←",
                    fontSize = 26.sp
                )
            }

            Text(
                text = "1Fi Marketplace",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Shop products with easy EMI",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(
                bottom = 18.dp
            )
        )


        // ==============================
        // UI STATES
        // ==============================

        when (uiState) {

            // --------------------------
            // LOADING
            // --------------------------

            ProductUiState.Loading -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    CircularProgressIndicator()

                    Text(
                        text = "Loading products...",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }


            // --------------------------
            // SUCCESS
            // --------------------------

            is ProductUiState.Success -> {

                val products =
                    (uiState as ProductUiState.Success).products

                if (products.isEmpty()) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "No products available",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                } else {

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {

                        items(products) { product ->

                            ProductCard(
                                product = product,
                                onClick = {
                                    onProductClick(product)
                                }
                            )
                        }

                        item {

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )
                        }
                    }
                }
            }


            // --------------------------
            // ERROR
            // --------------------------

            is ProductUiState.Error -> {

                val message =
                    (uiState as ProductUiState.Error).message

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Unable to load products",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = message,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Button(
                        onClick = {
                            productViewModel.loadProducts()
                        },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6C4AB6)
                        )
                    ) {

                        Text(
                            text = "Retry"
                        )
                    }
                }
            }
        }
    }
}


// ======================================
// PRODUCT CARD
// ======================================

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            // ==============================
            // IMAGE
            // ==============================

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(
                        Color(0xFFF1EFF7)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(
                        id = product.imageResId
                    ),
                    contentDescription = product.name,
                    modifier = Modifier.size(170.dp),
                    contentScale = ContentScale.Fit
                )
            }


            // ==============================
            // BRAND
            // ==============================

            Text(
                text = product.brand.uppercase(),
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(
                    top = 14.dp
                )
            )


            // ==============================
            // PRODUCT NAME
            // ==============================

            Text(
                text = product.name,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    top = 3.dp
                )
            )


            // ==============================
            // PRICE
            // ==============================

            Text(
                text = "₹${product.price}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = 8.dp
                )
            )


            // ==============================
            // EMI BADGE
            // ==============================

            val firstEmi =
                product.emiPlans.firstOrNull()

            if (firstEmi != null) {

                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF0EAFB)
                    ),
                    modifier = Modifier.padding(
                        top = 10.dp
                    )
                ) {

                    Text(
                        text = "EMI from ₹${firstEmi.monthlyAmount}/month",
                        fontSize = 13.sp,
                        color = Color(0xFF5B3A9E),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 7.dp
                        )
                    )
                }
            }


            // ==============================
            // BUTTON
            // ==============================

            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C4AB6)
                )
            ) {

                Text(
                    text = "View Details",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}