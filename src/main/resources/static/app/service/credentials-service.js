/**
 * Created by Stefan on 01.04.2016.
 */
gameItAngularApp.factory('CredentialsService', ['$http', '$rootScope', '$translate', function ($http, $rootScope, $translate) {
    return {
            authenticate: function (callback) {
            $http({
                method: 'GET',
                url: 'api/authenticate',
            }).then(function success(response) {
                if (response.data.name) {
                    if ($rootScope.loggedInUser === undefined) {
                        $rootScope.loggedInUser = response.data.principal;
                    }
                    if(response.data.authorities[0].authority == "ROLE_ADMIN"){
                        $rootScope.administrator = true;
                    }
                    $rootScope.authenticated = true;
                    console.log("requesting lang");
                    $http({
                        method: 'GET',
                        url: 'api/lang'
                    }).then(function success(response) {
                        $rootScope.language = response.data.langKey;
                        $translate.use($rootScope.language);
                    }).catch(function error(response){
                        console.log(response);
                    });
                } else {
                    $rootScope.loggedInUser = undefined;
                    $rootScope.authenticated = false;
                    $rootScope.administrator = false;
                }
                callback && callback();
            }, function error(response) {
                $rootScope.authenticated = false;
                $rootScope.administrator = false;
                callback && callback();
            });

        },
        register: function(credentials, callbackSuccess, callbackError) {
            $http({
                method: 'POST',
                url: 'api/register',
                data: credentials
            }).then(function success(response){
                callbackSuccess && callbackSuccess(response);
            }, function failure(response) {
                callbackError && callbackError(response);
            });
        },
        updateAccount: function(credentials, callbackSuccess, callbackFailure) {
            $http({
                method: 'PUT',
                url: 'api/users/'+credentials.username,
                data: credentials
            }).then(function success(response){
                callbackSuccess && callbackSuccess();
            }, function failure(response) {
                callbackFailure && callbackFailure();
            });
        }
    };

}]);