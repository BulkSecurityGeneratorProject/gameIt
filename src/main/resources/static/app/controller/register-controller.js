/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.controller('RegisterController', ['$scope', '$rootScope','$translate', '$translatePartialLoader',
    function ($scope, $rootScope,$translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('register');
        $translate.refresh();

        
    }]);