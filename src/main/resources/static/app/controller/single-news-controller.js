/**
 * Created by Stefan on 22.8.2016.
 */
gameItAngularApp.controller('SingleNewsPostController',[ '$rootScope', '$scope', '$translate', '$translatePartialLoader','NewsPostService','$state',
    function($rootScope, $scope, $translate, $translatePartialLoader, NewsPostService, $state){
        $translatePartialLoader.addPart('news');
        $translate.refresh();

        var postId = $state.params.id;
      //  $scope.gamesList = GamesService.query();
        NewsPostService.get({id: postId}, function (response) {
            $scope.newsPost = response;
            console.log(response);
        });
    }]);