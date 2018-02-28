'use strict';

webApp.controller('sudokuController', sudokuController);

sudokuController.$inject = ['$scope','sudokuService'];

function sudokuController($scope, sudokuService) {

	var canvasId = 'sudokuBoard';
	var canvasContext = '2d';
	
	var canvas = document.getElementById(canvasId);
	var context = canvas.getContext(canvasContext);
	canvas.width = 800;
	canvas.height = 600;
	
	sudokuService.postRequestForRandomSudokuBoard().then(function(success) {
		var initialBoardStates = success.data;
		paintSudokuBoard(initialBoardStates.boardStates, context);
	}, function(optional) { 
		console.log(optional);
	});
	
	
	function paintGameOfLifeBoard(board, context) {
//		for(var x=0; x<board.length; x++) {
//			for (var y=0; y<board[x].length; y++) {
//				context.fillStyle=board[x][y] == 0 ? colorDead : colorAlive;
//				context.fillRect(x*pix,y*pix,pix,pix);
//			}
//		}
//		context.stroke();
	}
};

