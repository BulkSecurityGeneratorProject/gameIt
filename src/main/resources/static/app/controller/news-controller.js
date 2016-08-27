/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('NewsController',[ '$rootScope', '$scope', '$translate', '$stateParams', '$translatePartialLoader','NewsPostService','$state', 'ApiNews',
    function($rootScope, $scope, $translate, $stateParams, $translatePartialLoader, NewsPostService, $state, ApiNews){
        $translatePartialLoader.addPart('news');
        $translate.refresh();
        console.log($rootScope.searchTagResultList);
        if ($rootScope.searchTagResultList !== undefined) {
            $scope.newsPostList = new ApiNews();
            $scope.newsPostList.items = $rootScope.searchTagResultList
        } else {
            $scope.newsPostList = new ApiNews();
        }

        console.log("newspostList: "+$scope.newsPostList.items);

        $scope.viewNewsPost = function (newsPostId) {
            $state.go('news.single', {id: newsPostId});
        }
    }]);