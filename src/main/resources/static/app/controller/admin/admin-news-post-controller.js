/**
 * Created by Stefan on 02.04.2016.
 */
gameItAngularApp.controller('AdminNewsPostController', ['$scope', '$rootScope','NewsPostService','$http', '$translate', '$translatePartialLoader','$state',
    function ($scope, $rootScope,NewsPostService,$http, $translate, $translatePartialLoader, $state) {
        $translatePartialLoader.addPart('news');
        $translate.refresh();
        $scope.newsPost = {};
        $scope.tagObject = {};
        // $http.get('/api/news').then(function success(response){
        //     console.log(response);
        //     $scope.inserted=response.data[0].postDescription;
        // })
            $scope.addNewPost = function() {
                console.log($scope.newsPost);
           $http({
               method: 'POST',
               url: '/api/news',
               data: {
                   newsPost: $scope.newsPost,
                   tagObject: $scope.tagObject
               }
           }).then(function success(response){
               $scope.inserted = response.postDescription;
               $scope.success = 'SUCCESS';
               $scope.error = undefined;
             //  $state.go('admin.admin-news-post-success');
           }, function error(response) {
               $scope.error = 'ERROR';
               $scope.success = undefined;
           });
        }
    }]);