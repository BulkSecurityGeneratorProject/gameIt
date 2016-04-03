/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('AdminMetricsController', ['$scope', '$uibModal', 'MetricsService', '$translate','$translatePartialLoader',
    function ($scope, $uibModal, MetricsService,$translate,$translatePartialLoader) {
        $translatePartialLoader.addPart('metrics');
        $translate.refresh();

        $scope.cachesStats = {};
        $scope.metrics = {};
        $scope.refresh = refresh;
        $scope.refreshThreadDumpData = refreshThreadDumpData;
        $scope.servicesStats = {};
        $scope.updatingMetrics = true;
        $scope.refresh();
        $scope.$watch('$scope.metrics', function (newValue) {
            $scope.servicesStats = {};
            $scope.cachesStats = {};
            angular.forEach(newValue.timers, function (value, key) {
                if (key.indexOf('web.rest') !== -1 || key.indexOf('service') !== -1) {
                    $scope.servicesStats[key] = value;
                }
                if (key.indexOf('net.sf.ehcache.Cache') !== -1) {
                    var index = key.lastIndexOf('.');
                    var newKey = key.substr(0, index);
                    index = newKey.lastIndexOf('.');
                    $scope.cachesStats[newKey] = {
                        'name': newKey.substr(index + 1),
                        'value': value
                    };
                }
            });
        });
        function refresh() {
            $scope.updatingMetrics = true;
            MetricsService.getMetrics().then(function (promise) {
                $scope.metrics = promise;
                $scope.updatingMetrics = false;
            }, function (promise) {
                $scope.metrics = promise.data;
                $scope.updatingMetrics = false;
            });
        }


         function refreshThreadDumpData() {
      /*   MetricsService.threadDump().then(function (data) {
         $uibModal.open({
         templateUrl: 'app/admin/metrics/metrics.modal.html',
         controller: 'JhiMetricsMonitoringModalController',
         controllerAs: 'vm',
         size: 'lg',
         resolve: {
         threadDump: function () {
         return data;
         }

         }
         });
         });*/
         }

    }]);
