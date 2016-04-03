/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('NewsController',[ '$rootScope', '$scope', '$translate', '$translatePartialLoader',
    function($rootScope, $scope, $translate, $translatePartialLoader){
        $translatePartialLoader.addPart('news');
        $translate.refresh();

    }]);