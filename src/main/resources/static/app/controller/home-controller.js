/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('HomeController', ['$scope', '$state', '$rootScope', '$http', '$translate', '$translatePartialLoader', 'CredentialsService',
    function ($scope, $state, $rootScope, $http, $translate, $translatePartialLoader, CredentialsService) {
        $translatePartialLoader.addPart('home');
        $translate.refresh();
        $scope.logout = function () {
            CredentialsService.logout(function success(response) {
                if ($scope.$state.current.name == 'home') {
                    $state.transitionTo('home', {}, {reload: true, notify: true});
                } else {
                    $state.go('home');
                }
            });
        }
    }]);