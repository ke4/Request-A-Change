-- Database for Request a Change Application

DROP TABLE IF EXISTS change_request_story;
DROP TABLE IF EXISTS change_request_recipient;
DROP TABLE IF EXISTS change_request;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS risk;
DROP TABLE IF EXISTS state;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS story;
DROP TABLE IF EXISTS recipient_type;

--
-- Table structure for table `change_request`
--

CREATE TABLE IF NOT EXISTS change_request (
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title       VARCHAR(150) NOT NULL,
    summary     TEXT,
    detail      TEXT,
    control     TEXT,
    customer    VARCHAR(100),
    risk        VARCHAR(10) NOT NULL,
    state       VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    KEY idx_change_request_title (title)   -- To build index (non-unique) on title
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `story`
--

CREATE TABLE IF NOT EXISTS story (
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title       VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `change_request_story`
--

CREATE TABLE IF NOT EXISTS change_request_story (
    change_request_id   INT UNSIGNED NOT NULL,
    story_id            INT UNSIGNED NOT NULL,
    PRIMARY KEY (change_request_id, story_id),
    CONSTRAINT fk_change_request_story_change_request FOREIGN KEY (change_request_id) REFERENCES change_request (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_change_request_story_story FOREIGN KEY (story_id) REFERENCES story (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS user (
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    sanger_id   VARCHAR(20) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    board_member TINYINT unsigned NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS role (
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name        VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS user_role (
    user_id     INT UNSIGNED NOT NULL,
    role_id     INT UNSIGNED NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES user (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `recipient_type`
--

CREATE TABLE IF NOT EXISTS recipient_type (
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `change_request_recipient`
--

CREATE TABLE IF NOT EXISTS change_request_recipient (
    change_request_id   INT UNSIGNED NOT NULL,
    user_id             INT UNSIGNED NOT NULL,
    recipient_type_id   INT UNSIGNED NOT NULL,
    PRIMARY KEY (change_request_id, user_id, recipient_type_id),
    CONSTRAINT fk_change_request_recipient_change_request FOREIGN KEY (change_request_id) REFERENCES change_request (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_change_request_recipient_user FOREIGN KEY (user_id) REFERENCES user (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_change_request_recipient_recipient_type FOREIGN KEY (recipient_type_id) REFERENCES recipient_type (id) 
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
