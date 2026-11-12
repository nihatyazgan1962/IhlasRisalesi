package com.ihlasrisalesi.analiz.data

enum class CategoryType(val title: String) {
    GIRIS("Giriş ve İhtar"),
    DUSTURLAR("4 Temel Düstur"),
    MANILER("İhlası Kıran Mâniler"),
    CARE_LER("İhlası Kazandıran Çareler"),
    YIRMINCI_LEMA("20. Lem'a: İhtilaf Sebepleri ve Reçeteler"),
    LUGAT("Risale Kavramları ve Lügatçe")
}

data class SectionItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: CategoryType,
    val originalText: String,
    val modernExplanation: String,
    val keyLessons: List<String>,
    val reflectionQuestions: List<String>,
    val iconName: String = "book"
)

data class GlossaryItem(
    val term: String,
    val meaning: String,
    val contextInIhlas: String
)

data class ReadingProgress(
    val lastReadTimestamp: Long = 0L,
    val completedSectionIds: Set<String> = emptySet(),
    val notes: Map<String, String> = emptyMap(),
    val favoriteIds: Set<String> = emptySet()
)

