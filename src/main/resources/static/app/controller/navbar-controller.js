/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('NavbarController', ['$scope', '$rootScope', '$state', '$http', '$location', 'CredentialsService', 'SearchService', '$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $state, $http, $location, CredentialsService, SearchService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('global');
        $translate.refresh();

        $scope.changeLanguage = function (langKey) {
            $translate.use(langKey);
            $rootScope.language = langKey;
            if ($rootScope.authenticated) {
                $http({
                    method: 'POST',
                    url: 'api/lang',
                    data: langKey
                });
            }
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
                $state.go('home');
                $rootScope.administrator = false;
            }, function error(response) {
                $rootScope.authenticated = false;
                $rootScope.loggedInUser = undefined;
                $rootScope.administrator = false;
            });
        };

        $scope.getResults = function (val) {
            return $http({
                method: 'POST',
                url: 'api/search',
                data: val
            }).then(function(response){
                return response.data.map(function(item){
                    return item;
                });
            });
        };

        $scope.onSelect = function ($item, $model, $label) {
            $state.go('games.game', {id: $item.gameId});
        };

        $scope.search = function (searchText) {
            if ($scope.searchText != undefined) {
                if ($scope.$state.current.name == 'search') {
                    $state.transitionTo('search', {searchInput: $scope.searchText}, {reload: true, notify: true});
                } else {
                    $state.go('search', {searchInput: $scope.searchText});
                }
            }
        };
    }]);