
    set client_min_messages = WARNING;

    alter table if exists articoli 
       drop constraint if exists FK5d2x4xop5vq9mjsbohm65ual6;

    alter table if exists articoli 
       drop constraint if exists FKbqwhwigu6vu9am118tp342y9;

    alter table if exists articoli 
       drop constraint if exists FKkop5x64hfqpj13e6hqe3o0qn3;

    alter table if exists articoli_ordinati 
       drop constraint if exists FKm27m233a3h05unx85vdfbbv9l;

    alter table if exists articoli_ordinati 
       drop constraint if exists FK9k3l5ppolqj9c20nnr232wq5p;

    alter table if exists carrello 
       drop constraint if exists FKn2e1cil2mfpgxyn5gn7umt0lj;

    alter table if exists carrello_item 
       drop constraint if exists FK49hpbsabryedafqp4diqraxlq;

    alter table if exists carrello_item 
       drop constraint if exists FK2uy8v67ws6bc0qctdgrhg011d;

    alter table if exists carrello_item 
       drop constraint if exists FKp9hrx8i337h65th1unxb95ci0;

    alter table if exists ordini 
       drop constraint if exists FK2pcp5659dvokfq4iayu4uxne2;

    drop table if exists articoli cascade;

    drop table if exists articoli_ordinati cascade;

    drop table if exists carrello cascade;

    drop table if exists carrello_item cascade;

    drop table if exists categoria cascade;

    drop table if exists genere cascade;

    drop table if exists marca cascade;

    drop table if exists ordini cascade;

    drop table if exists taglia_indumento cascade;

    drop table if exists utenti cascade;
