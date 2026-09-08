package com.example.fimarketplace

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductDetailScreen(
    product: Product,
    onBack: () -> Unit,
    onContinueToBuy: (
        Product,
        String,
        EmiPlan
    ) -> Unit
) {

    var selectedVariant by remember {
        mutableStateOf(
            product.variants.firstOrNull() ?: ""
        )
    }

    var selectedEmiPlan by remember {
        mutableStateOf(
            product.emiPlans.firstOrNull()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FC))
    ) {

        // =====================================
        // TOP BAR
        // =====================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

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
                text = "Product Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // =====================================
        // CONTENT
        // =====================================

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            // =====================================
            // PRODUCT IMAGE
            // =====================================

            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
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
                            modifier = Modifier.size(220.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            // =====================================
            // PRODUCT INFORMATION
            // =====================================

            item {

                Text(
                    text = product.brand.uppercase(),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = product.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 3.dp)
                )

                Text(
                    text = "₹${product.price}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // =====================================
            // VARIANT TITLE
            // =====================================

            item {

                Text(
                    text = "Select Variant",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            // =====================================
            // VARIANTS
            // =====================================

            items(product.variants) { variant ->

                val isSelected =
                    selectedVariant == variant

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedVariant = variant
                        },
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (isSelected) {
                                Color(0xFFF0EAFB)
                            } else {
                                Color.White
                            }
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = if (isSelected) {
                                "✓"
                            } else {
                                "○"
                            },
                            fontSize = 20.sp,
                            color = Color(0xFF6C4AB6)
                        )

                        Text(
                            text = variant,
                            fontSize = 16.sp,
                            fontWeight =
                                if (isSelected) {
                                    FontWeight.SemiBold
                                } else {
                                    FontWeight.Normal
                                },
                            modifier = Modifier.padding(
                                start = 12.dp
                            )
                        )
                    }
                }
            }

            // =====================================
            // EMI TITLE
            // =====================================

            item {

                Text(
                    text = "Choose EMI Plan",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "Select a plan that works for you",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // =====================================
            // EMI PLANS
            // =====================================

            items(product.emiPlans) { plan ->

                val isSelected =
                    selectedEmiPlan == plan

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedEmiPlan = plan
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (isSelected) {
                                Color(0xFFF0EAFB)
                            } else {
                                Color.White
                            }
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement =
                                Arrangement.SpaceBetween,
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Text(
                                text = "${plan.months} Months",
                                fontSize = 17.sp,
                                fontWeight =
                                    if (isSelected) {
                                        FontWeight.Bold
                                    } else {
                                        FontWeight.Medium
                                    }
                            )

                            Text(
                                text = if (isSelected) {
                                    "✓ Selected"
                                } else {
                                    "○ Select"
                                },
                                fontSize = 13.sp,
                                color = Color(0xFF6C4AB6),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Text(
                            text = "₹${plan.monthlyAmount}/month",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5B3A9E),
                            modifier = Modifier.padding(
                                top = 8.dp
                            )
                        )

                        Text(
                            text = "Interest: ${plan.interestRate}%",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(
                                top = 4.dp
                            )
                        )
                    }
                }
            }

            // =====================================
            // SELECTED EMI SUMMARY
            // =====================================

            item {

                selectedEmiPlan?.let { plan ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF6C4AB6)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Text(
                                text = "Your selected EMI",
                                fontSize = 14.sp,
                                color = Color.White
                            )

                            Text(
                                text = "₹${plan.monthlyAmount}/month",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(
                                    top = 4.dp
                                )
                            )

                            Text(
                                text = "for ${plan.months} months • ${plan.interestRate}% interest",
                                fontSize = 13.sp,
                                color = Color.White,
                                modifier = Modifier.padding(
                                    top = 3.dp
                                )
                            )
                        }
                    }
                }
            }

            // =====================================
            // CONTINUE TO BUY
            // =====================================

            item {

                Button(
                    onClick = {

                        selectedEmiPlan?.let { plan ->

                            onContinueToBuy(
                                product,
                                selectedVariant,
                                plan
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 4.dp,
                            bottom = 24.dp
                        ),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6C4AB6)
                    )
                ) {

                    Text(
                        text = "Continue to Buy",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            vertical = 4.dp
                        )
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
        }
    }
}