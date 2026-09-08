package com.example.fimarketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.fimarketplace.ui.theme.FiMarketplaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            FiMarketplaceTheme {

                // ==========================================
                // SCREEN STATE
                // ==========================================

                var currentScreen by remember {
                    mutableStateOf("SHOP")
                }

                // Selected product
                var selectedProduct by remember {
                    mutableStateOf<Product?>(null)
                }

                // Checkout information
                var checkoutProduct by remember {
                    mutableStateOf<Product?>(null)
                }

                var checkoutVariant by remember {
                    mutableStateOf("")
                }

                var checkoutEmiPlan by remember {
                    mutableStateOf<EmiPlan?>(null)
                }


                // ==========================================
                // NAVIGATION FUNCTIONS
                // ==========================================

                fun goToShop() {

                    currentScreen = "SHOP"
                }

                fun goToMarketplace() {

                    currentScreen = "MARKETPLACE"
                }

                fun goToProductDetail(product: Product) {

                    selectedProduct = product
                    currentScreen = "PRODUCT_DETAIL"
                }

                fun goToCheckout(
                    product: Product,
                    variant: String,
                    emiPlan: EmiPlan
                ) {

                    checkoutProduct = product
                    checkoutVariant = variant
                    checkoutEmiPlan = emiPlan

                    currentScreen = "CHECKOUT"
                }

                fun goToConfirmation() {

                    currentScreen = "CONFIRMATION"
                }


                // ==========================================
                // BACK NAVIGATION
                // ==========================================

                fun goBack() {

                    when (currentScreen) {

                        "SHOP" -> {

                            finish()
                        }

                        "MARKETPLACE" -> {

                            currentScreen = "SHOP"
                        }

                        "PRODUCT_DETAIL" -> {

                            selectedProduct = null

                            currentScreen = "MARKETPLACE"
                        }

                        "CHECKOUT" -> {

                            // IMPORTANT:
                            // Product information ko null nahi karna.
                            // Sirf previous screen par jaana hai.

                            currentScreen = "PRODUCT_DETAIL"
                        }

                        "CONFIRMATION" -> {

                            checkoutProduct = null
                            checkoutVariant = ""
                            checkoutEmiPlan = null

                            selectedProduct = null

                            currentScreen = "SHOP"
                        }
                    }
                }


                // ==========================================
                // ANDROID PHONE BACK BUTTON
                // ==========================================

                BackHandler {

                    goBack()
                }


                // ==========================================
                // SCREEN DISPLAY
                // ==========================================

                Surface {

                    when (currentScreen) {


                        // ======================================
                        // SHOP
                        // ======================================

                        "SHOP" -> {

                            ShopScreen(
                                onMarketplaceClick = {

                                    goToMarketplace()
                                }
                            )
                        }


                        // ======================================
                        // MARKETPLACE
                        // ======================================

                        "MARKETPLACE" -> {

                            MarketplaceScreen(

                                onBack = {

                                    goBack()
                                },

                                onProductClick = { product ->

                                    goToProductDetail(product)
                                }
                            )
                        }


                        // ======================================
                        // PRODUCT DETAIL
                        // ======================================

                        "PRODUCT_DETAIL" -> {

                            if (selectedProduct != null) {

                                ProductDetailScreen(

                                    product = selectedProduct!!,

                                    onBack = {

                                        goBack()
                                    },

                                    onContinueToBuy = {
                                            product,
                                            variant,
                                            emiPlan ->

                                        goToCheckout(
                                            product = product,
                                            variant = variant,
                                            emiPlan = emiPlan
                                        )
                                    }
                                )
                            }
                        }


                        // ======================================
                        // CHECKOUT
                        // ======================================

                        "CHECKOUT" -> {

                            if (
                                checkoutProduct != null &&
                                checkoutEmiPlan != null
                            ) {

                                CheckoutScreen(

                                    product = checkoutProduct!!,

                                    selectedVariant = checkoutVariant,

                                    selectedEmiPlan = checkoutEmiPlan!!,

                                    onBack = {

                                        goBack()
                                    },

                                    onPlaceOrder = {

                                        goToConfirmation()
                                    }
                                )
                            }
                        }


                        // ======================================
                        // ORDER CONFIRMATION
                        // ======================================

                        "CONFIRMATION" -> {

                            if (
                                checkoutProduct != null &&
                                checkoutEmiPlan != null
                            ) {

                                OrderConfirmationScreen(

                                    product = checkoutProduct!!,

                                    selectedVariant = checkoutVariant,

                                    selectedEmiPlan = checkoutEmiPlan!!,

                                    onBackToShop = {

                                        checkoutProduct = null
                                        checkoutVariant = ""
                                        checkoutEmiPlan = null
                                        selectedProduct = null

                                        goToShop()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}