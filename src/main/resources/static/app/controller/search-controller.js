/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('SearchController', ['$rootScope', '$scope', '$location', '$translate', '$translatePartialLoader', 'GamesService', '$state', '$stateParams', 'SearchService',
    function ($rootScope, $scope, $location, $translate, $translatePartialLoader, GamesService, $state, $stateParams, SearchService) {
        $translatePartialLoader.addPart('search');
        $translate.refresh();

        $scope.noData = undefined;
        if ($stateParams.searchInput != null) {
            SearchService.getSearch({query: $stateParams.searchInput},
                function success(response) {
                    $scope.resultList = response;
                    if($scope.resultList.length == 0) {
                        $scope.noData = "NODATA";
                    }
                    console.log($scope.resultList);
                }, function failure(response) {
                    console.log(response);
                });

            $scope.searchInput = null;
        } else {
            $state.go('home');
        };

        $scope.viewGame = function (gameId) {
            $state.go('games.game', {id: gameId});
        }
    }]);