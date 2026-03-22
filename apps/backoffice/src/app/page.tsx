import {
  dashboardSnapshot,
  demoIndividuals,
  demoObservations,
  demoZones,
  mapLayers,
  pendingSyncChanges,
  populationSnapshots,
} from '@cynetrace/contracts';

const formatHour = (value: string) =>
  new Intl.DateTimeFormat('fr-FR', {
    hour: '2-digit',
    minute: '2-digit',
  }).format(new Date(value));

export default function Home() {
  const importantIndividuals = demoIndividuals.filter(
    (item) => item.currentImportance === 'important',
  );

  return (
    <main className="page-shell">
      <section className="hero">
        <div className="hero-copy">
          <span className="eyebrow">CyneTrace Control Room</span>
          <h1>Suivi terrain, revue IA et gouvernance cartographique.</h1>
          <p>
            Backoffice de supervision pour les zones versionnées, les pièges
            photo, les synchronisations hors-ligne et les analyses de présence.
          </p>
        </div>

        <div className="hero-panel">
          <div className="hero-stat">
            <strong>{dashboardSnapshot.trackedZones}</strong>
            <span>territoires suivis</span>
          </div>
          <div className="hero-stat">
            <strong>{dashboardSnapshot.activeCameraTraps}</strong>
            <span>pièges actifs</span>
          </div>
          <div className="hero-stat accent">
            <strong>{dashboardSnapshot.aiReviewQueue}</strong>
            <span>captures à valider</span>
          </div>
        </div>
      </section>

      <section className="metrics-grid">
        <article className="metric-card">
          <span>Utilisateurs actifs</span>
          <strong>{dashboardSnapshot.activeUsers}</strong>
          <small>Equipe terrain + supervision</small>
        </article>
        <article className="metric-card">
          <span>Uploads en attente</span>
          <strong>{dashboardSnapshot.pendingUploads}</strong>
          <small>Médias hors réseau non consolidés</small>
        </article>
        <article className="metric-card">
          <span>Conflits de sync</span>
          <strong>{dashboardSnapshot.conflictQueue}</strong>
          <small>Révisions à arbitrer avant diffusion</small>
        </article>
        <article className="metric-card">
          <span>Observations terrain</span>
          <strong>{demoObservations.length}</strong>
          <small>Flux de la saison 2026</small>
        </article>
      </section>

      <section className="dashboard-grid">
        <article className="panel panel-large">
          <header className="panel-header">
            <div>
              <span className="panel-kicker">Territoires</span>
              <h2>Zones et sous-zones versionnées</h2>
            </div>
            <span className="badge badge-neutral">France / IGN / Mapbox</span>
          </header>

          <div className="zone-list">
            {demoZones.map((zone) => (
              <div key={zone.id} className="zone-item">
                <div>
                  <h3>{zone.name}</h3>
                  <p>
                    {zone.kind} • {zone.revision.areaHectares} ha • saison{' '}
                    {zone.seasonYear}
                  </p>
                </div>
                <div className="zone-meta">
                  <span className={`badge badge-${zone.syncStatus}`}>
                    {zone.syncStatus}
                  </span>
                  <small>
                    {zone.parentZoneId ? 'Sous-zone' : 'Zone principale'}
                  </small>
                </div>
              </div>
            ))}
          </div>

          <div className="population-strip">
            {populationSnapshots.map((snapshot) => (
              <div key={snapshot.id} className="population-chip">
                <strong>{snapshot.estimatedCount}</strong>
                <span>{snapshot.speciesId}</span>
                <small>{snapshot.confidence}</small>
              </div>
            ))}
          </div>
        </article>

        <article className="panel">
          <header className="panel-header">
            <div>
              <span className="panel-kicker">Synchronisation</span>
              <h2>File d’attente</h2>
            </div>
            <span className="badge badge-warning">
              {pendingSyncChanges.length} changements
            </span>
          </header>

          <div className="queue-list">
            {pendingSyncChanges.map((change) => (
              <div key={change.id} className="queue-row">
                <div>
                  <strong>{change.entityName}</strong>
                  <p>{change.entityId}</p>
                </div>
                <div className="queue-meta">
                  <span>{formatHour(change.changedAt)}</span>
                  <small>{change.deviceId}</small>
                </div>
              </div>
            ))}
          </div>
        </article>

        <article className="panel">
          <header className="panel-header">
            <div>
              <span className="panel-kicker">Cartographie</span>
              <h2>Couches actives</h2>
            </div>
            <span className="badge badge-neutral">{mapLayers.length} couches</span>
          </header>

          <ul className="layer-list">
            {mapLayers.map((layer) => (
              <li key={layer.id}>
                <div>
                  <strong>{layer.name}</strong>
                  <p>
                    {layer.provider} • {layer.sourceType}
                  </p>
                </div>
                <div className="layer-flags">
                  {layer.supportsOffline && <span>offline</span>}
                  {layer.supports3d && <span>3D</span>}
                </div>
              </li>
            ))}
          </ul>
        </article>

        <article className="panel panel-wide">
          <header className="panel-header">
            <div>
              <span className="panel-kicker">IA & individus</span>
              <h2>Revue des sujets prioritaires</h2>
            </div>
            <span className="badge badge-important">
              {importantIndividuals.length} prioritaires
            </span>
          </header>

          <div className="individual-grid">
            {demoIndividuals.map((individual) => (
              <div key={individual.id} className="individual-card">
                <div className="individual-head">
                  <strong>{individual.displayName}</strong>
                  <span
                    className={`badge badge-${individual.currentImportance}`}
                  >
                    {individual.currentImportance}
                  </span>
                </div>
                <p>{individual.yearProfile.notes}</p>
                <small>
                  poids {individual.yearProfile.presenceWeight} •{' '}
                  {individual.speciesId}
                </small>
              </div>
            ))}
          </div>
        </article>
      </section>
    </main>
  );
}
