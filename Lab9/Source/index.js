//This Code and Project Template is referenced and derived from: https://github.com/mschwarzmueller/nodejs-basics-tutorial
var express = require('express');
var router = express.Router();
var request = require('request');
var request2 = require('request');
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Welcome to Food App' });
});

router.get('/test/:id', function(req, res, next) {

    var MongoClient = require('mongodb').MongoClient;
    var assert = require('assert');
    var url = 'mongodb://root:JesusisLord_300@ds227555.mlab.com:27555/lab9';
    var insertDocument = function(db, callback) {

        var cursor = db.collection('name').find({"name": "pizza"});
        cursor.each(function(err,doc) {
            assert.equal(err,null);

            console.log("First Name:" + doc.name);
            console.log("Last Name:" + doc.item);

        });
    };
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        insertDocument(db, function() {
            db.close();
        });
    });



    // var MongoClient = require('mongodb').MongoClient;
    // var assert = require('assert');
    // var url = 'mongodb://root:JesusisLord_300@ds227555.mlab.com:27555/lab9';
    // var findUserwithName = function(db,callback) {
    //     var cursor = db.collection('name').find({"name": "Paul Charles"});
    //     cursor.each(function(err,doc) {
    //         assert.equal(err,null);
    //
    //             console.log("First Name:" + doc.name);
    //             console.log("Last Name:" + doc.item);
    //
    //     });
    // };
    // MongoClient.connect(url, function(err, db) {
    //     assert.equal(null, err);
    //     insertDocument(db, function() {
    //         db.close();
    //     });
    // });
    // console.log("hi");


    var r = req.params.id;
    var split = r.split("LLLLL");
    res.render('test', {output: split[0],output2:split[1],output3:split[2],output4:split[3],output5:split[4],output6:split[5],output7:split[6],output8:split[7],output9:split[8],output10:split[9],output11:split[10]});

});
var q;
router.post('/test/submit', function(req, res, next) {

    var MongoClient = require('mongodb').MongoClient;
    var assert = require('assert');
    var url = 'mongodb://root:JesusisLord_300@ds227555.mlab.com:27555/lab9';
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        console.log("Connected correctly to server.");
        db.close();
    });

    // var insertDocument = function(db, callback) {
    //
    //     db.collection('name').updateOne( {
    //         "name":"Paul Santosh"
    //     }, function(err, result) {
    //         assert.equal(err, null);
    //         console.log("Deleted a document into the asedemo collection.");
    //         callback();
    //     });
    //
    //
    // };
    // MongoClient.connect(url, function(err, db) {
    //     assert.equal(null, err);
    //     insertDocument(db, function() {
    //         db.close();
    //     });
    // });

//var url = 'mongodb://marmik:2621@ds051923.mlab.com:51923/demo';
    var insertDocument = function(db, callback) {

        db.collection('name').insertOne( {
            "name":"Paul Charles",
            "food":"tacos"
        }, function(err, result) {
            assert.equal(err, null);
            console.log("Inserted a document into the names collection.");
            callback();
        });
    };
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        insertDocument(db, function() {
            db.close();
        });
    });

    var id = req.body.id;
    var y = req.body.id2;
    var mainString1 = 'https://kgsearch.googleapis.com/v1/entities:search?query=';
    var mainString2 = '&key=AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg&limit=1&indent=True';
    var totalString = mainString1 + y  + mainString2;
    var tempString = 'https://kgsearch.googleapis.com/v1/entities:search?query=pizza&key=AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg&limit=1&indent=True';
    request(totalString, function (error, response, body) {

        body = JSON.parse(body);
        var ven = body;
        var k = ven['itemListElement'][0]['result']['description'];
        var l = ven['itemListElement'][0]['result']['name'];
        var j = ven['itemListElement'][0]['resultScore'];
        var i = ven['itemListElement'][0]['result']['@type'][0];
        var b = ven['itemListElement'][0]['result']['detailedDescription']['articleBody'];
        var h = b.replace(/(\r\n|\n|\r)/gm," ");

        var insertDocument = function(db, callback) {

            db.collection('name').insertOne( {
                "name":id,
                "item":k
            }, function(err, result) {
                assert.equal(err, null);
                console.log("Deleted a document into the asedemo collection.");
                callback();
            });


        };
        MongoClient.connect(url, function(err, db) {
            assert.equal(null, err);
            insertDocument(db, function() {
                db.close();
            });
        });

        var p = k + "LLLLL" + l + "LLLLL" + j + "LLLLL" + i + "LLLLL" + h;
        console.log(k.toString());
        q=p;
        res.redirect('/test/' + id);
    });

    // var mainString3 = 'https://api.edamam.com/api/food-database/parser?ingr=pizza&app_id=206a6911&app_key=d22367be1c1073a350195c85d95aaf4b&page=0';
    // var mainString4 = 'https://api.edamam.com/api/food-database/parser?ingr=';
    // var mainString5 = '&app_id=206a6911&app_key=d22367be1c1073a350195c85d95aaf4b&page=0';
    // var mainString6 = mainString4 + id + mainString5;
    // request2(mainString6, function (error, response, body) {
    //
    //    var t = JSON.parse(body);
    //     var k = t.parsed[0]['food']['label'];
    //     var y =t.hints[0].measures[0].label;
    //     var h = t.hints[0].measures[1].label;
    //     var p =t.hints[0].measures[2].label;
    //     var o =t.hints[0].measures[3].label;
    //     var z =t.hints[0].measures[4].label;
    //     var j = y + "LLLLL" + h + "LLLLL" + p + "LLLLL" + o + "LLLLL" +z + "LLLLL";
    //     console.log("hi" + q+k);
    //     console.log("h23i" + h);
    //     var u = k+q;
    //     var v = j + k +"LLLLL" + q;
    //     res.redirect('/test/' + v);
    // });
});


