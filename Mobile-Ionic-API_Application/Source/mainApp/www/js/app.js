//Parts of this code was adapted and referenced from the todoApp original template for CS5551 Tutorial 6 Source Code (CS5551_Tutorial6_SourceCode\CS5551_Tutorial6_SourceCode\todoApp\www\js\app.js)
angular.module('app1', ['ionic','ngCordova'])
    

  
  .controller('MainCtrl', function ($scope, $ionicModal, $http, $location, $cordovaDialogs) {
    //From original template from todoApp original template for CS5551 Tutorial 6 Source Code (todoApp)
    $ionicModal.fromTemplateUrl('views/appModal.html', function (modal) {
      $scope.taskModal = modal;
    }, {
        scope: $scope,
        animation: 'slide-in-up'
      });
      
    //New code added It was from the cordova plugins for Dialog it prompts the user to give an input
    
   var mainVal = $cordovaDialogs.prompt("Enter Your name", "hi", [], "User") .then(function(result) {
                                                   document.getElementById("nameTitle").innerHTML =
        "Hello " + result.input1;
        });
    $scope.locations = new Array();
    //test function
     $scope.displayText = function () {
         var httpReq = $http.get("https://api.foursquare.com/v2/venues/search?client_id=Z2TN2KU0NO0KFPAQT4AXV1UHCAT4YTPGITT2TFDJQMRGCOOQ&client_secret=CFUSS1WAR3S0IMJICNJEBCYEXT23JP5MZ3ZWZFZDV1PH4ZZB&=Lawrence&query=pizza");
          httpReq.success(function (values) {
                        
                   if (values != null ) {
                       
                       $scope.count = "valid";
                    }

                })
    };
    
    

     
     $scope.redirect = function(){
               $location.path('/index2.html');
            }
   
//Adapted from original template from todoApp original template for CS5551 Tutorial 6 Source Code (todoApp) (CS5551_Tutorial6_SourceCode\CS5551_Tutorial6_SourceCode\todoApp\www\js\app.js)
    $scope.changePage= function () {
      $scope.taskModal.show();
    };
    
    
    $scope.addFood = function (food,location) {
        var food2;
      
           
        
        
        var t = 0; 
   var q = 'Choices: ';
        //Adapted from Google Knowledge Graph Search API: https://developers.google.com/knowledge-graph/
     var google_url = 'https://kgsearch.googleapis.com/v1/entities:search';
  var setting = {
    'query': food,
    'limit': 5,
    'indent': true,
    'key' : 'AIzaSyAKLKeODy54hdbvXKurKfJ2PpZQz1wf2Rg',
  };
        //Adapted from Google Knowledge Graph Search API: https://developers.google.com/knowledge-graph/
  $.getJSON(google_url + '?callback=?', setting, function(response) {
    $.each(response.itemListElement, function(i, element) {
      if(t==0)
          {
              $scope.count = element['result']['name'];
          }
        if(t==1)
            {
                $scope.count1 = element['result']['name'];
            }
        if(t==2)
            {
                $scope.count2 = element['result']['name'];
            }
        if(t==4)
            {
                $scope.count4 = element['result']['name'];
            }
        if(t==3)
            {
                $scope.count3 = element['result']['name'];
            }
       
     
       t++;
    });
  });
        //Adapted and referenced from CS5551 Tutorial 3 Source Code: CS5551_T3-WebMashUp-Part1\CS5551_T3-WebMashUp\MashUp\app\app.js
        var string3 = "https://api.foursquare.com/v2/venues/search?client_id=Z2TN2KU0NO0KFPAQT4AXV1UHCAT4YTPGITT2TFDJQMRGCOOQ&client_secret=CFUSS1WAR3S0IMJICNJEBCYEXT23JP5MZ3ZWZFZDV1PH4ZZB&v=20160215&limit=5&near=" + location +"&query=" + food;
          httpReq = $http.get(string3);
                httpReq.success(function (values) {

                    if (values != null) {
                        for (var i = 0; i < 5; i++) {
                            $scope.locations[i] = {
                                "name": values.response.venues[i].name,
                            };
                            $scope.count7 = values.response.venues[0].name;
                            $scope.count8 = values.response.venues[1].name;
                            $scope.count9 = values.response.venues[2].name;
                            $scope.count10 = values.response.venues[3].name;
                            $scope.count11 = values.response.venues[4].name;
                        }
                         
                    }

                })
    };
    

    
   $scope.confirmDialog = function() {
        var value = $cordovaDialogs.prompt("Hi", "hi", ["button 1","button 2","button 2"], "button 3") .then(function() {
      // callback success
    });

    }
  
  });