CREATE TABLE cricket_team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    jersey_no INT,
    role VARCHAR(50),
    captain BOOLEAN,
    debut_date DATE,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES cricket_team(id)
);

CREATE TABLE matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    opponent VARCHAR(100),
    venue VARCHAR(100),
    match_date VARCHAR(50)
);

CREATE TABLE player_match (
    player_id INT,
    match_id INT,
    PRIMARY KEY (player_id, match_id),
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (match_id) REFERENCES matches(id)
);

CREATE TABLE player_salary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    month VARCHAR(20),
    amount DOUBLE,
    player_id INT,
    FOREIGN KEY (player_id) REFERENCES player(id)
);
