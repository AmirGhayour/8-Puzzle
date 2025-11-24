
public class Main {

	public static void main(String[] args) {

		// int[][] puzz = { { 4, 5, 3 }, { 7, 1, 6 }, { 2, 0, 8 } };

		Puzzle puzzle = new Puzzle(3);
		puzzle.create();
		puzzle.mix(10);
		puzzle.print();

		System.out.println("\n");

		System.out.println("Current Time: " + System.currentTimeMillis() + "\n");
		System.out.println("Please wait ...");
		AStar astar = new AStar(puzzle);
		System.out.println("Puzzle is solved");
		System.out.println("\nCurrent Time: " + System.currentTimeMillis());

		Puzzle[] extend = astar.getExtendedNodes();
		Puzzle[] path = astar.getPath();

		int e = extend.length;
		int p = path.length;

		System.out.println("\n");
		System.out.println("Extended Nodes:");
		System.out.println();

		for (int i = 0; i < e; i++) {
			extend[i].print();
			System.out.println("------------------------------");
		}

		System.out.println("Number of Nodes: " + e);

		System.out.println("\n");
		System.out.println("Path:");
		System.out.println();

		for (int i = 0; i < p; i++) {
			path[i].print();
			System.out.println("------------------------------");
		}

		System.out.println("Number of Nodes: " + p);

	}

}
