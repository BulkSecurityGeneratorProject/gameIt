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

        //$scope.gamesList = GamesService.query();
        $scope.userRating = 0;
        GamesService.get({id: gameId}, function (response) {
            $scope.game = response;
            $scope.minPerf = $scope.game.gameMinimalPerformance.split("@AND@");
            $scope.optPerf = $scope.game.gameOptimalPerformance.split("@AND@");
            if ($rootScope.loggedInUser != null) {
                for (var i = 0; i < $scope.game.ratings.length; i++) {
                    if ($scope.game.ratings[i].userId.username = $rootScope.loggedInUser.username) {
                        $scope.userRating = $scope.game.ratings[i].rating;
                        $scope.rate = $scope.userRating;
                        break;
                    }
                }
            }
        });

        $scope.rate = 0;
        $scope.max = 5;
        $scope.isReadonly = $rootScope.loggedInUser == null;

        $scope.hoveringOver = function (value) {
            $scope.overStar = value;
            $scope.percent = 100 * (value / $scope.max);
        };

        $scope.ratingStates = [
            {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
        ];

        $scope.updateRating = function (gameId) {
            $http({
                method: 'POST',
                url: 'api/games/' + gameId + '/rate',
                data: $scope.userRating
            }).then(function success(response) {
                console.log(response);
                $scope.game = response.data;
            }, function error(response) {

            });
        }
    }]);