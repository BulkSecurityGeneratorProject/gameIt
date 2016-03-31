/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('LoginController', ['$scope', '$rootScope', '$location', '$http',
    function ($scope, $rootScope, $location, $http) {
        $scope.hello = "hello";

        var authenticate = function (credentials, callback) {
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
            $http({
                method: 'GET',
                url: 'api/authenticate',
                headers: headers
            }).then(function success(response) {
                if (response.data.username) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                console.log(response);
                callback && callback();
            }, function error(response) {
                $rootScope.authenticated = false;
                console.log(response);
                callback && callback();
            });

        }
        authenticate();
//TODO: ADD AUTHENTICATE FUNCTION AS ROOT TO NAVIGATION
        $scope.credentials = {};

        $scope.loginLocal = function () {
            authenticate($scope.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };
    }]);