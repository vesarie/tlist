--insert into Person (forename, surname, email, password)
--    values ('Example',  'User',  'user@example.com', 'pwd');
--insert into Project (person, name) values (1, 'Work');
--insert into Project (person, name) values (1, 'School');
--insert into Project (person, name) values (1, 'Personal');

-- Insert a few tasks for the Example User
--
-- This script assumes that the db contains
-- * an user with the email address user@example.com, and
-- * 3 projects named "Work", "School" and "Personal".

with work_tasks as (
    insert into Task (priority, schedule, completed, name) values
        (4, CURRENT_DATE + interval '0 day', false, 'Work task 1'),
        (4, CURRENT_DATE + interval '1 day', false, 'Work task 2'),
        (4, CURRENT_DATE + interval '2 day', false, 'Work task 3')
    returning *
), work_proj as (
    with example_user as (
        select * from Person
        where email = 'user@example.com'
        limit 1
    )
    select * from Project
    where name = 'Work' and person in (select id from example_user)
    limit 1
)
insert into ProjectTask (task, project)
select work_tasks.id, work_proj.id
from work_proj
full join work_tasks on true;

with school_tasks as (
    insert into Task (priority, schedule, completed, name) values
        (4, CURRENT_DATE + interval '0 day', false, 'School task 1'),
        (4, CURRENT_DATE + interval '1 day', false, 'School task 2'),
        (4, CURRENT_DATE + interval '2 day', false, 'School task 3')
    returning *
), school_proj as (
    with example_user as (
        select * from Person
        where email = 'user@example.com'
        limit 1
    )
    select * from Project
    where name = 'School' and person in (select id from example_user)
    limit 1
)
insert into ProjectTask (task, project)
select school_tasks.id, school_proj.id
from school_proj
full join school_tasks on true;

with personal_tasks as (
    insert into Task (priority, schedule, completed, name) values
        (4, CURRENT_DATE + interval '0 day', false, 'Personal task 1'),
        (4, CURRENT_DATE + interval '1 day', false, 'Personal task 2'),
        (4, CURRENT_DATE + interval '2 day', false, 'Personal task 3')
    returning *
), personal_proj as (
    with example_user as (
        select * from Person
        where email = 'user@example.com'
        limit 1
    )
    select * from Project
    where name = 'Personal' and person in (select id from example_user)
    limit 1
)
insert into ProjectTask (task, project)
select personal_tasks.id, personal_proj.id
from personal_proj
full join personal_tasks on true;
