DROP TABLE IF EXISTS INVENTORY;
DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS VENDOR;
DROP TABLE IF EXISTS STORE;

CREATE TABLE STORE (
    store_id varchar(36) NOT NULL,
    name varchar(255),
    address varchar(255),
    PRIMARY KEY (store_id)
);

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

CREATE TABLE INVENTORY (
    store_id varchar(36) NOT NULL,
    item_id varchar(36) NOT NULL,
    stock number(10, 0) DEFAULT 0,
    PRIMARY KEY (store_id, item_id),
    CONSTRAINT FK_InventoryStore FOREIGN KEY (store_id) REFERENCES STORE(store_id),
    CONSTRAINT FK_InventoryItem FOREIGN KEY (item_id) REFERENCES ITEM(item_id)
);