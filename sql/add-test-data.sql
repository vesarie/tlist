-- User appears to be a reserved word
-- -> changed to Person and person
-- Needs to be updated to the docs
insert into Person (forename, surname, email, password) values ('Alex',  'Adams', 'alex.adams@example.com', '');
insert into Person (forename, surname, email, password) values ('Bill',  'Baker', 'bill.baker@example.com', '');
insert into Person (forename, surname, email, password) values ('Chloe', 'Clark', 'chloe.clark@example.com', '');
insert into Person (forename, surname, email, password) values ('Diana', 'Davis', 'diana.davis@example.com', '');

insert into Project (person, name, color) values (1, 'tsoha',     x'00ffff'::int);
insert into Project (person, name, color) values (1, 'Project B', x'0000ff'::int);
insert into Project (person, name, color) values (1, 'Project C', x'ffa500'::int);
insert into Project (person, name, color) values (2, 'Project D', x'ffff00'::int);
insert into Project (person, name, color) values (2, 'Project E', x'00ff00'::int);
insert into Project (person, name, color) values (3, 'Project F', x'aa00ff'::int);
insert into Project (person, name, color) values (3, 'Project G', x'ff0000'::int);

insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-03', true,  'Write create table commands');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-03', true,  'Create some test data (add-test-data.sql)');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-03', true,  'Boot-up the database');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-04', true,  'Design a layout for the "Today" and "Next 7 days" pages');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-04', false, 'Design a layout for the "Project", "Label" and "Priority" pages');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-05', false, 'Implement one view with JSP');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-05', false, 'Test the first view');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-06', false, 'Implement another view with JSP');
insert into Task (project, priority, schedule, completed, name) values (1, 4, '2016-04-06', false, 'Test the second view');
insert into Task (project, priority, schedule, completed, name) values (2, 1, '2016-04-04', false, 'Task b.1');
insert into Task (project, priority, schedule, completed, name) values (2, 4, '2016-04-04', false, 'Task b.2');
insert into Task (project, priority, schedule, completed, name) values (2, 4, '2016-04-05', false, 'Task b.3');
insert into Task (project, priority, schedule, completed, name) values (3, 2, '2016-04-05', false, 'Task c.1');
insert into Task (project, priority, schedule, completed, name) values (3, 4, '2016-04-06', false, 'Task c.2');
insert into Task (project, priority, schedule, completed, name) values (3, 4, '2016-04-06', false, 'Task c.3');
insert into Task (project, priority, schedule, completed, name) values (4, 3, '2016-04-07', false, 'Task d.1');
insert into Task (project, priority, schedule, completed, name) values (4, 4, '2016-04-07', false, 'Task d.2');
insert into Task (project, priority, schedule, completed, name) values (4, 4, '2016-04-08', false, 'Task d.3');
insert into Task (project, priority, schedule, completed, name) values (5, 4, '2016-04-04', false, 'Task e.1');
insert into Task (project, priority, schedule, completed, name) values (5, 4, '2016-04-04', false, 'Task e.2');
insert into Task (project, priority, schedule, completed, name) values (5, 4, '2016-04-05', false, 'Task e.3');
insert into Task (project, priority, schedule, completed, name) values (6, 4, '2016-04-05', false, 'Task f.1');
insert into Task (project, priority, schedule, completed, name) values (6, 4, '2016-04-06', false, 'Task f.2');
insert into Task (project, priority, schedule, completed, name) values (6, 4, '2016-04-06', false, 'Task f.3');
insert into Task (project, priority, schedule, completed, name) values (7, 4, '2016-04-07', false, 'Task g.1');
insert into Task (project, priority, schedule, completed, name) values (7, 4, '2016-04-07', false, 'Task g.2');
insert into Task (project, priority, schedule, completed, name) values (7, 4, '2016-04-08', false, 'Task g.3');

insert into Label (person, name, color) values (1, 'work',   x'ff4000'::int);
insert into Label (person, name, color) values (1, 'school', x'ffff00'::int);
insert into Label (person, name, color) values (1, 'home',   x'40ff00'::int);

insert into TaskLabel (task, label) values (3, 2);
