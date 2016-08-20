/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.controller('UserManagementController', ['$scope', '$rootScope', 'UserService', '$translate','$translatePartialLoader',
    function ($scope, $rootScope, UserService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('user-management');
        $translate.refresh();

        $scope.itemsByPage = 5;
        $scope.userList = UserService.query();
        $scope.displayedUsers = [].concat($scope.userList);
        console.log($scope.userList);
        $scope.success = null;
        $scope.error = null;

        $scope.deleteUser = function (user) {
            UserService.delete({id: user.userId}, function (result) {
                console.log(result);
                $scope.userList = UserService.query();
                $scope.success = 'OK';
                $scope.error = null;
            }, function (fail) {
                console.log(fail);
                $scope.success = null;
                $scope.error = 'ERROR';
            });
        }

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