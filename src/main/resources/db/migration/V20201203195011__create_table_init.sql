CREATE TABLE IF NOT EXISTS sectors(
	id BIGINT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255) UNIQUE,
	PRIMARY KEY (id)
) engine=InnoDB DEFAULT CHARACTER SET UTF8;

CREATE TABLE IF NOT EXISTS collaborators(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(150) NOT NULL,
	cpf VARCHAR(14) NOT NULL UNIQUE,
    	date_of_birth datetime(6) NOT NULL,
    	age INT,
    	email VARCHAR(70) UNIQUE NOT NULL,
    	telephone VARCHAR(30) NOT NULL,
    	sector_id BIGINT NOT NULL,
	PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARACTER SET UTF8;
