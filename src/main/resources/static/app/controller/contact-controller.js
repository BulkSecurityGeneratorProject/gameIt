/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('ContactController', ['$rootScope', 'toastr', '$scope', '$http', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function ($rootScope, toastr, $scope, $http, $translate, $translatePartialLoader, GamesService, $state) {
        $translatePartialLoader.addPart('contact');
        $translate.refresh();


    }]);