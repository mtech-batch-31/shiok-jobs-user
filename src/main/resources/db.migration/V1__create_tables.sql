CREATE TABLE IF NOT EXISTS sjmsuser.user_profile
(
    id serial PRIMARY KEY,
    name character varying(255) NOT NULL,
    seeking bool NOT NULL,
    job_title character varying(255) NOT NULL,
    image character varying(255) NOT NULL,
    about character varying(255) NOT NULL
);