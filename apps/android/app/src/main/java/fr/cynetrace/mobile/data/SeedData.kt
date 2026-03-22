package fr.cynetrace.mobile.data

import fr.cynetrace.mobile.data.local.IndividualEntity
import fr.cynetrace.mobile.data.local.ObservationEntity
import fr.cynetrace.mobile.data.local.SyncChangeEntity
import fr.cynetrace.mobile.data.local.ZoneEntity

object SeedData {
    val zones = listOf(
        ZoneEntity(
            id = "zone-foret-haute",
            parentZoneId = null,
            name = "Foret Haute",
            kind = "reserve_chasse",
            seasonYear = 2026,
            effectiveFrom = "2026-01-01T00:00:00Z",
            effectiveTo = null,
            areaHectares = 486.3,
            centroidLng = 2.545,
            centroidLat = 46.112,
            syncStatus = "synced",
        ),
        ZoneEntity(
            id = "zone-combe-cerf",
            parentZoneId = "zone-foret-haute",
            name = "Combe du Cerf",
            kind = "zt_onf",
            seasonYear = 2026,
            effectiveFrom = "2026-02-15T00:00:00Z",
            effectiveTo = null,
            areaHectares = 78.5,
            centroidLng = 2.551,
            centroidLat = 46.119,
            syncStatus = "pending_push",
        ),
    )

    val observations = listOf(
        ObservationEntity(
            id = "obs-001",
            zoneId = "zone-combe-cerf",
            individualId = "indiv-cerf-9c",
            speciesId = "cerf",
            seasonYear = 2026,
            observedAt = "2026-03-12T06:14:00Z",
            createdBy = "member",
            syncStatus = "synced",
            kind = "observation",
            weatherLabel = "froid sec, vent faible",
            gpsAccuracyMeters = 4.2,
            comment = "Entendu au brame, deplacement vers le point d eau.",
            longitude = 2.5538,
            latitude = 46.1203,
        ),
        ObservationEntity(
            id = "obs-002",
            zoneId = "zone-combe-cerf",
            individualId = null,
            speciesId = "sanglier",
            seasonYear = 2026,
            observedAt = "2026-03-13T21:32:00Z",
            createdBy = "member",
            syncStatus = "pending_push",
            kind = "trace",
            weatherLabel = "humide, leger crachin",
            gpsAccuracyMeters = 6.8,
            comment = "Passage repete en lisiere.",
            longitude = 2.5486,
            latitude = 46.1182,
        ),
    )

    val individuals = listOf(
        IndividualEntity(
            id = "indiv-cerf-9c",
            speciesId = "cerf",
            displayName = "Cerf 9C",
            sex = "male",
            currentImportance = "important",
            seasonYear = 2026,
            presenceWeight = 3,
            notes = "Sujet dominant suivi depuis 2024.",
            syncStatus = "synced",
        ),
        IndividualEntity(
            id = "indiv-chev-2b",
            speciesId = "chevreuil",
            displayName = "Brocard 2B",
            sex = "male",
            currentImportance = "interessant",
            seasonYear = 2026,
            presenceWeight = 2,
            notes = "Trophee en developpement.",
            syncStatus = "pending_push",
        ),
    )

    val syncChanges = listOf(
        SyncChangeEntity(
            id = "sync-change-1",
            entityName = "zone_revision",
            entityId = "zone-combe-cerf",
            operation = "upsert",
            changedAt = "2026-03-21T16:10:00Z",
            deviceId = "phone-forest-alpha",
            syncStatus = "pending_push",
        ),
        SyncChangeEntity(
            id = "sync-change-2",
            entityName = "observation",
            entityId = "obs-002",
            operation = "upsert",
            changedAt = "2026-03-21T16:12:00Z",
            deviceId = "phone-forest-alpha",
            syncStatus = "pending_push",
        ),
    )
}
