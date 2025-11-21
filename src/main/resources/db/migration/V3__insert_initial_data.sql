INSERT INTO cricket_team (team_name, country) VALUES ('India', 'India');

INSERT INTO player (name, jersey_no, role, captain, debut_date, team_id)
VALUES ('Arjun Tendulkar', 11, 'Batsman', false, '2026-08-18', 1);

INSERT INTO matches (opponent, venue, match_date)
VALUES ('Australia', 'MCG', '2024-01-10');

INSERT INTO player_match(player_id, match_id) VALUES (1, 1);

INSERT INTO player_salary(month, amount, player_id)
VALUES ('January', 1500000, 1);
