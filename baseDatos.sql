BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "actividades" (
	"id"	INTEGER,
	"salida_id"	INTEGER NOT NULL,
	"cliente_id"	INTEGER NOT NULL,
	"botellas_id"	INTEGER NOT NULL,
	FOREIGN KEY("salida_id") REFERENCES "salidas_buceo"("id"),
	FOREIGN KEY("botellas_id") REFERENCES "botellas"("id"),
	FOREIGN KEY("cliente_id") REFERENCES "clientes"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "botellas" (
	"id"	INTEGER,
	"capacidad"	INTEGER NOT NULL,
	"conexion"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "instructores" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	"apellidos"	TEXT NOT NULL,
	"fecha_nacimiento"	TEXT,
	"direccion"	TEXT NOT NULL,
	"telefono"	TEXT,
	"email"	TEXT,
	"certificaciones"	TEXT NOT NULL,
	"idiomas"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "salidas_buceo" (
	"id"	INTEGER,
	"fecha"	TEXT NOT NULL,
	"hora"	TIME NOT NULL,
	"lugar"	TEXT NOT NULL,
	"punto_salida"	TEXT NOT NULL,
	"instructor_id"	INTEGER NOT NULL,
	"tipo_salida"	TEXT NOT NULL,
	FOREIGN KEY("instructor_id") REFERENCES "instructores"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "clientes" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"apellidos"	TEXT,
	"fecha_nacimiento"	TEXT,
	"direccion"	TEXT NOT NULL,
	"telefono"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"numero_buceos"	INTEGER NOT NULL,
	"certificaciones"	TEXT NOT NULL,
	"nivel"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "actividades" VALUES (15,16,8,1);
INSERT INTO "actividades" VALUES (16,16,12,1);
INSERT INTO "actividades" VALUES (17,16,17,1);
INSERT INTO "actividades" VALUES (18,0,13,1);
INSERT INTO "actividades" VALUES (19,17,15,6);
INSERT INTO "actividades" VALUES (20,17,16,4);
INSERT INTO "actividades" VALUES (21,18,14,4);
INSERT INTO "actividades" VALUES (22,18,17,4);
INSERT INTO "actividades" VALUES (23,19,19,4);
INSERT INTO "actividades" VALUES (24,19,9,6);
INSERT INTO "actividades" VALUES (25,19,12,6);
INSERT INTO "actividades" VALUES (26,20,7,4);
INSERT INTO "botellas" VALUES (1,10,'DIN');
INSERT INTO "botellas" VALUES (2,10,'INT');
INSERT INTO "botellas" VALUES (3,12,'DIN');
INSERT INTO "botellas" VALUES (4,12,'INT');
INSERT INTO "botellas" VALUES (5,15,'DIN');
INSERT INTO "botellas" VALUES (6,15,'INT');
INSERT INTO "instructores" VALUES (5,'Israel','Medrano','452124000000','Huesca','111111111','email@email','OWDI Avanzado','Español Ingles Francés ');
INSERT INTO "instructores" VALUES (6,'Marc','Marquez','608335200000','Calle Tossa','345345454','marc@marc.com','DMI','Español Ingles Francés ');
INSERT INTO "instructores" VALUES (7,'fd','fd','1717253619600','fd','223232','fdsa','Dive Master','Español ');
INSERT INTO "instructores" VALUES (8,'oriol','bansanta','1725637045901','pepito','+34','fdfdf@pepito.es','OWDI Avanzado','Español Ingles Francés ');
INSERT INTO "salidas_buceo" VALUES (16,'30/06/2024','9:00','punto2','Playa',5,'Guiada');
INSERT INTO "salidas_buceo" VALUES (17,'30/06/2024','10:00','punto3','Playa',6,'Open');
INSERT INTO "salidas_buceo" VALUES (18,'30/06/2024','12:30','punto3','Barca',5,'Formación');
INSERT INTO "salidas_buceo" VALUES (19,'27/06/2024','9:00','punto3','Playa',5,'Guiada');
INSERT INTO "salidas_buceo" VALUES (20,'01/09/2024','9:00','Cañón azul','Playa',8,'Guiada');
INSERT INTO "salidas_buceo" VALUES (21,'01/09/2024','9:00','Roca blanca','Playa',8,'Guiada');
INSERT INTO "salidas_buceo" VALUES (22,'01/09/2024','10:15','Cañón azul','Barca',8,'Open');
INSERT INTO "salidas_buceo" VALUES (23,'02/09/2024','8:15','Roca blanca','Playa',6,'Open');
INSERT INTO "salidas_buceo" VALUES (24,'30/09/2024','8:15','Cañón azul','Barca',5,'Guiada');
INSERT INTO "salidas_buceo" VALUES (25,'30/09/2024','12:15','Pecio rojo','Barca',6,'Formación');
INSERT INTO "clientes" VALUES (7,'Irene','Perez','614383200000','Huesca','222222222','irene@iernees','Advanced','40','Autonoma con guía');
INSERT INTO "clientes" VALUES (8,'irene','irene','614383200000','irene','222222222','irene@irene.es',60,'Advanced','autónoma con guía');
INSERT INTO "clientes" VALUES (9,'Israel','Perez','30/04/1984','Calle chocolate','111222333','israel@israel.es',40,'OWD','autónomo');
INSERT INTO "clientes" VALUES (10,'Laura','González','15/07/1990','Avenida Marina','444555666','laura@example.com',25,'AOW','recreativo');
INSERT INTO "clientes" VALUES (11,'Juan','Martínez','02/03/1983','Calle Sol','777888999','juan@example.com',50,'Divemaster','profesional');
INSERT INTO "clientes" VALUES (12,'María','López','12/11/1985','Paseo del Mar','111000222','maria@example.com',35,'OWD','recreativo');
INSERT INTO "clientes" VALUES (13,'Carlos','Sánchez','25/09/1992','Calle Luna','333444555','carlos@example.com',60,'AOW','profesional');
INSERT INTO "clientes" VALUES (14,'Ana','Rodríguez','18/05/1988','Avenida del Bosque','666777888','ana@example.com',30,'OWD','recreativo');
INSERT INTO "clientes" VALUES (15,'Pedro','Díaz','03/12/1980','Calle Mayor','999000111','pedro@example.com',45,'Divemaster','profesional');
INSERT INTO "clientes" VALUES (16,'Elena','Gómez','20/06/1983','Avenida Primavera','222333444','elena@example.com',20,'OWD','recreativo');
INSERT INTO "clientes" VALUES (17,'Marta','Fernández','10/10/1995','Calle Jardín','555666777','marta@example.com',55,'AOW','profesional');
INSERT INTO "clientes" VALUES (18,'Luis','Ramírez','05/04/1986','Paseo de la Playa','888999000','luis@example.com',30,'OWD','recreativo');
INSERT INTO "clientes" VALUES (19,'Iñeñe','Peñe','614383200000','Calle Desengueñe','777777777','iñeñepeñe@peñe.es',40,'Advanced','Autónoma con instructor');
INSERT INTO "clientes" VALUES (20,'fdsa','fdsa','1718960303265','fdsa','fdsa','fdsa',34,'Seleccione','');
COMMIT;
