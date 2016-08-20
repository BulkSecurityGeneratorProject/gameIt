/**
 * Created by Stefan on 01.04.2016.
 */
gameItAngularApp.factory('CredentialsService', ['$http', '$rootScope', '$translate', function ($http, $rootScope, $translate) {
    return {
            authenticate: function (credentials, callback) {
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
            $http({
                method: 'GET',
                url: 'api/authenticate',
                headers: headers
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
                        console.log(response.data.langKey);
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
        register: function(credentials, callback) {
            $http({
                method: 'POST',
                url: 'api/register',
                data: credentials
            }).then(function success(response){
                console.log("Successful register");
                console.log(response);
                callback && callback();
            }, function failure(response) {
                console.log("Failed register");
                console.log(response);
                callback && callback();
            });
        },
        updateAccount: function(credentials, callbackSuccess, callbackFailure) {
            $http({
                method: 'PUT',
                url: 'api/users/'+credentials.username,
                data: credentials
            }).then(function success(response){
                console.log("Successful update");
                console.log(response);
                callbackSuccess && callbackSuccess();
            }, function failure(response) {
                console.log("Failed update");
                console.log(response);
                callbackFailure && callbackFailure();
            });
        }
    };

}]);