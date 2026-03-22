# CyneTrace Platform

Socle monorepo pour une plateforme de suivi cynégétique premium orientée terrain :

- `apps/android` : application Android native Kotlin/Compose, Room, WorkManager, Mapbox.
- `apps/api` : API NestJS pour sync, catalogue, zones, observations et futurs calculs géospatiaux.
- `apps/backoffice` : backoffice Next.js pour supervision, revue IA et administration des couches.
- `packages/contracts` : contrats TypeScript partagés, énumérations métier et jeu de données de démonstration.
- `infra` : infrastructure locale PostGIS, MinIO et scripts SQL d’amorçage.

## Démarrage

```bash
npm install
npm run build:contracts
npm run dev:api
npm run dev:backoffice
```

L’API expose un préfixe `/api` et des endpoints de base comme `/api/health`, `/api/catalog/species`, `/api/zones` et `/api/sync/pull`.

## Android

Le projet Android est prêt à être ouvert dans Android Studio depuis [`apps/android`](C:\Users\camil\Documents\New project\apps\android). Cette machine n’ayant pas Android SDK installé, le build Android n’a pas pu être exécuté ici, mais les scripts Gradle, la structure Compose et les modules Room/WorkManager/Mapbox sont présents.

## Infrastructure locale

```bash
docker compose -f infra/docker-compose.yml up -d
```

Services inclus :

- PostgreSQL 17 + PostGIS 3.5
- MinIO pour médias et exports
- Console MinIO

## Données et périmètre initial

- France d’abord
- Cartes premium IGN + rendu Mapbox
- Zones et sous-zones versionnées
- Observations, prélèvements, individus, GPS, pièges photo
- Synchronisation offline-first avec résolution de conflits côté serveur
