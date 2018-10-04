-- test values for 

/*
insert into parts (pid,pname,color) values (1,'capo','red');

insert into parts (pname,color) values ('seats','green');

insert into suppliers (sid,sname,address) values (1,'gasoline','alberdi 180');

insert into suppliers (sname,address) values ('Audi','25 de mayo 1250');

*/
select * from suppliers;

select * from parts;

select * from catalog;

 select * from suppliers as s inner join catalog as c inner join parts as p 
 on s.sid=c.sid and c.pid=p.pid ;
 
 -- Query for every suppliers that only supplies green parts, and count of parts that supplies.
 select s.sname, count(*) as cant 
 from (suppliers s inner join catalog c on c.sid=s.sid) 
 inner join parts p on c.pid=p.pid
 where (p.color='green' and c.sid not in 
 (select c2.sid from catalog c2 inner join parts p2 on c2.pid=p2.pid 
 where p2.color not like 'green'))
 group by s.sname;

-- Query for every suppliers that supplies a green and a red part and most expensive part
 select s.sname, max(c.cost) as cost
 from (catalog c inner join parts p on c.pid=p.pid) 
 inner join suppliers s on c.sid=s.sid
 where (p.color='green' and c.sid in 
 (select c2.sid from parts p2 inner join catalog c2 on c2.pid=p2.pid where p2.color='red'))
 group by c.sid;
