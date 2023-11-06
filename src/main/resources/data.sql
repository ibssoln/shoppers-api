INSERT INTO CATEGORY VALUES ('1aaa4389-d799-4f3a-bb20-135c3eb07da1', 'Grocery', 'grocery.jpg');
INSERT INTO CATEGORY VALUES ('1aaa4389-d799-4f3a-bb20-135c3eb07da2', 'Toy', 'toy.jpg');
INSERT INTO CATEGORY VALUES ('1aaa4389-d799-4f3a-bb20-135c3eb07da3', 'Pet', 'pet.jpg');
INSERT INTO CATEGORY VALUES ('1aaa4389-d799-4f3a-bb20-135c3eb07da4', 'Beauty', 'beauty.jpg');
INSERT INTO CATEGORY VALUES ('1aaa4389-d799-4f3a-bb20-135c3eb07da5', 'House', 'house.jpg');

INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df1', 'Walmart', 'walmart.jpg', '2023-11-05T01:20:13.479623900Z', '2023-11-04 21:00:00-02:00', '9876 Maple St. Washington, Washington, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df2', 'Dollarama', 'dollarama.jpg', '2023-11-05T01:20:13.479623900Z', '2023-11-04 21:00:00-02:00', '5555 Chest St. L.A, California, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df3', 'No Frills', 'nofrills.jpg', CURRENT_TIMESTAMP, '2023-11-04 21:00:00-02:00', '1111 Bear St. Austin, Texas, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df4', 'LCBO', 'lcbo.jpg', CURRENT_TIMESTAMP, '2023-11-04 21:00:00-02:00', '2222 Yellow St. Toronto, Ontario, CAN');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df5', 'Giant Tiger', 'gianttiger.jpg', CURRENT_DATE, '2023-11-04 21:00:00-02:00', '3333 Hamstring St. Texas City, Texas, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Bulk Barn', 'bulkbarn.jpg', CURRENT_DATE, '2023-11-04 21:00:00-02:00', '4444 Avery St. Austin, Texas, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df7', 'Toys R Us', 'toysrus.jpg', '2023-11-04 21:00:00-02:00', '2023-11-04 21:00:00-02:00', '6666 Fleet St. City, New York, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df8', 'Body Shop', 'bodyshop.jpg', '2023-11-04 21:00:00-02:00', '2023-11-04 21:00:00-02:00', '7777 John St. City, New York, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df9', 'Staples', 'staples.jpg', '2023-11-04 21:00:00-02:00', '2023-11-04 21:00:00-02:00', '8888 Walnut St. L.A, California, US');
INSERT INTO STORE VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07dg1', 'Nature''s Emporium', 'naturesemporium.jpg', '2023-11-04 21:00:00-02:00', '2023-11-04 21:00:00-02:00', '9999 Apple St. L.A, California, US');

INSERT INTO VENDOR VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'DecoFactory', '1234 Main St. Toronto, Ontario, CA', 'CAN');

INSERT INTO EVENT VALUES ('1aaa4389-1234-4f3a-bb20-135c3eb07da1', 'New Year''s', '2023-01-01T00:00:00.479623900Z', '2023-01-10T00:00:00.479623900Z');
INSERT INTO EVENT VALUES ('1aaa4389-1234-4f3a-bb20-135c3eb07da2', 'Thanksgiving', '2023-09-20T00:00:00.479623900Z', '2023-10-10T00:00:00.479623900Z');
INSERT INTO EVENT VALUES ('1aaa4389-1234-4f3a-bb20-135c3eb07da3', 'Halloween', '2023-10-20T00:00:00.479623900Z', '2023-10-31T00:00:00.479623900Z');
INSERT INTO EVENT VALUES ('1aaa4389-1234-4f3a-bb20-135c3eb07da4', 'Christmas', '2023-12-15T00:00:00.479623900Z', '2023-12-31T00:00:00.479623900Z');

INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07f', 'Wood Table', 119.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'woodtable.jpg', '11kg', '1aaa4389-1234-4f3a-bb20-135c3eb07da1');
INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07a', 'Towel Set', 99.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'towelset.jpg', '1.2kg', '1aaa4389-1234-4f3a-bb20-135c3eb07da2');
INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07b', 'Deco Box', 98.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'decobox.jpg', '2kg', '1aaa4389-1234-4f3a-bb20-135c3eb07da3');
INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07c', 'Metal Table', 79.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'metaltable.jpg', '14kg', '1aaa4389-1234-4f3a-bb20-135c3eb07da4');
INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07d', 'Mirror', 189.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'mirror.jpg', '22kg', null);
INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07e', 'Drawer', 1089.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'drawer.jpg', '30kg', null);
INSERT INTO ITEM VALUES ('7579eae4-9877-48e8-9983-f50eaad2a07g', 'Wood Chair', 669.95, '6aaa4389-d799-4f3a-bb20-135c3eb07df6', 'Y', 'woodchair.jpg', '15kg', '1aaa4389-1234-4f3a-bb20-135c3eb07da1');

INSERT INTO INVENTORY VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df1', '7579eae4-9877-48e8-9983-f50eaad2a07f', 5);
INSERT INTO INVENTORY VALUES ('6aaa4389-d799-4f3a-bb20-135c3eb07df2', '7579eae4-9877-48e8-9983-f50eaad2a07f', 3);

