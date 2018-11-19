# Sudoku Spring Boot Application

Server is hosted on Heroku: https://sudoku-generator-app.herokuapp.com/sudoku/

API:
POST: url: "api/randomBoard"; body: String level - "Easy, Medium, Hard"; respone: int[][] sudokuBoardCells;
POST: url: "api/generateNextSolution"; body: int[][] sudokuBoardCells; response: {int x, y, value}
POST: url: "api/checkIsBoardSolvable"; body: int[][] sudokuBoardCells; response: boolean

# List of github project using the server:
1. Angular 7 client : https://github.com/rissekwow/sudoku-client-angular7
URL: https://sudoku-generator-app.herokuapp.com/sudoku/
