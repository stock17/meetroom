

CREATE TABLE public.app_user
(
    id bigint NOT NULL,
    account_non_expired boolean NOT NULL,
    account_non_locked boolean NOT NULL,
    authorities character varying(255) COLLATE pg_catalog."default",
    credential_non_expired boolean NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    full_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT app_user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.app_user
    OWNER to postgres;


CREATE TABLE public.meeting
(
    id bigint NOT NULL,
    end_time timestamp without time zone,
    participants character varying(255) COLLATE pg_catalog."default",
    start_time timestamp without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT meeting_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.meeting
    OWNER to postgres;

INSERT INTO public.app_user(
	id, account_non_expired, account_non_locked, authorities, credential_non_expired, enabled, full_name, password, username)
	VALUES (1, true, true, 'ADMIN USER', true, true, 'admin', 'admin', 'admin');
	
INSERT INTO public.app_user(
	id, account_non_expired, account_non_locked, authorities, credential_non_expired, enabled, full_name, password, username)
	VALUES (2, true, true, 'USER', true, true, 'Alexei Stratonov', '123', 'stratonov');
	
INSERT INTO public.app_user(
	id, account_non_expired, account_non_locked, authorities, credential_non_expired, enabled, full_name, password, username)
	VALUES (3, true, true, 'USER', true, true, 'Alexei Filipov', '123', 'filipov');

 