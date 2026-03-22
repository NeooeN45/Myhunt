ALTER TABLE zone ENABLE ROW LEVEL SECURITY;
ALTER TABLE zone_revision ENABLE ROW LEVEL SECURITY;
ALTER TABLE individual ENABLE ROW LEVEL SECURITY;
ALTER TABLE individual_year_profile ENABLE ROW LEVEL SECURITY;
ALTER TABLE observation ENABLE ROW LEVEL SECURITY;
ALTER TABLE gps_track ENABLE ROW LEVEL SECURITY;
ALTER TABLE sync_change ENABLE ROW LEVEL SECURITY;
ALTER TABLE audit_event ENABLE ROW LEVEL SECURITY;

CREATE POLICY zone_read_all ON zone FOR SELECT USING (true);
CREATE POLICY zone_revision_read_all ON zone_revision FOR SELECT USING (true);
CREATE POLICY individual_read_all ON individual FOR SELECT USING (true);
CREATE POLICY individual_profile_read_all ON individual_year_profile FOR SELECT USING (true);
CREATE POLICY observation_read_all ON observation FOR SELECT USING (true);
CREATE POLICY gps_track_read_all ON gps_track FOR SELECT USING (true);
CREATE POLICY sync_change_read_all ON sync_change FOR SELECT USING (true);
CREATE POLICY audit_event_read_all ON audit_event FOR SELECT USING (true);
