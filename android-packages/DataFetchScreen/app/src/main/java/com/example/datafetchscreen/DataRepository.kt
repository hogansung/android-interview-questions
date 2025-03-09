package com.example.datafetchscreen

import kotlinx.coroutines.delay

class DataRepository {
    suspend fun fetchData(): List<String> {
        delay(2000) // 模擬網路延遲
        return listOf("Snoopy 1", "Snoopy 2", "Snoopy 3", "Snoopy 4", "Snoopy 5")
    }
}