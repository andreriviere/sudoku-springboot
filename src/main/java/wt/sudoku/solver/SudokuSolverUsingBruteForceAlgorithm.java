package wt.sudoku.solver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rits.cloning.Cloner;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;
import wt.sudoku.utils.BoardStatisticsHelper;

@Service("sudokuSolverUsingBruteForceAlgorithm")
public class SudokuSolverUsingBruteForceAlgorithm implements SudokuSolverAlgorithm{

	private Random randomGenerator;

	public SudokuSolverUsingBruteForceAlgorithm() {
		this.randomGenerator = new Random();
	}

	public Board solveSudokuBoard(Board enterBoard, int returnLimit) {
		Cloner cloner = new Cloner();
		Board clonedBoard = cloner.deepClone(enterBoard);
		if (returnLimit == 0) 
		{
			System.out.println("Limited");
			return enterBoard;
		}
		while (BoardStatisticsHelper.calculateFilledFields(enterBoard) != 81) {
			enterBoard.fillListWithValidValuesPerEachCell();
			boolean isAdded = false;
			
			for (int y = 0; y < enterBoard.getSudokuCells().length; y++) {
				for (int x = 0; x < enterBoard.getSudokuCells()[y].length; x++) {
					if (enterBoard.getSudokuCells()[x][y].getValue() == 0
							&& enterBoard.getSudokuCells()[x][y].getAvailableValues().size() == 1) {
						enterBoard.addValueToCell(x, y, enterBoard.getSudokuCells()[x][y].getAvailableValues().get(0));
						isAdded = true;
						break;
					}
				}
				if (isAdded)
					break;
			}

			if (isAdded)
				continue;

			for (int y = 0; y < enterBoard.getSudokuCells().length; y++) {
				for (int x = 0; x < enterBoard.getSudokuCells()[y].length; x++) {
					if (enterBoard.getSudokuCells()[x][y].getValue() == 0
							&& enterBoard.getSudokuCells()[x][y].getAvailableValues().size() == 2) {
						enterBoard.addValueToCell(x, y,
								enterBoard.getSudokuCells()[x][y].getAvailableValues().get(randomGenerator.nextInt(2)));
						isAdded = true;
						break;
					}
				}
				if (isAdded)
					break;
			}
			if (isAdded)
				continue;

			for (int y = 0; y < enterBoard.getSudokuCells().length; y++) {
				for (int x = 0; x < enterBoard.getSudokuCells()[y].length; x++) {
					if (enterBoard.getSudokuCells()[x][y].getValue() == 0
							&& enterBoard.getSudokuCells()[x][y].getAvailableValues().isEmpty()) {
						return solveSudokuBoard(clonedBoard, --returnLimit);
					}
					if (enterBoard.getSudokuCells()[x][y].getValue() == 0) {
						enterBoard.addValueToCell(x, y,
								enterBoard.getSudokuCells()[x][y].getAvailableValues().get(randomGenerator
										.nextInt(enterBoard.getSudokuCells()[x][y].getAvailableValues().size())));
						isAdded = true;
						break;
					}
				}
				if (isAdded)
					break;
			}
			if (isAdded)
				continue;
		}

		return enterBoard;

	}

	@Override
	public Cell findNextSolution(Board enterBoard) {
		enterBoard.fillListWithValidValuesPerEachCell();
		List<Cell> cellToList = Arrays.stream(enterBoard.getSudokuCells()).flatMap(Arrays::stream)
				.filter(c -> c.getValue() == 0)
				.sorted((c1, c2) -> Integer.compare(c1.getAvailableValues().size(), c2.getAvailableValues().size()))
				.collect(Collectors.toList());
		if (cellToList.isEmpty()) return null;
		Random random = new Random();
		cellToList.get(0).addValue(cellToList.get(0).getAvailableValues().get(random.nextInt(cellToList.get(0).getAvailableValues().size())));
		return cellToList.get(0);
	}
}
