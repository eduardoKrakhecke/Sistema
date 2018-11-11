app.service('mensagem', function($mdToast) {
var vm = this;
    var last = { bottom: false,   top: true,   left: false,  right: true  };

    vm.toastPosition = angular.extend({},last);

    vm.getToastPosition = function() {
        sanitizePosition();

        return Object.keys(vm.toastPosition)
            .filter(function(pos) { return vm.toastPosition[pos]; })
            .join(' ');
    };

    function sanitizePosition() {
        var current = vm.toastPosition;
        if ( current.bottom && last.top ) current.top = false;
        if ( current.top && last.bottom ) current.bottom = false;
        if ( current.right && last.left ) current.left = false;
        if ( current.left && last.right ) current.right = false;
        last = angular.extend({},current);
    }

    this.mensagemSucesso = function (mensagem) {
            $mdToast.show(
                $mdToast.simple()
                    .textContent(mensagem)
                    .position(vm.getToastPosition())
                    .hideDelay(3000)
            );
    };

   this.mensagemConfirmacaoExclusao = function(registro,callback) {
        bootbox.confirm({
            message: "Ao continuar registro "+registro+ " será excluído permanentemente",
            buttons: {
                confirm: {
                    label: 'Excluir',
                    className: 'btn-danger btn-sm'
                },
                cancel: {
                    label: 'Cancelar',
                    className: 'btn-blue-grey btn-sm'
                }
            },
            callback: function (result) {
                callback(result);
            }
        });
    };


    this.mensagemErroUsuario = function () {
        var dialog = bootbox.dialog({
            title: 'Erro de autenticação', message: 'Você será redicerionado para tela de login.', closeButton: false
        });
        dialog.init(function(){
            setTimeout(function(){location.href = "/";}, 3000);
        });
    };

});
