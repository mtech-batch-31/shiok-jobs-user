CREATE TABLE IF NOT EXISTS sjmsuser.user_profile
(
    user_id serial PRIMARY KEY,
    email character varying(255) NOT NULL,
    hashed_password character varying(255) NOT NULL,
    role character varying(255)
);