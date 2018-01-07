INSERT INTO community.users(users_username, users_avatar, users_bio, users_forename, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink, users_password)
    VALUES ('MatthewPoletin', 'https://pp.userapi.com/c626922/v626922567/2429b/IjwJ0ZMRHNo.jpg', 'electronics and software engineer Moscow, Russia 22 y.o.', 'Matthew', 'Poletin', 'poletinm@yandex.ru', 'contact@matthewpoletin.ru', 'github.com/matthewpoletin', 'fb.me/matthewpoletin', '202cb962ac59075b964b07152d234b70');
INSERT INTO community.users(users_username, users_avatar, users_bio, users_forename, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink, users_password)
    VALUES ('kormvina', 'https://vignette.wikia.nocookie.net/firefly/images/4/44/Kaylee_closeup.jpg/revision/latest/scale-to-width-down/200?cb=20100426173253', 'Exceptional engineer with a natural intuition for machines, "Heart of Serenity"', 'Ksenia', 'Strogina', 'ksyshkazaglushka@mail.ru', 'stroginakp@student.bmstu.ru', 'github.com/kormvina', 'fb.me/kseniastrogina', '202cb962ac59075b964b07152d234b70');
INSERT INTO community.users(users_username, users_avatar, users_bio, users_forename, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink, users_password)
	VALUES ('molros', 'https://i.imgur.com/cKasgwf.jpg', 'Main protagonist in the universe and host of the YouTube channel.', 'Ogneslav', 'Pestritski', 'persicor@gmail.com', 'persicor@gmail.com', 'github.com/persicor', 'fb.me/persicor', '202cb962ac59075b964b07152d234b70');
INSERT INTO community.users(users_username, users_avatar, users_bio, users_forename, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink, users_password)
	VALUES ('sworen', 'http://d38we5ntdyxyje.cloudfront.net/73444/profile/avatar_medium_square.jpg', '', 'Isaac', 'Grey', 'persicor@gmail.com', 'persicor@gmail.com', 'github.com/isaac_grey', 'fb.me/isaacg', '202cb962ac59075b964b07152d234b70');

INSERT INTO community.organizations(organizations_name, organizations_official_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('Haywire', 'Haywire Corp.', 'Wearable technologies', 'ask@haywire.ru', 'haywire.ru', (SELECT users_id FROM community.users WHERE users_username = 'MatthewPoletin'));
INSERT INTO community.organizations(organizations_name, organizations_official_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('Dreamedia', 'Dreamedia Inc.', 'Digital illusions', 'contact@dreamedia.com', 'dreamedia.com', (SELECT users_id FROM community.users WHERE users_username = 'MatthewPoletin'));
INSERT INTO community.organizations(organizations_name, organizations_official_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('Nerra', 'Nerra AB.', 'Software enchantments', 'support@nerra.com', 'nerra.com', (SELECT users_id FROM community.users WHERE users_username = 'kormvina'));
INSERT INTO community.organizations(organizations_name, organizations_official_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
	VALUES ('ecorp', 'Ecorp', 'One of the largest multi-national conglomerates in the world. Among their enterprises, they manufacture computers, phones, and tablets, and maintain a banking and consumer credit division.', 'help@ecorp.com', 'ecorp.com', (SELECT users_id FROM community.users WHERE users_username = 'sworen'));

INSERT INTO community.teams(teams_name, teams_organization_id)
    VALUES ('Wearables', (SELECT organizations_id FROM community.organizations WHERE organizations_name = 'Haywire Corp.'));
INSERT INTO community.teams(teams_name, teams_organization_id)
	VALUES ('Home consoles', (SELECT organizations_id FROM community.organizations WHERE organizations_name = 'Dreamedia Inc.'));

INSERT INTO community.teams_users(users_users_id, teams_teams_id)
    VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'MatthewPoletin'),
            (SELECT (teams_id) FROM community.teams WHERE teams_name = 'Wearables')
    );
INSERT INTO community.teams_users(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'kormvina'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Wearables')
	);
INSERT INTO community.teams_users(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'MatthewPoletin'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Home consoles')
	);
INSERT INTO community.teams_users(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'molros'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Home consoles')
);
INSERT INTO community.teams_users(users_users_id, teams_teams_id)
	VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'sworen'),
			(SELECT (teams_id) FROM community.teams WHERE teams_name = 'Home consoles')
	);
