/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('LoginController', ['$scope', '$rootScope', '$location','vcRecaptchaService', '$http', 'CredentialsService', '$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $location,vcRecaptchaService, $http, CredentialsService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('login');
        $translate.refresh();
        $scope.publicKey = "6LfSHB4TAAAAAOqnsjt55soDmCicYAHgDHZB-GX5";

        $scope.credentials = {};
        $scope.loginLocal = function () {
            if (vcRecaptchaService.getResponse() === "") { //if string is empty
                alert("Please resolve the captcha and submit!")
            } else {
                $http.post('login', $.param({
                    'username': $scope.credentials.username,
                    'password': $scope.credentials.password,
                    'g-recaptcha-response': vcRecaptchaService.getResponse()
                }), {
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    }
                }).success(function (data) {
                    console.log("success login");
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
                            $location.path("/");
                            $scope.error = false;

                        } else {
                            $location.path("/login");
                            $scope.error = true;
                        }
                    });
                }).error(function (data) {
                    console.log("error login");
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                });
            }
        }
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