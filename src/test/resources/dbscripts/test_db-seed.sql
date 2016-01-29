
--
-- Initial data for table `recipient_type`
--

INSERT INTO `recipient_type` (name) VALUES ('To');
INSERT INTO `recipient_type` (name) VALUES ('Cc');
INSERT INTO `recipient_type` (name) VALUES ('Bcc');

--
-- Test data for table `change_request`
--


INSERT INTO `change_request` (`id`, `title`, `summary`, `detail`, `control`, `customer`, `risk`, `state`)
    VALUES (1, 'test title 1', 'test summary 1', 'test detail 1', 'test control 1', 'test customer 1', 'Low', 'Approved');
INSERT INTO `change_request` (`id`, `title`, `summary`, `detail`, `control`, `customer`, `risk`, `state`)
    VALUES (2, 'test title 2', 'test summary 2', 'test detail 2', 'test control 2', 'test customer 2', 'Medium', 'Rejected');
INSERT INTO `change_request` (`id`, `title`, `summary`, `detail`, `control`, `customer`, `risk`, `state`)
    VALUES (3, 'test title 3', 'test summary 3', 'test detail 3', 'test control 3', 'test customer 3', 'Low', 'Submitted');
INSERT INTO `change_request` (`id`, `title`, `summary`, `detail`, `control`, `customer`, `risk`, `state`)
    VALUES (4, 'test title 4', 'test summary 4', 'test detail 4', 'test control 4', 'test customer 3', 'High', 'MoreInfo');
INSERT INTO `change_request` (`id`, `title`, `summary`, `detail`, `control`, `customer`, `risk`, `state`)
    VALUES (5, 'test title 5', 'test summary 5', 'test detail 5', 'test control 5', 'test customer 2', 'Low', 'Approved');