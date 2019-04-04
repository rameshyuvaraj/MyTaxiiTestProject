/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('SUZ-IG-M', '2017', 'Suzuki', 1);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('SUZ-IG-AT', '2017', 'Suzuki', 2);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('SUZ-SWIFT-AT', '2014', 'Suzuki', 3);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('SUZ-SWIFT-AMT', '2014', 'Suzuki', 4);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('MERCEDES-AMG', '2018', 'MERCEDES-BENZ', 5);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('MERCEDES-C-CLASS', '2017', 'MERCEDES-BENZ', 6);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('BMW-M-SERIES', '2018', 'BMW', 7);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('BMW-I-SERIES', '2017', 'BMW', 8);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('BMW-X-SERIES', '2016', 'BMW', 9);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('BMW-J-SERIES', '2015', 'BMW', 10);
insert into manufacture (model, model_year, manufacture_name, pk_manufature_id) values ('BMW-G-SERIES', '2014', 'BMW', 11);


insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (4, false, 'Ignis', false, now(), 'GAS', 'MANUAL', true, 'IG1234', 1, 2, 4, 1);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (2, false, 'Ignis-AMT', false, now(), 'GAS', 'AUTO', false, 'IG5678', 2, 3, 4, 2);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (2, false, 'Swift', false, now(), 'GAS', 'MANUAL', true, 'SW1234', 3, 4, 4, 3);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (4, false, 'Swift-AT', false, now(), 'GAS', 'AUTO', true, 'SW5678', 4, 4, 4, 4);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (4, false, 'Merc-MSUV', false, now(), 'PETROL', 'AUTO', true, 'MERC12', 5, 4, 5, 5);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (4, false, 'Merc-SUV', false, now(), 'PETROL', 'MANUAL', true, 'MERC34', 6, 4, 6, 6);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (6, false, 'BMW-x3', false, now(), 'DIESEL', 'MANUAL', true, 'BMW12', 7, 2, 6, 7);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (6, false, 'BMW-i8', false, now(), 'ELECTRIC', 'AUTO', true, 'BMWI1', 8, 4, 4, 8);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (8, false, 'Merc-C-Class', false, now(), 'DIESEL', 'MANUAL', true, 'MERCC1', 9, 4, 4, 9);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (8, false, 'Merc-S-Class', false, now(), 'HYBRID', 'AUTO', true, 'MERCS1', 10, 4, 4, 10);

insert into car (air_bag_count, assigned_to_driver, car_name, convertible, date_created, engine_type, gear_type, gps_enabled, license_plate, fk_manufature_id, rating, seat_count, id)
values (6, false, 'Merc-A-Class', false, now(), 'ELECTRIC', 'MANUAL', true, 'MERCA1', 11, 4, 4, 11);


