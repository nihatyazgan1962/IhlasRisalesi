package com.ihlasrisalesi.analiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihlasrisalesi.analiz.data.IhlasDataProvider
import com.ihlasrisalesi.analiz.ui.theme.EmeraldPrimary
import com.ihlasrisalesi.analiz.ui.theme.GoldAccent
import com.ihlasrisalesi.analiz.ui.theme.GoldBright

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifteenDayTrackerScreen(modifier: Modifier = Modifier) {
    var completedIds by remember { mutableStateOf(setOf<String>()) }
    var streakCount by remember { mutableStateOf(1) }
    var daysRemaining by remember { mutableStateOf(12) }
    var showDialog by remember { mutableStateOf(false) }

    val totalCount = IhlasDataProvider.sections.size
    val completedCount = completedIds.size
    val progress = if (totalCount > 0) completedCount.toFloat() / totalCount else 0f

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("15 Günlük Okuma Takibi", fontWeight = FontWeight.Bold, fontSize = 19.sp)
                        Text("Bediüzzaman Said Nursî'nin Tavsiyesi", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = padding.calculateTopPadding() + 8.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Main Gauge Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldPrimary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = GoldAccent.copy(alpha = 0.3f)
                        ) {
                            Text(
                                text = "“Bu Lem'a lâakal her on beş günde bir defa okunmalı.”",
                                color = GoldBright,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "$daysRemaining", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = GoldBright)
                                Text(text = "Gün Kaldı", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                            }
                            VerticalDivider(
                                modifier = Modifier.height(40.dp),
                                color = Color.White.copy(alpha = 0.3f)
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "%${(progress * 100).toInt()}", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Text(text = "Tamamlandı", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                            }
                            VerticalDivider(
                                modifier = Modifier.height(40.dp),
                                color = Color.White.copy(alpha = 0.3f)
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "$streakCount", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = GoldBright)
                                Text(text = "Dönem Hatmi", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = GoldAccent,
                            trackColor = Color.White.copy(alpha = 0.2f),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                streakCount++
                                completedIds = emptySet()
                                daysRemaining = 15
                                showDialog = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldAccent)
                        ) {
                            Icon(Icons.Default.DoneAll, contentDescription = null, tint = Color.Black)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("15 Günlük Devri Tamamla", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Checklist section
            item {
                Text(
                    text = "Konu Başlıkları Takip Çizelgesi ($completedCount / $totalCount)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            items(IhlasDataProvider.sections) { section ->
                val isCompleted = completedIds.contains(section.id)
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = section.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = if (isCompleted) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = section.subtitle,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Checkbox(
                            checked = isCompleted,
                            onCheckedChange = { checked ->
                                completedIds = if (checked) completedIds + section.id else completedIds - section.id
                            },
                            colors = CheckboxDefaults.colors(checkedColor = EmeraldPrimary)
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Tebrikler! 🎉", fontWeight = FontWeight.Bold) },
                text = {
                    Text("İhlas Risalesi okumasını tamamladınız! 15 günlük yeni periyodunuz başlatıldı. Cenab-ı Hak amellerimizi ve hizmetlerimizi ihlasla taçlandırsın.")
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Âmin", fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                    }
                }
            )
        }
    }
}
