
CREATE TABLE task (
  id VARCHAR(36) PRIMARY KEY,
  text VARCHAR NOT NULL,
  status VARCHAR DEFAULT 'inbox',
  createdAt VARCHAR NOT NULL
);