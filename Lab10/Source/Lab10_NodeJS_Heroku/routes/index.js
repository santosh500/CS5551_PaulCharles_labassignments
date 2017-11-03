//This Code and Project Template is referenced and derived from: https://github.com/mschwarzmueller/nodejs-basics-tutorial
//This Code and Project Template is referenced and derived from: CS5551 Lab 8 Source Code
//This class handles the code and logic
var express = require('express');
var router = express.Router();
var request = require('request');
var request2 = require('request');
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Welcome to Food App' });
});

router.get('/test/:id', function(req, res, next) {
        var r = req.params.id;
        var split = r.split(",");
        res.render('test', {output: r[0],output2:r[1]});

});
var q;
router.post('/test/submit', function(req, res, next) {

    var id = req.body.id;
    var mainString1 = 'https://kgsearch.googleapis.com/v1/entities:search?query=';
    var mainString2 = '&key=AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg&limit=1&indent=True';
    var totalString = mainString1 + id  + mainString2;
    var tempString = 'https://kgsearch.googleapis.com/v1/entities:search?query=pizza&key=AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg&limit=1&indent=True';
    request(totalString, function (error, response, body) {

        body = JSON.parse(body);
        var ven = body;
        var k = ven['itemListElement'][0]['result']['description'];
        console.log(k.toString());
        q=k;
    });
    var mainString3 = 'https://api.edamam.com/api/food-database/parser?ingr=pizza&app_id=206a6911&app_key=d22367be1c1073a350195c85d95aaf4b&page=0';
    var mainString4 = 'https://api.edamam.com/api/food-database/parser?ingr=';
    var mainString5 = '&app_id=206a6911&app_key=d22367be1c1073a350195c85d95aaf4b&page=0';
    var mainString6 = mainString4 + id + mainString5;
    request2(mainString6, function (error, response, body) {

       var t = JSON.parse(body);
        var k = t.parsed[0]['food']['label'];
        console.log("hi" + q+k);
        var u = k+" Google: " + q;

        res.redirect('/test/' + u);
    });
});

module.exports = router;
