/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GameController', ['$rootScope', '$scope', '$http', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function ($rootScope, $scope, $http, $translate, $translatePartialLoader, GamesService, $state) {
        $translatePartialLoader.addPart('games');
        $translate.refresh();

        var gameId = $state.params.id;
        $scope.comment = {};

        $scope.addComment = function () {
            console.log(gameId);
            console.log($scope.comment.commentText);
            $scope.comment.gameId = gameId;
            $http({
                method: 'POST',
                url: '/api/games/comment',
                data: $scope.comment
            // {
            //         'gameId': gameId,
            //         'commentText': $scope.comment.commentText
            //     }
            }).then(function success(response) {
                $scope.game = response.data;
                $scope.commentText = null;
            });
        };

        $scope.gamesList = GamesService.query();
        GamesService.get({id: gameId}, function (response) {
            $scope.game = response;
            console.log(response);
        });

    }]);