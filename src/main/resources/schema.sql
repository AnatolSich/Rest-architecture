CREATE TABLE event (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  place VARCHAR(100) NOT NULL,
  speaker VARCHAR(100) NOT NULL,
  dateTime VARCHAR(100) NOT NULL,
  eventType VARCHAR(16) NOT NULL,
);
