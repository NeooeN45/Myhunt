# CyneTrace Android

Application Android native pour la saisie terrain hors-ligne :

- Kotlin + Jetpack Compose
- Room comme source de vérité locale
- WorkManager pour la synchronisation périodique
- Mapbox Compose pour la carte et les futures couches IGN

## Pré-requis

- Android Studio récent
- Android SDK 36
- JDK 17

## Configuration

1. Ouvrir `apps/android` dans Android Studio.
2. Remplacer la valeur de `app/src/main/res/values/mapbox_access_token.xml` par un token public Mapbox.
3. Synchroniser Gradle.
