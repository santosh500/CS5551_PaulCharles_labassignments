//This Code and Project Template is referenced and derived from: https://github.com/mschwarzmueller/nodejs-basics-tutorial
//This Code and Project Template is referenced and derived from: CS5551 Lab 8 Source Code
//This file defines the locations and paths of the application
var logs = require('morgan');
var path = require('path');
var exp = require('express');
var bodyParser = require('body-parser');
var hbs = require('express-handlebars');
var routes = require('./index');

var app = exp();


app.engine('hbs', hbs({extname: 'hbs', defaultLayout: 'layout', layoutsDir: __dirname}));
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(exp.static(path.join(__dirname, 'public')));

app.use('/', routes);


module.exports = app;
