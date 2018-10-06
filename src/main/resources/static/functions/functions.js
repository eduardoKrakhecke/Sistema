function carregaImagem() {
    readURL = function (input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#imagem').attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        }
    };
    $("input[name='imagem']").click();
}

montaAutoComplete = function () {

};