/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('LoginController', ['$scope', '$rootScope', '$location', 'vcRecaptchaService', '$http', 'CredentialsService', '$translate', '$translatePartialLoader', '$state',
    function ($scope, $rootScope, $location, vcRecaptchaService, $http, CredentialsService, $translate, $translatePartialLoader, $state) {
        $translatePartialLoader.addPart('login');
        $translate.refresh();
        // $.getScript("https://www.google.com/recaptcha/api.js");
        $scope.publicKey = "6LfSHB4TAAAAAOqnsjt55soDmCicYAHgDHZB-GX5";

        $scope.credentials = {};

        $scope.setResponse = function (response) {
            // send the `response` to your server for verification
          //  console.log(response);
        };

        $scope.cbExpiration = function () {
            // reset the 'response' object that is on scope
            $scope.credentials.captcha = undefined;
            console.log("reset captcha");
        };

        $scope.loginLocal = function () {
            console.log($scope.credentials.captcha);
            $http({
                method: 'POST',
                url: '/login',
                data: $.param({
                    'username': $scope.credentials.username,
                    'password': $scope.credentials.password,
                    'g-recaptcha-response': $scope.credentials.captcha
                }), headers: {
                    "content-type": "application/x-www-form-urlencoded"
                }
            }).then(function success(response) {
                console.log("success login");
                console.log(response);
                CredentialsService.authenticate(function () {
                    if ($rootScope.authenticated) {
                        console.log("success authenticate");
                        if (navigator.geolocation) {
                            navigator.geolocation.getCurrentPosition(function (position) {
                                var locationObject = {
                                    latitude: position.coords.latitude,
                                    longitude: position.coords.longitude,
                                    username: $rootScope.loggedInUser.username
                                };
                                $http({
                                    method: 'POST',
                                    url: 'api/location',
                                    data: locationObject
                                }).then(function success(response) {
                                    console.log("sucess add to map");
                                })
                            });
                        }
                        $state.go('home');
                        $scope.error = false;

                    } else {
                        $state.go('login');
                        $scope.error = true;
                    }
                });
            }, function error(data) {
                console.log("error login");
                $state.go('login');
                $scope.error = true;
                $rootScope.authenticated = false;
            });
        };
        // $scope.loginLocal = function () {
        //     CredentialsService.authenticate($scope.credentials, function () {
        //         if ($rootScope.authenticated) {
        //             if (navigator.geolocation) {
        //                 navigator.geolocation.getCurrentPosition(function(position) {
        //                     var locationObject = {
        //                         latitude: position.coords.latitude,
        //                         longitude: position.coords.longitude,
        //                         username: $rootScope.loggedInUser.username
        //                     };
        //                     $http({
        //                         method: 'POST',
        //                         url: 'api/location',
        //                         data: locationObject
        //                     }).then(function success(response) {
        //                         console.log("sucess add to map");
        //                     })
        //                 });
        //             }
        //             $location.path("/");
        //             $scope.error = false;
        //         } else {
        //             $location.path("/login");
        //             $scope.error = true;
        //         }
        //     });
        // }

    }]);