package fr.cynetrace.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.cynetrace.mobile.data.local.IndividualEntity
import fr.cynetrace.mobile.data.local.ObservationEntity
import fr.cynetrace.mobile.data.local.SyncChangeEntity
import fr.cynetrace.mobile.data.local.ZoneEntity

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OverviewScreen(
    zones: List<ZoneEntity>,
    observations: List<ObservationEntity>,
    individuals: List<IndividualEntity>,
    syncChanges: List<SyncChangeEntity>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OverviewChip("Zones", zones.size.toString())
                OverviewChip("Observations", observations.size.toString())
                OverviewChip("Individus", individuals.size.toString())
                OverviewChip("Sync pending", syncChanges.count { it.syncStatus == "pending_push" }.toString())
            }
        }

        item {
            SectionCard(
                title = "Territoires actifs",
                subtitle = "Zones principales et sous-zones versionnées",
            ) {
                zones.forEach { zone ->
                    Text(
                        text = "${zone.name} • ${zone.kind} • ${zone.areaHectares} ha",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        item {
            SectionCard(
                title = "Sujets prioritaires",
                subtitle = "Classement simple / interessant / important",
            ) {
                individuals.forEach { individual ->
                    Text(
                        text = "${individual.displayName} • ${individual.currentImportance} • poids ${individual.presenceWeight}",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        items(observations, key = { it.id }) { observation ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                ),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${observation.kind} • ${observation.speciesId}",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = observation.comment,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                    Text(
                        text = "${observation.weatherLabel} • GPS ${observation.gpsAccuracyMeters} m",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun OverviewChip(label: String, value: String) {
    AssistChip(
        onClick = {},
        label = { Text("$label : $value") },
    )
}
