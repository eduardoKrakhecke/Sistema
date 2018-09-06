var app = angular.module("app", ["ngRoute","ui.router","ngCookies",'ui.grid','ui.grid.pagination','ui.grid.autoResize']);
app.config(function($routeProvider, $locationProvider){
    $routeProvider
        .when("/usuario", {templateUrl:'/usuario/listaUsuario.html'})
        .when("/produto", {templateUrl:'/module/listaProdutoComponent/listaProduto.html'})
        .when("/cliente", {templateUrl:'/module/listaClienteComponent/listaCliente.html'})
        .otherwise({redirect:"/"});

    $locationProvider.html5Mode(false);
});

app.config(function($provide) {
    $provide.decorator('GridOptions', ['$delegate', 'i18nService', function ($delegate, i18nService) {
        var gridOptions;
        gridOptions = angular.copy($delegate);
        gridOptions.initialize = function (options) {
            var initOptions;
            initOptions = $delegate.initialize(options);
            return initOptions;
        };
        i18nService.setCurrentLang('pt-br');
        return gridOptions;
    }]);
});