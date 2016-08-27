/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GameController', ['$rootScope', 'toastr', '$scope', '$http', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function ($rootScope, toastr, $scope, $http, $translate, $translatePartialLoader, GamesService, $state) {
        $translatePartialLoader.addPart('games');
        $translate.refresh();

        var gameId = $state.params.id;
        $scope.comment = {};

        $scope.addComment = function () {
            $scope.comment.gameId = gameId;
            $http({
                method: 'POST',
                url: '/api/games/comment',
                data: $scope.comment
            }).then(function success(response) {
                $scope.game = response.data;
                $scope.comment = {};
                $translate('games.game.commentSuccess').then(function (translatedMessage) {
                    toastr.success(translatedMessage, {
                        closeButton: true,
                        allowHtml: true
                    });
                });
            });
        };

        $scope.gamesList = GamesService.query();
        GamesService.get({id: gameId}, function (response) {
            $scope.game = response;
            $scope.minPerf = $scope.game.gameMinimalPerformance.split("@AND@");
            $scope.optPerf = $scope.game.gameOptimalPerformance.split("@AND@");
        });

    }]);