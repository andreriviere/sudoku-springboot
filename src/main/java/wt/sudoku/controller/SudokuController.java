package wt.sudoku.controller;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rits.cloning.Cloner;

import wt.sudoku.command.BoardCommand;
import wt.sudoku.command.CellCommand;
import wt.sudoku.command.MessageCommand;
import wt.sudoku.converter.BoardCommandToBoardPlayConverter;
import wt.sudoku.converter.BoardToBoardCommandConverter;
import wt.sudoku.converter.CellToCellCommandConverter;
import wt.sudoku.generator.SudokuBoardGenerator;
import wt.sudoku.generator.SudokuLevel;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.board.main.Cell;
import wt.sudoku.utils.SudokuBoardTextPrinter;

@Controller
public class SudokuController {

	private final String CROSS_ORIGIN = "*";
	private SudokuBoardGenerator sudokuBoardGenerator;
	private BoardCommandToBoardPlayConverter boardCommandToBoardPlayConverter;
	private BoardToBoardCommandConverter boardToBoardCommandConverter;
	private CellToCellCommandConverter cellToCellCommandConverter;

	@Autowired
	public SudokuController(@Qualifier("getSudokuBoardGenerator") SudokuBoardGenerator sudokuBoardGenerator,
			BoardToBoardCommandConverter boardToBoardCommandConverter,
			BoardCommandToBoardPlayConverter boardCommandToBoardPlayConverter,
			CellToCellCommandConverter cellToCellCommandConverter) {
		this.sudokuBoardGenerator = sudokuBoardGenerator;
		this.boardToBoardCommandConverter = boardToBoardCommandConverter;
		this.boardCommandToBoardPlayConverter = boardCommandToBoardPlayConverter;
		this.cellToCellCommandConverter = cellToCellCommandConverter;
	}

	@RequestMapping("/")
	public String getIndexPage(Model model) {
		return "index";
	}

	@RequestMapping(value = "api/randomBoard", method = RequestMethod.POST)
	@CrossOrigin(origins = CROSS_ORIGIN)
	@ResponseBody
	public BoardCommand generateRandomBoard(@RequestBody String level) {
			
		Optional<SudokuLevel> sudokuLevel = Optional.of(SudokuLevel.valueOf(level.toUpperCase()));
		if (sudokuLevel.isPresent()) {
			BoardPlay boardPlay = sudokuBoardGenerator.generateSolvableBoard(sudokuLevel.get());
			SudokuBoardTextPrinter.printSudokuBoard(boardPlay.getSudokuCells());
			return boardToBoardCommandConverter.convert(boardPlay);
		} else {
			throw new InvalidParameterException(
					"Level parameter should have one of possible results [easy, medium, hard]");
		}

	}

	@RequestMapping(value = "api/generateNextSolution", method = RequestMethod.POST)
	@CrossOrigin(origins = CROSS_ORIGIN)
	@ResponseBody
	public CellCommand findNextMoveSolution(@RequestBody BoardCommand boardCommand) {
		BoardPlay boardPlay = boardCommandToBoardPlayConverter.convert(boardCommand);
		SudokuBoardTextPrinter.printSudokuBoard(boardPlay.getSudokuCells());
		return tryToSolve10Times(boardPlay);
	}

	private CellCommand tryToSolve10Times(BoardPlay boardPlay) {
		int repeatTime = 10;
		while (repeatTime > 0) {
			try {
				Cloner cloner = new Cloner();
				BoardPlay clonedBoard = cloner.deepClone(boardPlay);
				Cell solution = clonedBoard.findNextSolution();
				if (clonedBoard.isBoardSolvable())	
					return cellToCellCommandConverter.convert(solution);
				repeatTime -= 1;
			} catch (RuntimeException r) {
				repeatTime -= 1;
			}
		}
		throw new RuntimeException("Board can't be solved");
	}

	@RequestMapping(value = "api/checkIsBoardSolvable", method = RequestMethod.POST)
	@CrossOrigin(origins = CROSS_ORIGIN)
	@ResponseBody
	public boolean checkIsBoardSolvable(@RequestBody BoardCommand boardCommand) {
		BoardPlay boardPlay = boardCommandToBoardPlayConverter.convert(boardCommand);
		return boardPlay.isBoardSolvable();
	}

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<MessageCommand> handleEmptyNecessarilyFieldException(RuntimeException ree) {
		MessageCommand message = new MessageCommand();
		message.setMessage(ree.getMessage());
		return new ResponseEntity<MessageCommand>(message, HttpStatus.NOT_ACCEPTABLE);
	}

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<MessageCommand> handleInvalidParameterExceptionException(InvalidParameterException ipe) {
		MessageCommand message = new MessageCommand();
		message.setMessage(ipe.getMessage());
		return new ResponseEntity<MessageCommand>(message, HttpStatus.NOT_ACCEPTABLE);
	}

}
