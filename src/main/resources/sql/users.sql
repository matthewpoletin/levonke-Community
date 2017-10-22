CREATE TABLE community.users
(
    users_id integer DEFAULT nextval('community.users_users_id_seq'::regclass) PRIMARY KEY NOT NULL,
    users_fblink varchar(255),
    users_firstname varchar(255),
    users_ghlink varchar(255),
    users_pubemail varchar(255),
    users_regemail varchar(255),
    users_surname varchar(255),
    users_username varchar(255)
);
CREATE UNIQUE INDEX uk_o8aun046cuf6ekagariotbu35 ON community.users (users_username);