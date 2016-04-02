/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('NavbarController', ['$scope', '$rootScope','$state', '$http', '$location', 'CredentialsService', '$translate', '$translatePartialLoader',
    function ($scope, $rootScope,$state, $http, $location, CredentialsService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('global');
        $translate.refresh();

        $scope.changeLanguage = function (langKey) {
            $translate.use(langKey);
            $rootScope.language=langKey;

        };

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
                $rootScope.administrator = false;
            }, function error(response) {
                $rootScope.authenticated = false;
                $rootScope.loggedInUser = undefined;
                $rootScope.administrator = false;
            });
        };
    }]);