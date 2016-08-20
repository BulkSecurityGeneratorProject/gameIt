/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('SettingsController', ['$rootScope', '$scope', '$http', 'Upload', '$translate', '$translatePartialLoader', '$state', 'CredentialsService', 'UserService',
    function ($rootScope, $scope, $http, Upload, $translate, $translatePartialLoader, $state, CredentialsService, UserService) {
        $translatePartialLoader.addPart('settings');
        $translate.refresh();

        var map;

        function initMap() {
            console.log("init map called");
            var locationObject = undefined;
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    locationObject = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };
                });
            }
            if (locationObject === undefined) {
                locationObject = {
                    lat: 42,
                    lng: 21
                };
            }
            map = new google.maps.Map(document.getElementById('map'), {
                center: locationObject,
                zoom: 8
            });
            $http({
                method: 'GET',
                url: 'api/location'
            }).then(function success(response) {
                for (i = 0; i < response.data.length; i++) {
                    var position = {
                        lat: response.data[i].latitude,
                        lng: response.data[i].longitude
                    }
                    var marker = new google.maps.Marker({
                        position: position,
                        map: map,
                        title: 'dsdsa'
                    });
                }
            });
        }
        initMap();

        $scope.success = null;
        $scope.error = null;
        function getUser() {
            $http({
                method: 'GET',
                url: 'api/me'
            }).then(function success(response) {
                console.log(response);
                $scope.settingsAccount = copyAccount(response.data);
            });
        }

        getUser();

        $scope.save = function (file) {
            CredentialsService.updateAccount($scope.settingsAccount, function success() {
                if (file !== undefined) {
                    $scope.upload(file);
                } else {
                    getUser();
                }
                $scope.error = null;
                $scope.success = 'OK';

            }, function failure() {
                $scope.success = null;
                $scope.error = 'ERROR';
            });
        };

        $scope.submit = function () {
            if ($scope.form.file.$valid && $scope.file) {
                $scope.save($scope.file);
            } else {
                $scope.save(undefined);
            }
        };

        $scope.upload = function (file) {
            Upload.upload({
                url: 'api/account/upload',
                data: {file: file}
            }).then(function (resp) {
                console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
                getUser();
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);

            });
        };

        var copyAccount = function (account) {
            return {
                email: account.email,
                firstName: account.firstName,
                lastName: account.lastName,
                username: account.username,
                userDescription: account.userDescription,
                profileImage: account.profileImage,
                authorities: account.authorities,
                userId: account.userId

            }
        };
    }]);