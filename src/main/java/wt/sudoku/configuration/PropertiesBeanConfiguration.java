package wt.sudoku.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wt.sudoku.generator.SudokuBoardGenerator;
import wt.sudoku.solver.SudokuSolverAlgorithm;

@Configuration
public class PropertiesBeanConfiguration {

	@Autowired
	private ApplicationContext context;

	@Bean
	public SudokuBoardGenerator getSudokuBoardGenerator(@Value("${sudoku.generator}") String sudokuGenerator) {
		return (SudokuBoardGenerator) context.getBean(sudokuGenerator);
	}
	
	@Bean
	public SudokuSolverAlgorithm getSudokuSolverAlgorithm(@Value("${sudoku.algorithm}") String sudokuAlgorithm) {
		return (SudokuSolverAlgorithm) context.getBean(sudokuAlgorithm);
	}
}
