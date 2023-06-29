CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    minutes INT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    director VARCHAR(40) NOT NULL
);

insert into movies (name, minutes, genre, director) values
    ('Dark Knight', 152, 'ACTION', 'Dark Knight Director'),
    ('Memento', 113, 'THRILLER', 'Memento Director'),
    ('Matrix', 136, 'ACTION', 'Matrix Director'),
    ('Super Loco', 139, 'COMEDY', 'Super Loco Director'),
    ('Superman', 200, 'ACTION', 'Superman Director');