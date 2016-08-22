/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('AdminGraphicsTableController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader', 'UserService', 'GamesService',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader, UserService, GamesService) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        $scope.gameList = [];
        $http({
            method: 'GET',
            url: 'api/games/sortByViews'
        }).then(function success(response) {
            $scope.gameList = response.data
            createGamesChart();
        }, function error(response) {
            $("#gamesChart").html("There was a problem with loading the data");
        });
        var userList = UserService.query();
        console.log(userList);
        var numComments = 0;
        for (var i = 0; i < userList.length; i++) {
            numComments += userList[i].commentsGame.length;
        }
        console.log(numComments);
        function createGamesChart(gameList) {
            var chart = c3.generate({
                bindto: '#gamesChart',
                data: {
                    columns: [],
                    type: 'bar'
                }
            });
            for(var i=0; i < $scope.gameList.length; i++){
                var gameName = $scope.gameList[i].gameName;
                var numViews = $scope.gameList[i].gameNumberOfViews;
                // setTimeout(function () {
                    chart.load({
                        columns: [
                            [gameName, numViews]
                        ]
                    });
                // }, 2000);
            }
        }

    }]);