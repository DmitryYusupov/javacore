DELETE FROM ORDER_TAB;
DELETE FROM MODEL;
DELETE FROM MARK;
DELETE FROM USER;

INSERT INTO MARK (NAME, COUNTRY) VALUES ('Toyota', 'Japan');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Ural', 'Russia');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('BMW', 'Germany');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Mazda', 'Japan');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Mercedes-Benz', 'Germany');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Kamaz', 'Russia');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Ford', 'USA');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Volvo', 'Sweden');
INSERT INTO MARK (NAME, COUNTRY) VALUES ('Honda', 'Japan');

--Toyota----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Toyota'),
                    'Land cruiser 200',
                    'Big like a gym',
                    1990,
                    NULL,
                    'PASSENGER',
                    4,
                    3,
                    'Pionerr',
                    NULL, NULL, NULL
                   );
--Ural----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Ural'),
                    '53125',
                    'Power yeaah',
                    1970,
                    NULL,
                    'TRUCK',
                    NULL,
                    NULL,
                    NULL,
                    7000,
                    FALSE,
                    200
                   );
--BMW----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'BMW'),
                    '745Li',
                    'Expensive',
                    1960,
                    NULL,
                    'PASSENGER',
                    4,
                    4,
                    'BestAudio',
                    NULL,
                    NULL,
                    NULL
                   );
--Mazda----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Mazda'),
                    'Mazda 6',
                    'Not bad',
                    1990,
                    NULL,
                    'PASSENGER',
                    4,
                    4,
                    'Panasonic',
                    NULL,
                    NULL,
                    NULL
                   );
--Mercedes-Benz----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Mercedes-Benz'),
                    'G-500 Amg',
                    'Fast and brutal',
                    1960,
                    NULL,
                    'PASSENGER',
                    6,
                    4,
                    'Sony',
                    NULL,
                    NULL,
                    NULL
                   );
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Mercedes-Benz'),
                    'SLR McLaren',
                    'Great Sound',
                    2002,
                    2008,
                    'PASSENGER',
                    4,
                    2,
                    'Prestige',
                    NULL,
                    NULL,
                    NULL
                   );
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Mercedes-Benz'),
                    'Actros',
                    'Universal and powerfull',
                    2002,
                    NULL,
                    'TRUCK',
                    NULL,
                    NULL,
                    NULL,
                    7000,
                    FALSE,
                    200
                   );
--Kamaz----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Kamaz'),
                    '53125',
                    'Power yeaah',
                    1960,
                    NULL,
                    'TRUCK',
                    NULL,
                    NULL,
                    NULL,
                    8000,
                    FALSE,
                    300
                   );
--Ford----
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Ford'),
                    'Focus',
                    'Casual, economic',
                    2002,
                    NULL,
                    'PASSENGER',
                    2,
                    4,
                    'Sony',
                    NULL,
                    NULL,
                    NULL
                   );
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Ford'),
                    'Scorpio',
                    '90-th dream',
                    1992,
                    1998,
                    'PASSENGER',
                    2,
                    4,
                    'Sony',
                    NULL,
                    NULL,
                    NULL
                   );
--Volvo
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Volvo'),
                    'FH-12',
                    'Comfortable',
                    1960,
                    NULL,
                    'TRUCK',
                    NULL,
                    NULL,
                    NULL,
                    8000,
                    TRUE,
                    400
                   );
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Volvo'),
                    'XC 90',
                    'Good car',
                    2000,
                    NULL,
                    'PASSENGER',
                    2,
                    4,
                    'Sony',
                    NULL,
                    NULL,
                    NULL
                   );
--Honda
INSERT INTO MODEL (
                    MARK_ID,
                    NAME,
                    DESCRIPTION,
                    PRODUCTION_YEAR_START,
                    PRODUCTION_YEAR_END,
                    DISCRIMINATOR,
                    NUMBER_OF_AIR_BAGS,
                    NUMBER_OF_SEATS,
                    AUDIO_SYSTEM_NAME,
                    WEIGHT,
                    EMBEDDED_KITCHEN,
                    TANK_SIZE
                    )
                    VALUES
                   (
                    (SELECT ID FROM MARK WHERE NAME = 'Honda'),
                    'Accord',
                    'Smart',
                    2000,
                    NULL,
                    'PASSENGER',
                    2,
                    4,
                    'Sony',
                    NULL,
                    NULL,
                    NULL
                   );

