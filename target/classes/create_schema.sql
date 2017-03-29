/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Pedro Arthur
 * Created: 28/03/2017
 */

CREATE TABLE IF NOT EXISTS MOVIE (
    id SERIAL,
    title VARCHAR(50),
    gender INT,
    duration INT,
    rented BOOLEAN,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS RENT (
    id SERIAL,
    rent_date DATE,
    delivery_date DATE,
    movie_id INT,
    FOREIGN KEY(movie_id) REFERENCES MOVIE(id),
    PRIMARY KEY(id)
);
