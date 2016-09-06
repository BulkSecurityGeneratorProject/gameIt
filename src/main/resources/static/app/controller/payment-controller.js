/**
 * Created by Stefan on 31.8.2016.
 */
gameItAngularApp.controller('PaymentController', ['$scope','toastr','$http', 'stripe', 'param', '$uibModalInstance', '$rootScope', '$translate', '$translatePartialLoader', 'CredentialsService', '$state', '$stateParams',
    function ($scope, toastr, $http, stripe, param, $uibModalInstance, $rootScope, $translate, $translatePartialLoader, CredentialsService, $state, $stateParams) {

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
                .then(function (response) {
                    console.log(response);
                    var fileName = "order-report.pdf";
                    var a = document.createElement("a");
                    document.body.appendChild(a);
                    a.style = "display: none";
                    $http({
                        method: 'GET',
                        url: 'report/order/'+response.data.orderId,
                        headers: {
                            'Content-type': 'application/pdf'
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
                    $translate('games.game.buyComplete').then(function (translatedMessage) {
                        toastr.success(translatedMessage, {
                            closeButton: true,
                            allowHtml: true
                        });
                    });
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