import java.util.Random;

public class Puzzle {

	private int[][] puzzle;
	private int n;
	private int zeroRow, zeroColumn;
	private Puzzle parent;

	// *************************************************************** instructor
	public Puzzle(int n) {
		this.n = n;
		zeroRow = n - 1;
		zeroColumn = n - 1;
		puzzle = new int[n][n];
	}

	public Puzzle(int n, Puzzle parent) {
		this.n = n;
		zeroRow = n - 1;
		zeroColumn = n - 1;
		puzzle = new int[n][n];
		this.parent = parent;
	}

	public Puzzle(int n, int[][] puzzle, int zeroRow, int zeroColumn) {
		if (puzzle[zeroRow][zeroColumn] != 0) {
			System.out.println("Wrong ZeroRow and ZeroColumn");
			return;
		}
		this.n = n;
		this.zeroRow = zeroRow;
		this.zeroColumn = zeroColumn;
		this.puzzle = puzzle;
	}

	// *************************************************************** Create puzzle
	public void create() {
		int c = 1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++, c++)
				puzzle[i][j] = c;
		puzzle[n - 1][n - 1] = 0;
	}

	// *************************************************************** Mix puzzle
	public void mix(int moveNum) {
		for (int i = 0; i < moveNum; i++) {
			Random random = new Random();
			try {
				switch (random.nextInt(4)) {
				case 0:
					moveZeroToUp();
					break;
				case 1:
					moveZeroToDown();
					break;
				case 2:
					moveZeroToLeft();
					break;
				case 3:
					moveZeroToRight();
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	// *************************************************************** Copy
	public void copyOf(Puzzle puzzle) {
		int[][] p = puzzle.getPuzzle();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				this.puzzle[i][j] = p[i][j];
		zeroRow = puzzle.zeroRow;
		zeroColumn = puzzle.zeroColumn;
	}

	// *************************************************************** Move Zero
	public void moveZeroToUp() {
		puzzle[zeroRow][zeroColumn] = puzzle[zeroRow - 1][zeroColumn];
		puzzle[zeroRow - 1][zeroColumn] = 0;
		zeroRow--;
	}

	public void moveZeroToDown() {
		puzzle[zeroRow][zeroColumn] = puzzle[zeroRow + 1][zeroColumn];
		puzzle[zeroRow + 1][zeroColumn] = 0;
		zeroRow++;
	}

	public void moveZeroToLeft() {
		puzzle[zeroRow][zeroColumn] = puzzle[zeroRow][zeroColumn - 1];
		puzzle[zeroRow][zeroColumn - 1] = 0;
		zeroColumn--;
	}

	public void moveZeroToRight() {
		puzzle[zeroRow][zeroColumn] = puzzle[zeroRow][zeroColumn + 1];
		puzzle[zeroRow][zeroColumn + 1] = 0;
		zeroColumn++;
	}

	// *************************************************************** Print
	public void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(puzzle[i][j] + "\t");
			System.out.println("\n");
		}
	}

	// *************************************************************** Getter
	public int[][] getPuzzle() {
		return puzzle;
	}

	public int getN() {
		return n;
	}

	public int getZeroRow() {
		return zeroRow;
	}

	public int getZeroColumn() {
		return zeroColumn;
	}

	public Puzzle getParent() {
		return parent;
	}

}
