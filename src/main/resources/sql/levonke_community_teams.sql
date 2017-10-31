CREATE TABLE community.teams
(
    teams_id integer DEFAULT nextval('community.teams_teams_id_seq'::regclass) PRIMARY KEY NOT NULL,
    teams_name varchar(255),
    team_organization_id integer,
    CONSTRAINT fkc5o53wve824mspycosqybenf6 FOREIGN KEY (team_organization_id) REFERENCES organizations (organizations_id)
);