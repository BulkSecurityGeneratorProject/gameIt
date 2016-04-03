/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('LoginController', ['$scope', '$rootScope', '$location', '$http', 'CredentialsService', '$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $location, $http, CredentialsService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('login');
        $translate.refresh();
        
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

    }]);