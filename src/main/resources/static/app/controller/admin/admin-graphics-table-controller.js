/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('AdminGraphicsTableController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader', 'UserService', 'GamesService',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader, UserService, GamesService) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        var gameList = GamesService.query();
        var userList = UserService.query();
        console.log(userList);
        var numComments = 0;
        for (i = 0; i < userList.length; i++) {
            numComments += userList[i].commentsGame.length;
        }
        console.log(numComments);
        var chart = c3.generate({
            bindto: '#chart',
            data: {
                columns: [
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            }
        });
    }]);