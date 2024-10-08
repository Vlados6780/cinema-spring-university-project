create table movie(
    movie_id int generated by default as identity primary key,
    movie_name varchar(200) not null,
    year_of_production int check ( year_of_production > 1900 )
);
create table actor(
    actor_id int generated by default as identity primary key,
    actor_name varchar(100) not null,
    age int check ( age > 0 )
);
create table movie_actor(
    movie_id int references movie(movie_id),
    actor_id int references actor(actor_id),
    primary key (movie_id, actor_id)
);

