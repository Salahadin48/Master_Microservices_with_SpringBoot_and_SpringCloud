insert into user_table values(10001, LOCALTIMESTAMP - INTERVAL '30' MINUTE, 'AB');
insert into user_table values(10002, LOCALTIMESTAMP - INTERVAL '30' MINUTE, 'Jam');
insert into user_table values(10003, LOCALTIMESTAMP - INTERVAL '30' MINUTE, 'Jill');
insert into post_table values(11001, 'My First Post', 10001);
insert into post_table values(11002, 'My Second Post', 10001);