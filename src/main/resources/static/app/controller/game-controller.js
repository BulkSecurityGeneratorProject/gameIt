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
            console.log($rootScope.loggedInUser);
            console.log(gameId);
            console.log($scope.comment.commentText);
            $scope.comment.gameId = gameId;
            $scope.comment.userId = $rootScope.loggedInUser.userId;
            $http({
                type: 'POST',
                url: '/api/games/comment',
                data: {'commentGameObject': $scope.comment}
            }).then(function success(response) {
                console.log(response);
            });
        };

        $scope.gamesList = GamesService.query();
        GamesService.get({id: gameId}, function (response) {
            $scope.game = response;
            console.log(response);
        });

    }]);