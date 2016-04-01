/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('NavbarController', ['$scope', '$rootScope', '$http', '$location', 'CredentialsService',
    function ($scope, $rootScope, $http, $location, CredentialsService) {
        CredentialsService.authenticate();
        $scope.logout = function () {
            $http({
                method: 'POST',
                url: 'logout',
                data: {}
            }).then(function success(response) {
                $rootScope.authenticated = false;
                $rootScope.loggedInUser = undefined;
                $location.path("/");
            }, function error(response) {
                $rootScope.authenticated = false;
                $rootScope.loggedInUser = undefined;
            });
        };
    }]);