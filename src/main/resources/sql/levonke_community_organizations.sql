CREATE TABLE community.organizations
(
    organizations_id integer DEFAULT nextval('community.organizations_organizations_id_seq'::regclass) PRIMARY KEY NOT NULL,
    organizations_description varchar(255),
    organizations_name varchar(255),
    organizations_pubemail varchar(255),
    organizations_website varchar(255),
    organizations_owner_id integer,
    CONSTRAINT fknvmulvkhbx71x960yarq9mouj FOREIGN KEY (organizations_owner_id) REFERENCES users (users_id)
);