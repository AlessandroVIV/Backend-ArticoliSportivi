
    set client_min_messages = WARNING;

    alter table if exists carrello 
       drop constraint if exists FKn2e1cil2mfpgxyn5gn7umt0lj;

    drop table if exists carrello cascade;

    drop table if exists utenti cascade;
