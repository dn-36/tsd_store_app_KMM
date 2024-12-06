package com.viewmodel

sealed class ProductsMenuIntents {

    object Categories: ProductsMenuIntents()

    object UnitsMeasurement: ProductsMenuIntents()

    object GoodsAndServices: ProductsMenuIntents()

}