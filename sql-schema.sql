create table if not exists mqtt.protocol
(
	topic varchar,
	time timestamp,
	temperature double precision
);

alter table mqtt.protocol owner to postgres;