/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GamesController', ['$rootScope', '$scope', '$http', '$translate', '$translatePartialLoader', 'ApiGames', 'GamesService', '$state',
    function ($rootScope, $scope, $http, $translate, $translatePartialLoader, ApiGames, GamesService, $state) {
        $translatePartialLoader.addPart('games');
        $translate.refresh();

        $scope.gameList = new ApiGames();

        $scope.viewGame = function (gameId) {
            $state.go('games.game', {id: gameId});
        }
    }]);
