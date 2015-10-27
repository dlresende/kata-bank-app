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
                    $http.put('/api/account', {client: client});
                    $scope.newClient = {username: ''};
                });
            }
        };
    })
    .controller('AccountsCtrl', function($scope, $http, $routeParams) {
        $scope.transactions = [{amount: undefined, type: undefined}];

        $scope.getAccountBy = function(username) {
            $http.get('/api/account/' + username)
                .success(function(account) {
                    $scope.account = account;
                });
        };

        $scope.updateAccount = function() {
            $scope.account.transactions.transactions = $scope.transactions;

            $http.put('/api/account/', $scope.account).success(function() {
                $scope.getAccountBy($routeParams.username);
            });
        };

        if($routeParams.username) {
            $scope.getAccountBy($routeParams.username);
        }

        $scope.newTransaction = function() {
            $scope.addTransaction({amount: 0, type: 'DEPOSIT'});
        };

        $scope.addTransaction = function(transaction) {
            $scope.transactions.push(transaction);
        };
    });
