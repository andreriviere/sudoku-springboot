package generator;

import java.util.Random;

import org.springframework.stereotype.Service;

import model.Board;

@Service(value = "lowLevelSudokuGenerator")
public class LowLevelSudokuGenerator implements SudokuBoardGenerator {

	private Random randomGenerator;

	public LowLevelSudokuGenerator() {
		randomGenerator = new Random();
	}
	
	

	@Override
	public Board generateSolvableBoard() {
		Board emptyBoard = new Board();
		int valuesToSet = 70;
		for (int i = 0; i < valuesToSet; i++) {
			emptyBoard = generateRandomValue(emptyBoard, 0);
		}
		
		return emptyBoard;
	}

	private Board generateRandomValue(Board emptyBoard, int counter) {
		if (counter==100) return emptyBoard;
		int randomX = randomGenerator.nextInt(9);
		int randomY = randomGenerator.nextInt(9);
		int randomValue = randomGenerator.nextInt(9) + 1;
		if (emptyBoard.getSudokuCells()[randomX][randomY].getValue() == 0
				&& emptyBoard.isValueAcceptableToCell(randomX, randomY, randomValue) && emptyBoard.acceptablePossibilities(randomX, randomY) > 1) {
			emptyBoard.addValueToCell(randomX, randomY, randomValue);
		} else {
			generateRandomValue(emptyBoard, ++counter);
		}
		return emptyBoard;
	}

}
