CREATE TABLE events(
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	description TEXT,
	venue VARCHAR(200) NOT NULL,
	event_date TIMESTAMP NOT NULL,
	total_seats BIGINT NOT NULL,
	creationtime TIMESTAMP(6) without time zone,
    updatedtime TIMESTAMP(6) without time zone
);