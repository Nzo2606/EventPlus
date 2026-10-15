alter table event
add column status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
add column cancellation_reason VARCHAR(255) NULL;