'use strict';

webApp.service('sudokuService', sudokuService);

sudokuService.$inject = [ '$http' ];

function sudokuService($http) {
	var ctrl = this;
	
	ctrl.postRequestForRandomSudokuBoard = function(level) {
		return $http({
			url : 'api/randomBoard',
			method : "POST",
			data : level
		});
	}
	
	ctrl.postRequestForGenerateNextSolution = function(board) {
		return $http({
			url : 'api/generateNextSolution',
			method : "POST",
			data : board
		});
	}
};

