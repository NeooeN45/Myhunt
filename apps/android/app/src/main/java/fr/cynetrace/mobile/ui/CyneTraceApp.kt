package fr.cynetrace.mobile.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.PersonSearch
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.cynetrace.mobile.data.CyneTraceRepository
import fr.cynetrace.mobile.ui.screens.MapScreen
import fr.cynetrace.mobile.ui.screens.ObservationsScreen
import fr.cynetrace.mobile.ui.screens.OverviewScreen
import fr.cynetrace.mobile.ui.screens.SyncScreen

private enum class Destination(
    val label: String,
) {
    Overview("Pilotage"),
    Map("Carte"),
    Observations("Observations"),
    Sync("Sync"),
}

@Composable
fun CyneTraceApp(repository: CyneTraceRepository) {
    val zones by repository.observeZones().collectAsState(initial = emptyList())
    val observations by repository.observeObservations().collectAsState(initial = emptyList())
    val individuals by repository.observeIndividuals().collectAsState(initial = emptyList())
    val syncChanges by repository.observeSyncChanges().collectAsState(initial = emptyList())
    var destination by rememberSaveable { mutableStateOf(Destination.Overview) }

    val navItems = listOf(
        Destination.Overview to Icons.Outlined.Hub,
        Destination.Map to Icons.Outlined.Map,
        Destination.Observations to Icons.Outlined.PersonSearch,
        Destination.Sync to Icons.Outlined.Sync,
    )

    BoxWithConstraints {
        val useRail = maxWidth >= 900.dp

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("CyneTrace 2026", style = MaterialTheme.typography.titleLarge)
                    },
                )
            },
            bottomBar = {
                if (!useRail) {
                    NavigationBar {
                        navItems.forEach { (item, icon) ->
                            NavigationBarItem(
                                selected = destination == item,
                                onClick = { destination = item },
                                icon = { Icon(icon, contentDescription = item.label) },
                                label = { Text(item.label) },
                            )
                        }
                    }
                }
            },
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                if (useRail) {
                    NavigationRail {
                        navItems.forEach { (item, icon) ->
                            NavigationRailItem(
                                selected = destination == item,
                                onClick = { destination = item },
                                icon = { Icon(icon, contentDescription = item.label) },
                                label = { Text(item.label) },
                            )
                        }
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    tonalElevation = 2.dp,
                ) {
                    when (destination) {
                        Destination.Overview -> OverviewScreen(
                            zones = zones,
                            observations = observations,
                            individuals = individuals,
                            syncChanges = syncChanges,
                        )
                        Destination.Map -> MapScreen(
                            zones = zones,
                            pendingSyncCount = syncChanges.count { it.syncStatus == "pending_push" },
                        )
                        Destination.Observations -> ObservationsScreen(
                            observations = observations,
                            individuals = individuals,
                        )
                        Destination.Sync -> SyncScreen(syncChanges = syncChanges)
                    }
                }
            }
        }
    }
}
