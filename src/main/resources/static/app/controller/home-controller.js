/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('HomeController', ['$scope', '$state', '$rootScope', '$http', '$translate', '$translatePartialLoader', 'CredentialsService',
    function ($scope, $state, $rootScope, $http, $translate, $translatePartialLoader, CredentialsService) {
        $translatePartialLoader.addPart('home');
        $translate.refresh();
        $scope.logout = function () {
            CredentialsService.logout(function success(response) {
                if ($scope.$state.current.name == 'home') {
                    $state.transitionTo('home', {}, {reload: true, notify: true});
                } else {
                    $state.go('home');
                }
            });
        }

        var fileName = "test.pdf";
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        $http({
            method: 'GET',
            url: 'report',
            headers : {
                'Content-type' : 'application/pdf'
            },
            responseType: 'arraybuffer'
        }).then(function success(response) {
            console.log(response);
            var file = new Blob([response.data], {type: 'application/pdf'});
            var fileURL = window.URL.createObjectURL(file);
            a.href = fileURL;
            a.download = fileName;
            a.click();
        });
     
    }]);