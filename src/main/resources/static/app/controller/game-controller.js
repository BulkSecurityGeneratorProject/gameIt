/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GameController', ['$rootScope', '$scope', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function ($rootScope, $scope, $translate, $translatePartialLoader, GamesService, $state) {
        $translatePartialLoader.addPart('games');
        $translate.refresh();

        var gameId = $state.params.id;
        $scope.gamesList = GamesService.query();
        GamesService.get({id: gameId}, function (response) {
            $scope.game = response;
            console.log(response);
        });

    }]);