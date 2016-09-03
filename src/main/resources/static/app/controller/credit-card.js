/**
 * Created by Stefan on 31.8.2016.
 */
gameItAngularApp.controller('CreditCardController', ['$scope', 'toastr', '$http', 'stripe', 'param', '$uibModalInstance', '$rootScope', '$translate', '$translatePartialLoader', 'CredentialsService', '$state', '$stateParams',
    function ($scope, toastr, $http, stripe, param, $uibModalInstance, $rootScope, $translate, $translatePartialLoader, CredentialsService, $state, $stateParams) {

        $scope.games = {};
        $scope.games = param.data;
        $scope.payment = {};
        $scope.payment.card = {};
        $scope.payment.card.number = 4242424242424242;
        $scope.payment.card.exp_month = 8;
        $scope.payment.card.exp_year = 2017;
        console.log($scope.games);

        $scope.charge = function (callback) {
            var ammount = 0;
            for (var i = 0; i < $scope.games.length; i++) {
                var game = $scope.games[i];
                var buy = function (game, i) {
                    stripe.card.createToken($scope.payment.card)
                        .then(function (response) {
                            console.log('token created for card ending in ', response.card.last4);
                            var payment = angular.copy($scope.payment);
                            payment.card = void 0;
                            payment.token = response.id;
                            ammount += game.gamePrice;
                            $http({
                                method: 'POST',
                                url: 'api/games/' + game.gameId + '/order',
                                data: payment
                            }).then(function success(response) {
                                if (i == $scope.games.length - 1) {
                                    $translate('games.game.buyComplete').then(function (translatedMessage) {
                                        toastr.success(translatedMessage, {
                                            closeButton: true,
                                            allowHtml: true
                                        });
                                    });
                                    $uibModalInstance.close($scope.games);
                                }
                            });
                            return ammount
                        })
                        .then(function (ammount) {
                            console.log(ammount);
                            console.log('successfully submitted payment for $', ammount);
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
                buy(game, i);
            }
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel', $scope.games);
        };
    }]);