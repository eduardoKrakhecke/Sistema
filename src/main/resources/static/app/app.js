var app = angular.module("app", ["ngRoute","ui.router","ngCookies"]);
app.config(function($routeProvider, $locationProvider){
    $routeProvider
        .when("/usuario", {templateUrl:'/usuario/listaUsuario.html'})
        .when("/produto", {templateUrl:'/module/listaProdutoComponent/listaProduto.html'})
        .when("/cliente", {templateUrl:'/module/listaClienteComponent/listaCliente.html'})
        .otherwise({redirect:"/"});

    $locationProvider.html5Mode(false);
});
