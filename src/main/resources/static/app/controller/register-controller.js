/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.controller('RegisterController', ['$scope', '$rootScope', '$translate', '$translatePartialLoader', 'CredentialsService', '$state','$stateParams',
    function ($scope, $rootScope, $translate, $translatePartialLoader, CredentialsService, $state, $stateParams) {
        $translatePartialLoader.addPart('register');
        $translate.refresh();

        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.registerAccount = {};

        $scope.register = function () {
            if ($scope.registerAccount.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.doNotMatch = null;
                $scope.error = null;
                $scope.errorUserExists = null;
                $scope.errorEmailExists = null;

                CredentialsService.register($scope.registerAccount, function success(data) {
                    $scope.error = null;
                    $scope.doNotMatch = null;
                    $scope.errorUserExists = null;
                    $state.go('login', {justRegistered: true});
                }, function failure(response) {
                    $scope.success = null;
                    if (response.status === 400 && response.data.message === 'username is already in use') {
                        $scope.errorUserExists = 'ERROR';
                        angular.element('[ng-model="registerAccount.username"]').focus();
                    } else if (response.status === 400 && response.data.message === 'e-mail is already in use') {
                        $scope.errorEmailExists = 'ERROR';
                        angular.element('[ng-model="registerAccount.email"]').focus();
                    } else {
                        $scope.error = 'ERROR';
                        angular.element('[ng-model="registerAccount.username"]').focus();
                    }
                });
            }
        };
    }]);