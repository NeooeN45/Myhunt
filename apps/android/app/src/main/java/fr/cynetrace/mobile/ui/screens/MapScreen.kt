package fr.cynetrace.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import fr.cynetrace.mobile.R
import fr.cynetrace.mobile.data.local.ZoneEntity

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MapScreen(
    zones: List<ZoneEntity>,
    pendingSyncCount: Int,
) {
    val context = LocalContext.current
    val token = remember { context.getString(R.string.mapbox_access_token) }

    if (token.startsWith("YOUR_MAPBOX_ACCESS_TOKEN")) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLow),
            contentAlignment = Alignment.Center,
        ) {
            Card(modifier = Modifier.padding(24.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Configurer le token Mapbox",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = "Ajoute ton token public dans res/values/mapbox_access_token.xml pour activer la carte 3D et les futures couches IGN.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
        }

        return
    }

    Box(modifier = Modifier.fillMaxSize()) {
        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = rememberMapViewportState {
                setCameraOptions {
                    zoom(11.8)
                    center(Point.fromLngLat(2.551, 46.119))
                    pitch(48.0)
                    bearing(12.0)
                }
            },
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Vue territoire",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "${zones.size} zones locales • $pendingSyncCount changements en attente",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text("Plan IGN") })
                AssistChip(onClick = {}, label = { Text("Orthophoto") })
                AssistChip(onClick = {}, label = { Text("Relief 3D") })
                AssistChip(onClick = {}, label = { Text("Presence 2026") })
            }
        }
    }
}
