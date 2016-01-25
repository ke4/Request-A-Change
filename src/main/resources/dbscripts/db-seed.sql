
--
-- Initial data for table `risk`
--

INSERT INTO `risk` (value) VALUES ('Low');
INSERT INTO `risk` (value) VALUES ('Medium');
INSERT INTO `risk` (value) VALUES ('High');

--
-- Initial data for table `state`
--

INSERT INTO `state` (name) VALUES ('Draft');
INSERT INTO `state` (name) VALUES ('Submitted');
INSERT INTO `state` (name) VALUES ('Approved');
INSERT INTO `state` (name) VALUES ('Rejected');
INSERT INTO `state` (name) VALUES ('More Information Needed');

--
-- Initial data for table `recipient_type`
--

INSERT INTO `recipient_type` (name) VALUES ('To');
INSERT INTO `recipient_type` (name) VALUES ('Cc');
INSERT INTO `recipient_type` (name) VALUES ('Bcc');
