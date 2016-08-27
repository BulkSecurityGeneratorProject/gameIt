/**
 * Created by Stefan on 02.04.2016.
 */
gameItAngularApp.controller('AdminController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader','$state',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader, $state) {
        $translatePartialLoader.addPart('admin');
        $translate.refresh();

    }]);