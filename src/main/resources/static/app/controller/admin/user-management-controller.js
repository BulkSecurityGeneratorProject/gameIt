/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.controller('UserManagementController', ['$scope','toastr','$http', '$rootScope', 'UserService', '$translate','$translatePartialLoader',
    function ($scope, toastr,$http, $rootScope, UserService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('user-management');
        $translate.refresh();

        $scope.itemsByPage = 5;
        $scope.userList = UserService.query();
        $scope.displayedUsers = [].concat($scope.userList);
        console.log($scope.userList);

        $scope.deleteUser = function (user) {
            UserService.delete({id: user.userId}, function (result) {
                console.log(result);
                $scope.userList = UserService.query();
                $translate('admin.sidenav.users.success').then(function (translatedMessage) {
                    toastr.success(translatedMessage, {
                        closeButton: true,
                        allowHtml: true
                    });
                });
            }, function (fail) {
                console.log(fail);
                $translate('admin.sidenav.users.error').then(function (translatedMessage) {
                    toastr.error(translatedMessage, {
                        closeButton: true,
                        allowHtml: true
                    });
                });
            });
        };
        $scope.makeAdmin = function(user) {
            console.log(user.isAdmin);
            $http({
                method: 'POST',
                url: 'api/users/'+user.userId+"/admin",
                data: user.isAdmin
            }).then(function success(response) {
                $translate('admin.sidenav.users.adminChanged').then(function (translatedMessage) {
                    toastr.success(translatedMessage, {
                        closeButton: true,
                        allowHtml: true
                    });
                });
            });
        };
        /*  var editUserDialog = $modal({
         scope: $scope,
         title: 'true',
         template: 'views/modal/modal-dialog.html',
         contentTemplate: '/views/modal/impl/updateUserModal.html',
         show: false
         });
         $scope.updateUser = function (user) {
         editUserDialog.show();
         $scope.updateUsername = user.username;
         }
         $scope.update = function (username) {
         User.update({username: username}, function (result) {
         toastr.success('User updated!');
         $scope.userList = User.query();
         editUserDialog.hide();
         }, function () {
         toastr.error('course not removed! Error occurred.');
         });
         }*/

    }]);