package com.example.fimarketplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderConfirmationScreen(
    product: Product,
    selectedVariant: String,
    selectedEmiPlan: EmiPlan,
    onBackToShop: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FC))
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        // =====================================
        // SUCCESS ICON
        // =====================================

        Card(
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8DFF8)
            )
        ) {

            Text(
                text = "✓",
                fontSize = 42.sp,
                color = Color(0xFF6C4AB6),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 22.dp,
                    vertical = 10.dp
                )
            )
        }

        // =====================================
        // SUCCESS MESSAGE
        // =====================================

        Text(
            text = "Order Placed!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "Your order has been successfully placed.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(
                top = 6.dp
            )
        )

        // =====================================
        // ORDER ID
        // =====================================

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Order ID",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "1FI-MKT-001",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // =====================================
        // PRODUCT SUMMARY
        // =====================================

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Order Details",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Image(
                    painter = painterResource(
                        id = product.imageResId
                    ),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(top = 12.dp)
                )

                Text(
                    text = product.name,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 12.dp
                    )
                )

                Text(
                    text = product.brand,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(
                        top = 3.dp
                    )
                )

                Text(
                    text = "Variant: $selectedVariant",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        top = 10.dp
                    )
                )

                Text(
                    text = "Price: ₹${product.price}",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(
                        top = 6.dp
                    )
                )
            }
        }

        // =====================================
        // EMI SUMMARY
        // =====================================

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0EAFB)
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "EMI Plan",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text =
                        "₹${selectedEmiPlan.monthlyAmount}/month",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5B3A9E),
                    modifier = Modifier.padding(
                        top = 6.dp
                    )
                )

                Text(
                    text =
                        "${selectedEmiPlan.months} months • " +
                                "${selectedEmiPlan.interestRate}% interest",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(
                        top = 3.dp
                    )
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =====================================
        // BACK TO SHOP
        // =====================================

        Button(
            onClick = {
                onBackToShop()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6C4AB6)
            )
        ) {

            Text(
                text = "Back to Shop",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    vertical = 4.dp
                )
            )
        }
    }
}