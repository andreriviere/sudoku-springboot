'use strict';

webApp.service('sudokuService', sudokuService);

sudokuService.$inject = [ '$http' ];

function sudokuService($http) {
	var ctrl = this;
	
	ctrl.postRequestForRandomSudokuBoard = function() {
		return $http({
			url : 'api/randomBoard',
			method : "POST",
			data : null
		});
	}
	
	ctrl.postRequestForUpdateSudokuBoard = function(boardCommand) {
		return $http({
			url : 'api/updateBoard',
			method : "POST",
			data : boardCommand
		});
	}
};