router.post('/test/submit2', function(req, res, next) {

    var MongoClient = require('mongodb').MongoClient;
    var assert = require('assert');
    var url = 'mongodb://root:JesusisLord_300@ds227555.mlab.com:27555/lab9';
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        console.log("Connected correctly to server.");
        db.close();
    });
    console.log("HI GUYS!!!!!!")
    // var insertDocument = function(db, callback) {
    //
    //     db.collection('name').updateOne( {
    //         "name":"Paul Santosh"
    //     }, function(err, result) {
    //         assert.equal(err, null);
    //         console.log("Deleted a document into the asedemo collection.");
    //         callback();
    //     });
    //
    //
    // };
    // MongoClient.connect(url, function(err, db) {
    //     assert.equal(null, err);
    //     insertDocument(db, function() {
    //         db.close();
    //     });
    // });

//var url = 'mongodb://marmik:2621@ds051923.mlab.com:51923/demo';
//     var insertDocument = function(db, callback) {
//
//         db.collection('name').insertOne( {
//             "name":"Paul Charles"
//         }, function(err, result) {
//             assert.equal(err, null);
//             console.log("Inserted a document into the asedemo collection.");
//             callback();
//         });
//
//
//     };
//     MongoClient.connect(url, function(err, db) {
//         assert.equal(null, err);
//         insertDocument(db, function() {
//             db.close();
//         });
//     });

    var id = req.body.id;
    var mainString1 = 'https://kgsearch.googleapis.com/v1/entities:search?query=';
    var mainString2 = '&key=AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg&limit=1&indent=True';
    var totalString = mainString1 + id  + mainString2;
    var tempString = 'https://kgsearch.googleapis.com/v1/entities:search?query=pizza&key=AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg&limit=1&indent=True';
    request(totalString, function (error, response, body) {

        body = JSON.parse(body);
        var ven = body;
        var k = ven['itemListElement'][0]['result']['description'];
        var l = ven['itemListElement'][0]['result']['name'];
        var j = ven['itemListElement'][0]['resultScore'];
        var i = ven['itemListElement'][0]['result']['@type'][0];
        var b = ven['itemListElement'][0]['result']['detailedDescription']['articleBody'];
        var h = b.replace(/(\r\n|\n|\r)/gm," ");

        var insertDocument = function(db, callback) {

            db.collection('name').insertOne( {
                "name":k
            }, function(err, result) {
                assert.equal(err, null);
                console.log("Deleted a document into the asedemo collection.");
                callback();
            });


        };
        MongoClient.connect(url, function(err, db) {
            assert.equal(null, err);
            insertDocument(db, function() {
                db.close();
            });
        });

        var p = k + "LLLLL" + l + "LLLLL" + j + "LLLLL" + i + "LLLLL" + h;
        console.log(k.toString());
        q=p;
    });
    var mainString3 = 'https://api.edamam.com/api/food-database/parser?ingr=pizza&app_id=206a6911&app_key=d22367be1c1073a350195c85d95aaf4b&page=0';
    var mainString4 = 'https://api.edamam.com/api/food-database/parser?ingr=';
    var mainString5 = '&app_id=206a6911&app_key=d22367be1c1073a350195c85d95aaf4b&page=0';
    var mainString6 = mainString4 + id + mainString5;
    request2(mainString6, function (error, response, body) {

        var t = JSON.parse(body);
        var k = t.parsed[0]['food']['label'];
        var y =t.hints[0].measures[0].label;
        var h = t.hints[0].measures[1].label;
        var p =t.hints[0].measures[2].label;
        var o =t.hints[0].measures[3].label;
        var z =t.hints[0].measures[4].label;
        var j = y + "LLLLL" + h + "LLLLL" + p + "LLLLL" + o + "LLLLL" +z + "LLLLL";
        console.log("hi" + q+k);
        console.log("h23i" + h);
        var u = k+q;
        var v = j + k +"LLLLL" + q;
        res.redirect('/test/' + v);
    });
});


module.exports = router;
