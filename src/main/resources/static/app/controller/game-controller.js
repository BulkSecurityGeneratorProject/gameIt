/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GameController',['$rootScope','$scope', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function($rootScope,$scope, $translate, $translatePartialLoader,GamesService, $state){
        $translatePartialLoader.addPart('games');
        $translate.refresh();
        console.log($state);
        var gameId = $state.params.id;

            GamesService.get({id:gameId}, function(response) {
                $scope.game = response;
                console.log(response);
            });

}]);