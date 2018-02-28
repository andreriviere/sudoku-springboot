package wt.sudoku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wt.sudoku.generator.SudokuBoardGenerator;
import wt.sudoku.generator.SudokuLevel;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.utils.SudokuBoardTextPrinter;

@Controller
public class SudokuController {

	private SudokuBoardGenerator sudokuBoardGenerator;
	
	@Autowired
	public SudokuController(SudokuBoardGenerator sudokuBoardGenerator) {
		this.sudokuBoardGenerator = sudokuBoardGenerator;
	}

	@RequestMapping("/")
	public String getSudokuBoard(Model model) {
		BoardPlay boardPlay = sudokuBoardGenerator.generateSolvableBoard(SudokuLevel.EASY);
		SudokuBoardTextPrinter.printSudokuBoard(boardPlay.getSudokuCells());
		return "index";
	}
}
