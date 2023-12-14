CREATE TABLE "USER" (
                        "id"	INTEGER NOT NULL UNIQUE,
                        "registrationDate"	DATE NOT NULL,
                        "username"	NUMERIC NOT NULL,
                        PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "VIDEO" (
                         "id"	INTEGER NOT NULL UNIQUE,
                         "uploader"	INT NOT NULL,
                         "name"	VARCHAR(250) NOT NULL,
                         "duration"	INT UNSIGNED NOT NULL,
                         "uploadDate"	DATE NOT NULL,
                         PRIMARY KEY("id" AUTOINCREMENT),
                         FOREIGN KEY("uploader") REFERENCES "USER"("id")
)