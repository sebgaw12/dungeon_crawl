create table if not exists player
(
    id          serial
    primary key,
    player_name text    not null,
    hp          integer not null,
    x           integer not null,
    y           integer not null
);

create table if not exists game_state
(
    id          serial
    primary key,
    current_map text                                not null,
    saved_at    timestamp default CURRENT_TIMESTAMP not null,
    player_id   integer                             not null
    constraint fk_player_id
    references player
);

create table if not exists monster
(
    id            serial,
    health        integer not null,
    defense       integer not null,
    attack        integer not null,
    field_of_view integer not null,
    position_x    integer not null,
    position_y    integer not null,
    player_id     integer not null
    constraint fk_player_id
    references player,
    monster_type  text    not null
);

create table if not exists item
(
    id         serial,
    position_x integer not null,
    position_y integer not null,
    player_id  integer not null
    constraint fk_player_id
    references player,
    item_type  text    not null
);

create table if not exists interactive_object
(
    id            serial,
    position_x    integer not null,
    position_y    integer not null,
    is_interacted boolean not null,
    player_id     integer not null
    constraint fk_player_id
    references player,
    object_type   text    not null
);