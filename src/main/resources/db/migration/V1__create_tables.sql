CREATE TABLE IF NOT EXISTS sjmsuser.user_profile
(
    id serial PRIMARY KEY,
    account_uuid VARCHAR(255) UNIQUE,
    name character varying(255) NOT NULL,
    seeking bool NOT NULL DEFAULT TRUE,
    job_title character varying(255) NOT NULL,
    image character varying(255) NOT NULL,
    about character varying(255) NOT NULL
);