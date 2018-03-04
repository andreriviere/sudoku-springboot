package wt.sudoku.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rits.cloning.Cloner;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;
import wt.sudoku.utils.BoardStatisticsHelper;
import wt.sudoku.utils.SudokuBoardTextPrinter;

@Service("sudokuSolverUsingReturnAlgorithm")
@Scope("prototype")
public class SudokuSolverUsingReturnAlgorithm implements SudokuSolverAlgorithm{

	class ClonedBoard{
		private Board board;
		private Cell cell;
		private List<Integer> anotherPossibilities;
		
		public Board getBoard() {
			return board;
		}
		public void setBoard(Board board) {
			this.board = board;
		}
		public Cell getCell() {
			return cell;
		}
		public void setCell(Cell cell) {
			this.cell = cell;
		}
		public List<Integer> getAnotherPossibilities() {
			return anotherPossibilities;
		}
		public void setAnotherPossibilities(List<Integer> anotherPossibilities) {
			this.anotherPossibilities = anotherPossibilities;
		}
		
	}
	
	private Random randomGenerator;

	public SudokuSolverUsingReturnAlgorithm() {
		this.randomGenerator = new Random();
	}

	public Board solveSudokuBoard(Board enterBoard, int returnLimit) {
	
		List<ClonedBoard> clonedBoardToReturn = new ArrayList<ClonedBoard>();
		
		while (BoardStatisticsHelper.calculateFilledFields(enterBoard) != 81) {
			
			enterBoard.fillListWithValidValuesPerEachCell();

			Optional<Cell> cellWithoutPossibilitiesToSet = Arrays.stream(enterBoard.getSudokuCells()).flatMap(Arrays::stream)
					.filter(c -> c.getValue() == 0 && c.getAvailableValues().size() == 0).findFirst();
			if (cellWithoutPossibilitiesToSet.isPresent()) {
				if (clonedBoardToReturn.isEmpty()) throw new RuntimeException("Board is unsololvable or current algorithm isn't good enought to solve it");
				ClonedBoard goBackBoard = clonedBoardToReturn.get(clonedBoardToReturn.size()-1);
				if (goBackBoard.getAnotherPossibilities().isEmpty()) {
					clonedBoardToReturn.remove(clonedBoardToReturn.size()-1);
					enterBoard.deleteValue(goBackBoard.getCell().getCellParams().getX(),goBackBoard.getCell().getCellParams().getY());
					continue;
				}
				
				goBackBoard.getBoard().deleteValue(goBackBoard.getCell().getCellParams().getX(), goBackBoard.getCell().getCellParams().getY());
				int nextValue = goBackBoard.getAnotherPossibilities().get(randomGenerator.nextInt(goBackBoard.getAnotherPossibilities().size()));
				goBackBoard.getBoard().addValueToCell(goBackBoard.getCell().getCellParams().getX(), goBackBoard.getCell().getCellParams().getY(), 
						nextValue);
				goBackBoard.getAnotherPossibilities().remove(Integer.valueOf(nextValue));
				clonedBoardToReturn.remove(clonedBoardToReturn.size()-1);
				clonedBoardToReturn.add(goBackBoard);
				enterBoard = goBackBoard.getBoard();
				continue;
			}
					
			Optional<Cell> cellWithOnePossibilityToSet = Arrays.stream(enterBoard.getSudokuCells()).flatMap(Arrays::stream)
					.filter(c -> c.getValue() == 0 && c.getAvailableValues().size() == 1).findFirst();
			if (cellWithOnePossibilityToSet.isPresent()) {
				Cell cell = cellWithOnePossibilityToSet.get();
				enterBoard.addValueToCell(cell.getCellParams().getX(), cell.getCellParams().getY(), cell.getAvailableValues().get(0));
				continue;
			}

			
			Optional<Cell> cellWithMoreThanOnePossibility = Arrays.stream(enterBoard.getSudokuCells()).flatMap(Arrays::stream)
					.filter(c -> c.getValue() == 0 && c.getAvailableValues().size() > 1)
					.sorted((c1, c2) -> Integer.compare(c1.getAvailableValues().size(),c2.getAvailableValues().size())).findFirst();
			if (!cellWithMoreThanOnePossibility.isPresent()) 
			{
				throw new RuntimeException("That situation shouldn't appear. Bug!");
			}
			Cell cell = cellWithMoreThanOnePossibility.get();
			List<Integer> possibilities = cell.getAvailableValues();
			int randomValue = possibilities.get(randomGenerator.nextInt(possibilities.size()));
			possibilities.remove(Integer.valueOf(randomValue));
			enterBoard.addValueToCell(cell.getCellParams().getX(), cell.getCellParams().getY(), randomValue);
			ClonedBoard clonedBoard = new ClonedBoard();
			Cloner cloner = new Cloner();
			clonedBoard.setBoard(cloner.deepClone(enterBoard));
			clonedBoard.setCell(cell);
			clonedBoard.setAnotherPossibilities(possibilities);
			clonedBoardToReturn.add(clonedBoard);
		}

		return enterBoard;

	}

	@Override
	public Cell findNextSolution(Board enterBoard) {
		enterBoard.fillListWithValidValuesPerEachCell();
		Board solved = solveSudokuBoard(enterBoard.cloneBoard(), 0);
		SudokuBoardTextPrinter.printSudokuBoard(solved.getSudokuCells());
		Optional<Cell> cellWithPossibilitiesOrderAsc = Arrays.stream(enterBoard.getSudokuCells()).flatMap(Arrays::stream)
				.filter(c -> c.getValue() == 0)
				.sorted((c1, c2) -> Integer.compare(c1.getAvailableValues().size(),c2.getAvailableValues().size())).findFirst();
		if (!cellWithPossibilitiesOrderAsc.isPresent()) return null;
		return solved.getSudokuCells()[cellWithPossibilitiesOrderAsc.get().getCellParams().getX()][cellWithPossibilitiesOrderAsc.get().getCellParams().getY()];
	}
}
