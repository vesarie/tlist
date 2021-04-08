-- Add one user with 3 empty projects
with example_user as (
    insert into Person (forename, surname, email, password)
    values ('Example',  'User',  'user@example.com', 'pwd')
    RETURNING *
), proj_names as (
    select * from (values ('Work'), ('School'), ('Personal')) as names (name)
)
insert into Project (person, name)
select example_user.id, proj_names.name
from example_user
full join proj_names on true;
