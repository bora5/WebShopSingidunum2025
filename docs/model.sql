CREATE DATABASE IF NOT EXISTS webshop_singidunum CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 
USE webshop_singidunum;
 
CREATE TABLE korisnik (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL
) ENGINE=InnoDB;
 
CREATE TABLE artikal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL,
    cena DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB;
 
CREATE TABLE kupovina (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    korisnik_id BIGINT NOT NULL,
    vreme_kupovine DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    ukupna_cena DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (korisnik_id) REFERENCES korisnik(id)
) ENGINE=InnoDB;
 
CREATE TABLE stavka_kupovine (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    kupovina_id BIGINT NOT NULL,
    artikal_id BIGINT NOT NULL,
    kolicina INT NOT NULL,
    jedicna_cena DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (kupovina_id) REFERENCES kupovina(id),
    FOREIGN KEY (artikal_id) REFERENCES artikal(id)
) ENGINE=InnoDB;
 
-- Unos podataka --
 
INSERT INTO korisnik (ime, prezime) 
VALUES 
('Petar', 'Petrović'), 
('Marko', 'Marković');
 
INSERT INTO artikal (naziv, cena) 
VALUES 
('Laptop', 85000.00), 
('Miš', 1500.00), 
('Tastatura', 3000.00);
 
INSERT INTO kupovina (korisnik_id, vreme_kupovine, status, ukupna_cena) 
VALUES 
(1, NOW(), 'KREIRANA', 86500.00);
 
INSERT INTO stavka_kupovine (kupovina_id, artikal_id, kolicina, jedicna_cena)
VALUES 
(1, 1, 1, 85000.00),
(1, 2, 1, 1500.00);