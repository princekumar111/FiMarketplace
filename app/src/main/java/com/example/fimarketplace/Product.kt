package com.example.fimarketplace

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Int,
    val imageResId: Int,
    val variants: List<String>,
    val emiPlans: List<EmiPlan>
)

data class EmiPlan(
    val months: Int,
    val interestRate: Double,
    val monthlyAmount: Int
)