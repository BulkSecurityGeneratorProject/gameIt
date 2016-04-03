/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GamesController',['$rootScope','$scope', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function($rootScope,$scope, $translate, $translatePartialLoader,GamesService, $state){
        $translatePartialLoader.addPart('games');
        $translate.refresh();


        $scope.gamesList=GamesService.query();
        console.log($scope.gamesList);
        $scope.text="ADJE TOMMY BABA";
        $scope.viewGame = function(gameId) {
            $state.go('games.game', {id:gameId});
        }
}]);