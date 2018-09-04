var app = angular.module("app", ["ngRoute"]);
app.controller("loginController", function ($http, $location, $q) {
    var vm = this;
    vm.usuario = {};

    vm.autenticar = function () {
        $http.post("/autenticar", vm.usuario).then(function (response) {
                $q.when();
                location.href = "home#!/?parametros=";
            },
            function (response) {
                notif({'type':'error','msg':'Usuário ou Senha inválida','position': 'center'})
            });
    };
});