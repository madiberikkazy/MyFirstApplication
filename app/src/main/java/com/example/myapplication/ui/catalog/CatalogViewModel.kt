package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.CatalogItem

class CatalogViewModel : ViewModel() {

    private val _items = MutableLiveData(
        List(10) {
            CatalogItem(
                id = it,
                title = "Item $it",
                desc = "Description for item $it",
                price = 100.0 + it,
                isFav = false,
                name = "Item $it"
            )
        }
    )

    val items: LiveData<List<CatalogItem>> = _items

    fun toggleFav(id: Int) {
        _items.value = _items.value?.map {
            if (it.id == id) it.copy(isFav = !it.isFav) else it
        }
    }

    fun getItemById(id: Int): CatalogItem? =
        _items.value?.find { it.id == id }
}