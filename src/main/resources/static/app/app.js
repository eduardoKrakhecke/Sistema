var app = angular.module("app", ["ngRoute","ui.router","ngCookies","ngMaterial","md.data.table", "rw.moneymask"]);
app.config(function($routeProvider, $locationProvider){
    $routeProvider
        .when("/cliente", {templateUrl:'/module/listaClienteComponent/listaCliente.html'})
        .otherwise({redirect:"/"});

    $locationProvider.html5Mode(false);
});


