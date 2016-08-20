/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('PasswordController', ['$rootScope', '$scope', '$translate', '$translatePartialLoader', '$state',
    function ($rootScope, $scope, $translate, $translatePartialLoader, $state) {
        $translatePartialLoader.addPart('password');
        $translate.refresh();


    }]);