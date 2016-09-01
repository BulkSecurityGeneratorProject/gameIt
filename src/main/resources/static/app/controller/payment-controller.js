/**
 * Created by Stefan on 31.8.2016.
 */
gameItAngularApp.controller('PaymentController', ['$scope', '$http', 'stripe', 'param', '$uibModalInstance', '$rootScope', '$translate', '$translatePartialLoader', 'CredentialsService', '$state', '$stateParams',
    function ($scope, $http, stripe, param, $uibModalInstance, $rootScope, $translate, $translatePartialLoader, CredentialsService, $state, $stateParams) {

        $scope.game = {};
        $scope.game = param.data;
        $scope.payment = {};
        $scope.payment.card = {};
        $scope.payment.card.number = 4242424242424242;
        $scope.payment.card.exp_month = 8;
        $scope.payment.card.exp_year = 2017;
        console.log($scope.game);

        $scope.charge = function () {
            return stripe.card.createToken($scope.payment.card)
                .then(function (response) {
                    console.log('token created for card ending in ', response.card.last4);
                    var payment = angular.copy($scope.payment);
                    payment.card = void 0;
                    payment.token = response.id;
                    return $http.post('api/games/' + $scope.game.gameId + '/order', payment);
                })
                .then(function (payment) {
                    console.log(payment);
                    console.log('successfully submitted payment for $', payment.data.amount/100);
                    $uibModalInstance.close();
                })
                .catch(function (err) {
                    if (err.type && /^Stripe/.test(err.type)) {
                        console.log('Stripe error: ', err.message);
                    }
                    else {
                        console.log('Other error occurred, possibly with your API', err.message);
                    }
                });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }]);