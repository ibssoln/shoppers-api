CREATE TABLE VENDOR (
    vendor_id varchar(36) NOT NULL,
    name varchar(255),
    address varchar(255),
    PRIMARY KEY (vendor_id)
);

CREATE TABLE ITEM (
    item_id varchar(36) NOT NULL,
    name varchar(255),
    price number(10, 2),
    vendor_id varchar(36),
    PRIMARY KEY (item_id),
    CONSTRAINT FK_ItemVendor FOREIGN KEY (vendor_id) REFERENCES VENDOR(vendor_id)
);
