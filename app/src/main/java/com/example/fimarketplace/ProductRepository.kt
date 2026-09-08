package com.example.fimarketplace

object ProductRepository {

    val products = listOf(

        Product(
            id = 1,
            name = "iPhone 17 Pro",
            brand = "Apple",
            price = 125900,
            imageResId = R.drawable.iphone_17_pro,
            variants = listOf(
                "256 GB",
                "512 GB",
                "1 TB"
            ),
            emiPlans = listOf(
                EmiPlan(3, 0.0, 41967),
                EmiPlan(6, 0.0, 20983),
                EmiPlan(12, 0.0, 10492)
            )
        ),

        Product(
            id = 2,
            name = "Galaxy S26",
            brand = "Samsung",
            price = 89999,
            imageResId = R.drawable.galaxy_s26,
            variants = listOf(
                "128 GB",
                "256 GB",
                "512 GB"
            ),
            emiPlans = listOf(
                EmiPlan(3, 0.0, 30000),
                EmiPlan(6, 0.0, 15000),
                EmiPlan(12, 0.0, 7500)
            )
        ),

        Product(
            id = 3,
            name = "MacBook Air",
            brand = "Apple",
            price = 99900,
            imageResId = R.drawable.macbook_air,
            variants = listOf(
                "256 GB",
                "512 GB"
            ),
            emiPlans = listOf(
                EmiPlan(3, 0.0, 33300),
                EmiPlan(6, 0.0, 16650),
                EmiPlan(12, 0.0, 8325)
            )
        ),

        Product(
            id = 4,
            name = "OnePlus 13",
            brand = "OnePlus",
            price = 69999,
            imageResId = R.drawable.oneplus_13,
            variants = listOf(
                "128 GB",
                "256 GB"
            ),
            emiPlans = listOf(
                EmiPlan(3, 0.0, 23333),
                EmiPlan(6, 0.0, 11667),
                EmiPlan(12, 0.0, 5833)
            )
        )
    )
}