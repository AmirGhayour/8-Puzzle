
public final class Heuristic {

	public static int heuristic(Puzzle puzzle) {
		
		int[][] p = puzzle.getPuzzle();
		int n = puzzle.getN();
		
		int heuristic, number, row, column;
		heuristic = 0;
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {

				number = p[i][j];

				if (number == 0) {
					heuristic += Math.abs(n - 1 - i) + Math.abs(n - 1 - j);
					continue;
				}

				row = number / n - 1;
				column = number % n;

				if (column == 0)
					heuristic += Math.abs(row - i) + Math.abs(n - 1 - j);
				else
					heuristic += Math.abs(row + 1 - i) + Math.abs(column - 1 - j);
			}
		return heuristic;
	}

}
