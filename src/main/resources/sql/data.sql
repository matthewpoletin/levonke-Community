INSERT INTO community.users(users_username, users_firstname, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink)
    VALUES ('MatthewPoletin', 'Matthew', 'Poletin', 'poletinm@yandex.ru', 'contact@matthewpoletin.ru', 'github.com/matthewpoletin', 'fb.me/matthewpoletin');
INSERT INTO community.users(users_username, users_firstname, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink)
    VALUES ('kormvina', 'Ksenia', 'Strogina', 'ksyshkazaglushka@mail.ru', 'stroginakp@student.bmstu.ru', 'github.com/kormvina', 'fb.me/kseniastrogina');
INSERT INTO community.users(users_username, users_firstname, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink)
	VALUES ('molros', 'Ogneslav', 'Pestritski', 'persicor@gmail.com', 'persicor@gmail.com', 'github.com/persicor', 'fb.me/persicor');
INSERT INTO community.users(users_username, users_firstname, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink)
	VALUES ('sworen', 'Isaac', 'Grey', 'persicor@gmail.com', 'persicor@gmail.com', 'github.com/isaac_grey', 'fb.me/isaacg');

INSERT INTO community.organizations(organizations_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('Haywire Corp.', 'Wearable technologies', 'ask@haywire.ru', 'haywire.ru', (SELECT users_name FROM community.users WHERE users_username = 'MatthewPoletin'));
INSERT INTO community.organizations(organizations_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('Dreamedia Inc.', NULL, NULL, 'dreamedia.com', (SELECT users_name FROM community.users WHERE users_username = 'MatthewPoletin'));
INSERT INTO community.organizations(organizations_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('Nerra AB.', NULL, NULL, 'ecorp.com', (SELECT users_name FROM community.users WHERE users_username = 'sworen'));

INSERT INTO community.teams(teams_name, teams_organization_id)
    VALUES ('Wearables', (SELECT organizations_id FROM community.organizations WHERE organizations_name = 'Haywire Corp.'));
INSERT INTO community.teams(teams_name, teams_organization_id)
	VALUES ('Home consoles', (SELECT organizations_id FROM community.organizations WHERE organizations_name = 'Dreamedia Inc.'));

INSERT INTO community.users_teams(users_users_id, teams_teams_id)
    VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'MatthewPoletin'),
            (SELECT (teams_id) FROM community.teams WHERE teams_name = 'Wearables')
    );
INSERT INTO community.users_teams(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'kormvina'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Wearables')
	);
INSERT INTO community.users_teams(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'MatthewPoletin'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Home consoles')
	);
INSERT INTO community.users_teams(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'molros'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Home consoles')
);
INSERT INTO community.users_teams(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'sworen'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Home consoles')
	);
