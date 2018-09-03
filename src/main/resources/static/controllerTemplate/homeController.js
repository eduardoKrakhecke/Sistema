
app.controller("homeController", function ($http, $location, $q, $cookies, usuario) {

    var vm = this;
    vm.usuario = {};

    usuario.getUsuario().then(function (retorno) {
        vm.usuario.fotoUsuario = retorno.fotoUsuario;
        vm.imagemUsuario = vm.usuario.fotoUsuario || './image/semfoto.png';
    });

    if ($cookies.get("userToken") === undefined) {
        location.href = "/";
    }

    vm.sair = function () {
        $cookies.remove("userToken");
        location.href = "/";
    };

    vm.goToPagina = function (pagina) {
        $location.path(pagina);
    };

});