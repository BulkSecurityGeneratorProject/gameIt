/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('LoginController', ['$scope', '$rootScope', '$location', '$http', 'CredentialsService',
    function ($scope, $rootScope, $location, $http, CredentialsService) {
        $scope.hello = "hello";
              $scope.credentials = {};
        $scope.loginLocal = function () {
            CredentialsService.authenticate($scope.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        }
        $scope.loginFacebook = function(){
            $http({
                method: 'GET',
                url: '/login/facebook'
            });
        }
        $scope.loginGoogle = function(){
            $http({
                method: 'GET',
                url: '/login/google'
            });
        }
    }]);