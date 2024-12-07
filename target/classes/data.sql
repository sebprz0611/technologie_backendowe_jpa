INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES
(1, 'Malbork', 'ul. Wypoczynkowa 93', 'Mieszkanie 13', '82-200'),
(2, 'Tarnobrzeg', 'aleja Orzeszkowej 35', 'Mieszkanie 7', '39-400'),
(3, 'Nowy Sącz', 'ulica Poznańska 05', 'Mieszkanie 22', '33-300'),
(4, 'Kraków', 'aleja Szymanowskiego 606', 'Mieszkanie 4', '30-011'),
(5, 'Olkusz', 'al. Słowackiego 35', 'Brak', '32-300'),
(6, 'Świnoujście', 'al. Nowa 71', 'Mieszkanie 9', '72-600'),
(7, 'Poznań', 'ul. Dąbrowskiej 297', 'Mieszkanie 3', '61-001'),
(8, 'Lębork', 'plac Żwirowa 87', 'Mieszkanie 25', '84-300'),
(9, 'Żagań', 'ul. Staffa 548', 'Brak', '68-100'),
(10, 'Szczecinek', 'al. Rzemieślnicza 787', 'Mieszkanie 6', '78-400'),
(11, 'Gdynia', 'aleja Maczka 22', 'Mieszkanie 11', '81-000'),
(12, 'Łomża', 'pl. Perłowa 07', 'Brak', '18-400'),
(13, 'Gorlice', 'plac Tylna 54', 'Mieszkanie 21', '38-300'),
(14, 'Bielsk Podlaski', 'ul. Brzoskwiniowa 02', 'Mieszkanie 8', '17-100'),
(15, 'Nysa', 'pl. Cmentarna 900', 'Brak', '48-300'),
(16, 'Warszawa', 'ul. Jana Pawła II 20', 'Mieszkanie 1', '00-175'),
(17, 'Wrocław', 'ul. Świdnicka 78', 'Mieszkanie 12', '50-019'),
(18, 'Gdańsk', 'ul. Długa 92', 'Mieszkanie 5', '80-831'),
(19, 'Katowice', 'ul. Słowiańska 33', 'Mieszkanie 9', '40-086'),
(20, 'Lublin', 'ul. Krakowskie Przedmieście 121', 'Mieszkanie 8', '20-400');


INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
(1, 'Michał', 'Nowicki', '+48 607 875 902', 'm.nowicki@tlen.pl', 'D001', 'Urologia', 11),
(2, 'Magdalena', 'Kaczmarek', '+48 791 555 342', 'magda.kaczmarek@outlook.com', 'D002', 'Kardiochirurgia', 6),
(3, 'Jarosław', 'Pawlak', '+48 725 512 410', 'jarek.pawlak@wp.pl', 'D003', 'Pediatria', 3),
(4, 'Beata', 'Wiśniewska', '+48 713 221 456', 'beata.wisniewska@protonmail.com', 'D004', 'Neurologia', 4),
(5, 'Jakub', 'Zawisza', '+48 770 892 635', 'jakub.zawisza@poczta.pl', 'D005', 'Onkologia', 9),
(6, 'Olga', 'Sikora', '+48 799 811 247', 'olga.sikora@icloud.com', 'D006', 'Ortopedia', 17),
(7, 'Tomasz', 'Grzelak', '+48 772 314 549', 'tomasz.grzelak@wp.pl', 'D007', 'Stomatologia', 10),
(8, 'Zofia', 'Bąk', '+48 735 301 483', 'zofia.bak@tlen.pl', 'D008', 'Chirurgia', 19),
(9, 'Ludwik', 'Kwiatkowski', '+48 709 994 321', 'ludwik.kwiatkowski@poczta.pl', 'D009', 'Psychiatra', 15),
(10, 'Dorota', 'Szulc', '+48 741 295 384', 'dorota.szulc@interia.pl', 'D010', 'Dermatologia', 2);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES
(1, 'Mariusz', 'Makowski', '+48 603 741 812', 'mariuszmakowski@tlen.pl', 'P001', '1982-04-15', 1),
(2, 'Ewa', 'Rogalska', '+48 742 366 441', 'ewa.rogalska@wp.pl', 'P002', '1990-07-23', 7),
(3, 'Patryk', 'Jankowski', '+48 603 512 764', 'patryk.jankowski@protonmail.com', 'P003', '1980-02-05', 14),
(4, 'Barbara', 'Kaczmarek', '+48 752 800 091', 'barbara.kaczmarek@tlen.pl', 'P004', '1985-12-30', 20),
(5, 'Marek', 'Borkowski', '+48 767 412 690', 'marek.borkowski@poczta.pl', 'P005', '1992-10-01', 8),
(6, 'Aneta', 'Szewczyk', '+48 732 516 413', 'aneta.szewczyk@poczta.pl', 'P006', '1996-05-21', 13),
(7, 'Wojciech', 'Wysocki', '+48 791 453 968', 'wojciech.wysocki@protonmail.com', 'P007', '1987-08-16', 5),
(8, 'Zuzanna', 'Tomaszewska', '+48 711 614 821', 'zuzanna.tomaszewska@outlook.com', 'P008', '1993-12-18', 12),
(9, 'Jakub', 'Sienkiewicz', '+48 727 539 112', 'jakub.sienkiewicz@icloud.com', 'P009', '1981-03-04', 16),
(10, 'Katarzyna', 'Pietrzak', '+48 752 899 623', 'katarzyna.pietrzak@icloud.com', 'P010', '1995-11-25', 18);

INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES
(1, 'Wizyta Stacjonarna', '2024-12-02 14:00:00', 2, 1),
(2, 'Wizyta Stacjonarna', '2024-12-02 09:30:00', 8, 2),
(3, 'Wizyta Stacjonarna', '2024-11-25 11:20:00', 7, 3),
(4, 'Wizyta Stacjonarna', '2024-11-30 16:45:00', 9, 4),
(5, 'Wizyta Stacjonarna', '2024-06-10 09:11:30', 2, 1),
(6, 'Wizyta Stacjonarna', '2024-12-03 10:30:00', 6, 5),
(7, 'Wizyta Stacjonarna', '2024-12-03 15:00:00', 6, 7),
(8, 'Wizyta Stacjonarna', '2024-12-04 13:00:00', 4, 8),
(9, 'Wizyta Stacjonarna', '2024-12-05 12:00:00', 5, 6),
(10, 'Wizyta Stacjonarna', '2024-12-06 08:30:00', 10, 9);

INSERT INTO medical_treatment (id, description, type, visit_id) VALUES
(1, 'Kontrola kardiologiczna', 'Badanie', 1),
(2, 'Chirurgia urazowa', 'Operacja', 2),
(3, 'Leczenie ran', 'Opieka medyczna', 3),
(4, 'Psychoterapia', 'Konsultacja', 4),
(5, 'Badanie kardiologiczne', 'Badanie', 5),
(6, 'Konsultacja ortopedyczna', 'Konsultacja', 6),
(7, 'Rehabilitacja pooperacyjna', 'Rehabilitacja', 7),
(8, 'Konsultacja neurologiczna', 'Konsultacja', 8),
(9, 'Badanie onkologiczne', 'Badanie', 9),
(10, 'Porada dermatologiczna', 'Konsultacja', 10);

