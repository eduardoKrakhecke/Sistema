app.factory("tokenInterceptor", function($q, $location) {
	return {
		'request' : function(config) {			
			config.headers.Authorization = 'Bearer ' + getCookie("userToken");
			
			/*idEntidadeSelecionada = decodeURIComponent($location.$$search.dataBase);
			config.headers.DataBase = idEntidadeSelecionada;*/
			config.headers.Page = $location.$$path.substring(1, $location.$$path.lenght);			
			
			return config;
		},
		'responseError' : function(rejection) {			
			if (rejection.status === 500) {
				if (rejection.data.message === "Token inexiste ou inválido") {
					location.href = "/";
				}
			}
			return $q.reject(rejection);
		},

		'response' : function(response) {
			return response;
		}

	};

});