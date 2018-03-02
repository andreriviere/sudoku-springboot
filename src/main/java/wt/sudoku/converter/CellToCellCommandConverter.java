package wt.sudoku.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wt.sudoku.command.CellCommand;
import wt.sudoku.model.board.main.Cell;

@Component
public class CellToCellCommandConverter implements Converter<Cell, CellCommand> {

	@Override
	public CellCommand convert(Cell cell) {
		if (cell == null) return null;
		CellCommand cellCommand = new CellCommand();
		cellCommand.setX(cell.getCellParams().getX());
		cellCommand.setY(cell.getCellParams().getY());
		cellCommand.setValue(cell.getValue());
		return cellCommand;
	}

}
