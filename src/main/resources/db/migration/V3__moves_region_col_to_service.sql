BEGIN;

ALTER TABLE service
  ADD COLUMN region VARCHAR (100);


UPDATE service SET region = (SELECT wt.region as r FROM wait_times wt WHERE service.service_id = wt.service_id LIMIT 1);

ALTER TABLE wait_times
  DROP COLUMN region;

COMMIT;