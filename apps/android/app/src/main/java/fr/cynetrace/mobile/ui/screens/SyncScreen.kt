package fr.cynetrace.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.cynetrace.mobile.data.local.SyncChangeEntity

@Composable
fun SyncScreen(syncChanges: List<SyncChangeEntity>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            SectionCard(
                title = "File de synchronisation",
                subtitle = "Append-only pour observations, traces et médias",
            ) {
                Text(
                    text = "Les révisions de géométrie sont conçues pour remonter vers une revue serveur en cas de conflit.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        items(syncChanges, key = { it.id }) { item ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${item.entityName} • ${item.operation}",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = item.entityId,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                    Text(
                        text = "${item.changedAt} • ${item.deviceId} • ${item.syncStatus}",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
        }
    }
}
