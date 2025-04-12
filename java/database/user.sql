-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER vantarides_owner
WITH PASSWORD 'vantarides';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO vantarides_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO vantarides_owner;

CREATE USER vantarides_appuser
WITH PASSWORD 'vantarides';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO vantarides_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO vantarides_appuser;
