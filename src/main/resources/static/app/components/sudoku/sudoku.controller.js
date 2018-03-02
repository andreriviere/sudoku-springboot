'use strict';

webApp.controller('sudokuController', sudokuController);

sudokuController.$inject = ['$scope','sudokuService'];

function sudokuController($scope, sudokuService) {

	var board;
	var backup;
	var intervalDelay = 10;
	
	
	sudokuService.postRequestForRandomSudokuBoard('hard').then(function(success) {
		board = success.data;
		backup = JSON.parse(JSON.stringify(board));
		redrawBoard(board.sudokuBoardCells);
		var isDone = true;
		var error = false;
		var runningInterval = setInterval(function(){
			if (isDone == true) 
			{
				isDone = false;
				sudokuService.postRequestForGenerateNextSolution(board).then(function(success) {
				var cell = success.data;
				console.log(cell);
				if (!cell) {
					clearInterval(runningInterval);
					return;
				}
				board.sudokuBoardCells[cell.x][cell.y] = cell.value;
				redrawBoard(board.sudokuBoardCells);
				isDone = true;
			}).catch(function (err) {
				console.log('error');// error
				board = JSON.parse(JSON.stringify(backup));
				isDone = true;
			});
			}
		}, intervalDelay);
		
		
		
	}, function(optional) { 
		console.log(optional);
	});
	
	
	
	
	function redrawBoard(sudokuBoardCells) {
		var emptyClassesList = document.getElementsByClassName("sudokuCell");
		var filledClassesList = document.getElementsByClassName("sudokuCellFilled");
		console.log(sudokuBoardCells.length);
		for (var x=0; x<sudokuBoardCells.length; x++)
		{
			for (var y=0; y<sudokuBoardCells[x].length; y++)
			{
				var cellDiv = Array.prototype.filter.call(emptyClassesList, function(doc){
					return doc.attributes.x.value===""+(x+1) && doc.attributes.y.value===""+(y+1);
				});
				if (cellDiv.length == 0) {
					cellDiv = Array.prototype.filter.call(filledClassesList, function(doc){
						return doc.attributes.x.value===""+(x+1) && doc.attributes.y.value===""+(y+1);
					});
				}

				cellDiv[0].className = sudokuBoardCells[x][y] != 0 ? 'sudokuCellFilled' : 'sudokuCell';
				cellDiv[0].innerHTML = sudokuBoardCells[x][y] != 0 ? sudokuBoardCells[x][y] : '';
			}
		}
	}
};

