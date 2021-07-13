ALTER TABLE sectors ADD update_at DATETIME null;
UPDATE sectors SET update_at = utc_timestamp;
ALTER TABLE sectors MODIFY update_at DATETIME NOT NULL;