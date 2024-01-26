
CREATE TABLE payments (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    value decimal(19,2) NOT NULL,
    name varchar(100) DEFAULT NULL,
    number varchar(19) DEFAULT NULL,
    expiration varchar(7) NOT NULL,
    cod varchar(3) DEFAULT NULL,
    status varchar(255) NOT NULL,
    id_order bigint(20) NOT NULL,
    id_payment_type bigint(20) NOT NULL,
    PRIMARY KEY (id)
);