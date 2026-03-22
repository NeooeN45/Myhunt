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
import fr.cynetrace.mobile.data.local.IndividualEntity
import fr.cynetrace.mobile.data.local.ObservationEntity

@Composable
fun ObservationsScreen(
    observations: List<ObservationEntity>,
    individuals: List<IndividualEntity>,
) {
    val individualNames = individuals.associateBy({ it.id }, { it.displayName })

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            SectionCard(
                title = "Journal terrain",
                subtitle = "Indices, traces, prélèvements et observations typées",
            ) {
                Text(
                    text = "Toutes les entrées restent valides même sans individu assigné.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        items(observations, key = { it.id }) { observation ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${observation.speciesId} • ${observation.kind}",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = individualNames[observation.individualId] ?: "Individu non assigné",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                    Text(
                        text = observation.comment,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                    Text(
                        text = "${observation.observedAt} • ${observation.weatherLabel}",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
        }
    }
}
