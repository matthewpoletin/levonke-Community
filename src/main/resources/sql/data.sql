INSERT INTO community.users(users_username, users_firstname, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink)
  VALUES ('MatthewPoletin', 'Matthew', 'Poletin', 'poletinm@yandex.ru', 'contact@matthewpoletin.ru', 'github.com/matthewpoletin', 'fb.me/matthewpoletin');
INSERT INTO community.users(users_username, users_firstname, users_surname, users_regemail, users_pubemail, users_ghlink, users_fbLink)
  VALUES ('kormvina', 'Ksenia', 'Strogina', 'ksyshkazaglushka@mail.ru', 'stroginakp@student.bmstu.ru', 'github.com/kormvina', 'fb.me/kseniastrogina');

INSERT INTO community.organizations(organizations_name, organizations_description, organizations_pubemail, organizations_website, organizations_owner_id)
    VALUES ('Levonke', 'Electronics Devices Design Service', 'help@levonke.com', 'levonke.com', 1);

INSERT INTO community.teams(teams_name, teams_organization_id)
    VALUES ('back-end developers', 1);

INSERT INTO community.users_teams(users_users_id, teams_teams_id)
    VALUES ((SELECT (users_id) FROM community.users WHERE users_username = 'MatthewPoletin'),
            (SELECT (teams_id) FROM community.teams WHERE teams_organization_id = 1 AND teams_name = 'back-end developers')
    );