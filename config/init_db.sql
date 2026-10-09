
CREATE TABLE public.resume (
                               "uuid" bpchar(36) NOT NULL,
                               full_name text NOT NULL,
                               CONSTRAINT resume_pkey PRIMARY KEY (uuid)
);
create table contact(
                        id serial primary key,
                        resume_uuid char(36) not null,
                        type text not null,
                        value text not null,
                        CONSTRAINT contact_resume_uuid_fk FOREIGN KEY (resume_uuid) REFERENCES resume (uuid) ON DELETE CASCADE
)

create unique index contact_uuid_type_index on contact(resume_uuid, type);