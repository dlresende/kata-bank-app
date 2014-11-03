'use strict';

angular.module('bankApp')
    .controller('ClientsCtrl', function($scope, $http) {
        $scope.newClient = {username: ''};

        $scope.listClients = function() {
            $http.get('/api/client')
                .success(function(clients) {
                    $scope.clients = clients;
                });
        };
        $scope.listClients();

        $scope.addClient = function() {
            var username = $scope.newClient.username;
            if(username) {
                var client = $scope.newClient;
                $http.put('/api/client/' + username, client).success(function() {
                    $scope.listClients();
                    $http.put('/api/account', client);
                    $scope.newClient = {username: ''};
                });
            }
        };
    })
    .controller('AccountsCtrl', function($scope, $http, $routeParams) {
        $scope.getAccountBy = function(username) {
            $http.get('/api/account/' + username)
                .success(function(account) {
                    $scope.account = account;
                });
        };

        if($routeParams.username) {
            $scope.getAccountBy($routeParams.username);
        }
    });
