ALTER TABLE collaborators 
	ADD CONSTRAINT FK_collaborators_sectors
	FOREIGN KEY (sector_id)
    REFERENCES sectors (id);