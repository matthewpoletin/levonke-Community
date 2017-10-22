CREATE TABLE community.users_teams
(
    team_users_id integer NOT NULL,
    teams_teams_id integer NOT NULL,
    users_users_id integer NOT NULL,
    CONSTRAINT fkp5vpv4wdrwf7bk3er3yb61l8i FOREIGN KEY (team_users_id) REFERENCES users (users_id),
    CONSTRAINT fkp5ofrrxubukjkgnualmqe9fnk FOREIGN KEY (teams_teams_id) REFERENCES teams (teams_id),
    CONSTRAINT fktn8eeyx2dqtv1h8axp2kg92ga FOREIGN KEY (users_users_id) REFERENCES users (users_id)
);