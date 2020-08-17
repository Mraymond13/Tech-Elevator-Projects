BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tools;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS tool_category;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
  CREATE SEQUENCE seq_reservation_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email_address text,
	user_image text,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role, email_address, user_image) VALUES ('User','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'matthewraymond142@gmail.com', 'https://www.w3schools.com/w3images/avatar2.png');
INSERT INTO users (username,password_hash,role, email_address, user_image) VALUES ('Admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'frhnazd@gmail.com', 'https://www.w3schools.com/howto/img_avatar2.png');
INSERT INTO users (username,password_hash,role, email_address, user_image) VALUES ('Librarian','$2a$10$xDn9pAlR1gFu4BYi9jC1eeP2wy76U/usfFa0lvWpa7ir8gcB7VhIu','ROLE_ADMIN', 'oliver.coffey@gmail.com', 'https://www.w3schools.com/howto/img_avatar.png');


CREATE TABLE tools (
	tool_id serial primary key,
	name varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	image text,
	available boolean,
	date_available date,
	user_id int,
	CONSTRAINT fk_users_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
	);

INSERT INTO tools (name, description, image, available, date_available) VALUES ('Hammer','Fiberglass Handle', 'https://images.homedepot-static.com/productImages/4332845b-7fd0-439f-b43b-45bbe58115c9/svn/anvil-claw-hammers-n-g16cav-64_1000.jpg', 'true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Pliers', 'Needle Nose', 'https://images.homedepot-static.com/productImages/b3d20ab6-115b-478a-85bd-ffe13b63140c/svn/husky-all-trades-needle-nose-pliers-48058-64_1000.jpg', 'true', null);
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Electric Saw','Reciprocating','https://images.homedepot-static.com/productImages/519727cd-7986-4bd7-a530-6f206db7121b/svn/ryobi-reciprocating-saws-rj186v-64_1000.jpg','true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Screwdriver','Cross shaped head to turn screws','https://images.homedepot-static.com/productImages/52ce8579-4c0f-443e-bd7d-4f626bb223d1/svn/hdx-multi-bit-screwdrivers-120sd12d-64_400_compressed.jpg', 'true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Powerwasher','High pressure water sprayer', 'https://images.homedepot-static.com/productImages/20dfdcab-bcce-48c8-b12b-239ce543c7ff/svn/ryobi-electric-pressure-washers-ry141900-64_1000.jpg','true', null);
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Shovel','Used for earth and snow removal', 'https://images.homedepot-static.com/productImages/715f3dbf-4f83-4878-8d12-d49fbb72d3b6/svn/eagle-shovels-1554300-64_1000.jpg' ,'true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Wrench','Monkey', 'https://images.homedepot-static.com/productImages/67d0ac84-7ef0-4597-884d-8418ceb174f2/svn/anvil-adjustable-wrenches-anvilw6adj-64_1000.jpg','true', null);
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Power Drill','Drilling machine for fasteners','https://images.homedepot-static.com/productImages/f4c96695-00ed-4bbd-b175-2a47f8c47845/svn/milwaukee-impact-wrenches-2754-20-64_1000.jpg' ,'true', null);
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Air Compressor','Air powered power', 'https://images.homedepot-static.com/productImages/247fce0a-526f-4b64-bb12-892170d01eec/svn/husky-portable-air-compressors-c202h-64_1000.jpg','true', null);
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Nail Gun','Uses compressed air to drive nails', 'https://images.homedepot-static.com/productImages/39fcaffa-228a-4a36-8121-c07fcf11a105/svn/ridgid-framing-nailers-r350rhf-64_1000.jpg','true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Jackhammer','Pneumatically operated rock-drilling tool','https://images.homedepot-static.com/productImages/a2d840bb-4cb7-4dd4-a058-b88888b80175/svn/hilti-demolition-breaker-hammers-3578531-64_1000.jpg' ,'true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Wheelbarrow','Small hand-propelled vehicle for hauling', 'https://images.homedepot-static.com/productImages/2c112fa6-251f-4c83-ad4f-c89f25af3417/svn/true-temper-wheelbarrows-c6orut14-64_1000.jpg','true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Leaf Blower','Gardening tool that propels air to remove debris', 'https://images.homedepot-static.com/productImages/be77fb4e-a891-41a3-84d4-65ee8ab29d8a/svn/ryobi-gas-leaf-blowers-ry25axb-64_1000.jpg','true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Chainsaw','Mechanical power-driven cutting tool','https://images.homedepot-static.com/productImages/00e11303-224a-4ecd-92ba-33de70981c65/svn/ryobi-gas-chainsaws-ry3716-64_1000.jpg' ,'true', null );
INSERT INTO tools (name, description, image, available, date_available) VALUES ('Lawnmower', 'Gas powered', 'https://images.homedepot-static.com/productImages/17c0e719-7ff5-45d7-8834-fe867a31d4fa/svn/toro-self-propelled-lawn-mowers-20379-64_1000.jpg', 'true', null);



 

CREATE TABLE categories (
	category_id serial primary key,
	name varchar(50) NOT NULL
	);

INSERT INTO categories (name) VALUES ('Power Tool');
INSERT INTO categories (name) VALUES ('Hand Tool');
INSERT INTO categories (name) VALUES ('Garden Tool');

CREATE TABLE tool_category(
	tool_id int,
	category_id int,

	PRIMARY KEY(tool_id, category_id),
	CONSTRAINT fk_tools_tool_id FOREIGN KEY (tool_id) REFERENCES tools(tool_id),
	CONSTRAINT fk_categories_category_id FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE reservation(
	
	reservation_id int DEFAULT nextval('seq_reservation_id'::regclass) NOT NULL,
	tool_id int NOT NULL, 
	user_id int NOT NULL,

	CONSTRAINT PK_reservation PRIMARY KEY (reservation_id),
	CONSTRAINT fk_tools_tool_id FOREIGN KEY (tool_id) REFERENCES tools(tool_id),
	CONSTRAINT fk_users_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO reservation VALUES (DEFAULT, '1', '2'); 


INSERT INTO tool_category VALUES ('1', '2');
INSERT INTO tool_category VALUES ('2', '2');
INSERT INTO tool_category VALUES ('3', '1');
INSERT INTO tool_category VALUES ('4', '2');
INSERT INTO tool_category VALUES ('5', '1');
INSERT INTO tool_category VALUES ('6', '3');
INSERT INTO tool_category VALUES ('7', '2');
INSERT INTO tool_category VALUES ('8', '1');
INSERT INTO tool_category VALUES ('9', '1');
INSERT INTO tool_category VALUES ('10', '1');
INSERT INTO tool_category VALUES ('11', '1');
INSERT INTO tool_category VALUES ('12', '3');
INSERT INTO tool_category VALUES ('13', '3');
INSERT INTO tool_category VALUES ('14', '1');
INSERT INTO tool_category VALUES ('15', '3');





COMMIT TRANSACTION;
