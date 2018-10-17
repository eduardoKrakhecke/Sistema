var app = angular.module("app", ["ngRoute","ui.router","ngCookies","ngMaterial","md.data.table", "fixed.table.header","infinite-scroll"]).value ('THROTTLE_MILLISECONDS', 1500);
app.config(function($routeProvider, $locationProvider){
    $routeProvider
        .when("/usuario", {templateUrl:'/usuario/listaUsuario.html'})
        .when("/produto", {templateUrl:'/module/listaProdutoComponent/listaProduto.html'})
        .when("/cliente", {templateUrl:'/module/listaClienteComponent/listaCliente.html'})
        .when("/estoque", {templateUrl:'/module/listaEstoqueComponent/listaEstoque.html'})
        .otherwise({redirect:"/"});

    $locationProvider.html5Mode(false);
});


