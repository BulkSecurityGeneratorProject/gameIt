/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('LoginController', ['$scope', 'toastr', '$rootScope', '$location', 'vcRecaptchaService', '$http', 'CredentialsService', '$translate', '$translatePartialLoader', '$state', '$stateParams',
    function ($scope, toastr, $rootScope, $location, vcRecaptchaService, $http, CredentialsService, $translate, $translatePartialLoader, $state, $stateParams) {
        $translatePartialLoader.addPart('login');
        $translate.refresh();

        $scope.publicKey = "6LfSHB4TAAAAAOqnsjt55soDmCicYAHgDHZB-GX5";

        $scope.credentials = {};
        console.log($stateParams);
        console.log($state.params.justRegistered);
        var justRegistered = $state.params.justRegistered;
        if (justRegistered != undefined) {
            $scope.successRegister = 'SUCCESS';
        }

        $scope.setResponse = function (response) {
            //  console.log(response);
        }

        $scope.cbExpiration = function () {
            $scope.credentials.captcha = undefined;
        };

        $scope.loginLocal = function () {
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
                                    $translate('login.loginLocation').then(function (translatedMessage) {
                                        toastr.success(translatedMessage, {
                                            closeButton: true,
                                            allowHtml: true
                                        });
                                    });
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
    }]);