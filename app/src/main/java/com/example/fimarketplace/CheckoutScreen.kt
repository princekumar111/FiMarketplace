package com.example.fimarketplace

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
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
fun CheckoutScreen(
    product: Product,
    selectedVariant: String,
    selectedEmiPlan: EmiPlan,
    onBack: () -> Unit,
    onPlaceOrder: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FC))
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // =====================================
        // TOP BAR
        // =====================================

        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 18.dp,
                        bottom = 8.dp
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
                    text = "Checkout",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "Review your order before placing it",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(
                    bottom = 6.dp
                )
            )
        }

        // =====================================
        // PRODUCT CARD
        // =====================================

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
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

                    // Product image
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
                        horizontalAlignment =
                            Alignment.CenterHorizontally,
                        verticalArrangement =
                            Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(
                                id = product.imageResId
                            ),
                            contentDescription = product.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Text(
                        text = product.brand.uppercase(),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(
                            top = 14.dp
                        )
                    )

                    Text(
                        text = product.name,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 3.dp
                        )
                    )

                    Text(
                        text = "Variant: $selectedVariant",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(
                            top = 7.dp
                        )
                    )

                    Text(
                        text = "₹${product.price}",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 8.dp
                        )
                    )
                }
            }
        }

        // =====================================
        // EMI CARD
        // =====================================

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0EAFB)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Selected EMI Plan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "${selectedEmiPlan.months} Months",
                        fontSize = 15.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(
                            top = 8.dp
                        )
                    )

                    Text(
                        text =
                            "₹${selectedEmiPlan.monthlyAmount}/month",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B3A9E),
                        modifier = Modifier.padding(
                            top = 4.dp
                        )
                    )

                    Text(
                        text =
                            "${selectedEmiPlan.interestRate}% interest",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(
                            top = 3.dp
                        )
                    )
                }
            }
        }

        // =====================================
        // ORDER SUMMARY
        // =====================================

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Order Summary",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    SummaryRow(
                        title = "Product",
                        value = product.name
                    )

                    SummaryRow(
                        title = "Variant",
                        value = selectedVariant
                    )

                    SummaryRow(
                        title = "Product Price",
                        value = "₹${product.price}"
                    )

                    SummaryRow(
                        title = "EMI Duration",
                        value =
                            "${selectedEmiPlan.months} months"
                    )

                    SummaryRow(
                        title = "Monthly Payment",
                        value =
                            "₹${selectedEmiPlan.monthlyAmount}"
                    )
                }
            }
        }

        // =====================================
        // FINAL PAYMENT CARD
        // =====================================

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF6C4AB6)
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Your monthly EMI",
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Text(
                        text =
                            "₹${selectedEmiPlan.monthlyAmount}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(
                            top = 3.dp
                        )
                    )

                    Text(
                        text =
                            "for ${selectedEmiPlan.months} months",
                        fontSize = 13.sp,
                        color = Color.White,
                        modifier = Modifier.padding(
                            top = 2.dp
                        )
                    )
                }
            }
        }

        // =====================================
        // PLACE ORDER BUTTON
        // =====================================

        item {

            Button(
                onClick = {
                    onPlaceOrder()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 2.dp,
                        bottom = 28.dp
                    ),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C4AB6)
                )
            ) {

                Text(
                    text = "Place Order",
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


// =====================================
// SUMMARY ROW
// =====================================

@Composable
fun SummaryRow(
    title: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceBetween,
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}