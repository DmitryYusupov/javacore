CREATE TABLE IF NOT EXISTS  USER (
   ID           BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FIRST_NAME   VARCHAR(50) NOT NULL,
   LAST_NAME    VARCHAR(50) NOT NULL,
   AGE          INT,
   CLIENT_TYPE  VARCHAR(10) NOT NULL DEFAULT 'NEW'
);

CREATE TABLE IF NOT EXISTS MARK (
   ID           BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
   NAME         VARCHAR(50) NOT NULL,
   COUNTRY      VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS MODEL (
   ID                    BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
   MARK_ID               BIGINT      NOT NULL,
   NAME                  VARCHAR(50) NOT NULL,
   DESCRIPTION           VARCHAR(50) NOT NULL,
   PRODUCTION_YEAR_START INT         NOT NULL,
   PRODUCTION_YEAR_END   INT,
   DISCRIMINATOR         VARCHAR(50) NOT NULL,
   NUMBER_OF_AIR_BAGS    INT,
   NUMBER_OF_SEATS       INT,
   AUDIO_SYSTEM_NAME     VARCHAR(50),
   WEIGHT                INT,
   EMBEDDED_KITCHEN      BOOLEAN,
   TANK_SIZE             INT,

   FOREIGN KEY (MARK_ID) references MARK (ID)
);

CREATE TABLE IF NOT EXISTS ORDERS (
  ID                    BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  MARK_ID               BIGINT      NOT NULL,
  MODEL_ID              BIGINT      NOT NULL,
  USER_ID               BIGINT      NOT NULL,
  PRICE                 INT,
  DESCRIPTION           VARCHAR(150),

 FOREIGN KEY (USER_ID) references USER (ID),
 FOREIGN KEY (MARK_ID) references MARK (ID),
 FOREIGN KEY (MODEL_ID) references MODEL (ID)
);


