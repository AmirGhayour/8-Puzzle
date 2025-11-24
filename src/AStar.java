import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStar {

	private Puzzle[] extendedNodes, path;

	public AStar(Puzzle puzzle) {
		search(puzzle);
	}

	public void search(Puzzle puzzle) {

		int n = puzzle.getN();
		AStarLinkedList list = new AStarLinkedList();

		Puzzle goalPuzzle = new Puzzle(n); // create goalPuzzle
		goalPuzzle.create();
		int[][] gp = goalPuzzle.getPuzzle();

		int g = 0;

		Puzzle p = puzzle;
		int[][] intP;
		int zeroRow;
		int zeroColumn;
		int h;

		h = Heuristic.heuristic(p);
		list.add(h + g, p, h);

		Queue<Puzzle> extendedNodes = new LinkedList<>();

		while (true) {
			g++;
			intP = p.getPuzzle();
			zeroRow = p.getZeroRow();
			zeroColumn = p.getZeroColumn();
			extendedNodes.add(p);

			boolean b = true; // Check puzzle with goalPuzzle
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					if (intP[i][j] != gp[i][j]) {
						b = false;
						i = j = n;
						break;
					}
				}

			if (b)
				break;

			if (zeroRow != 0) {
				Puzzle node = new Puzzle(n, p);
				node.copyOf(p);
				node.moveZeroToUp();
				h = Heuristic.heuristic(node);
				list.add(h + g, node, h);
			}

			if (zeroRow != n - 1) {
				Puzzle node = new Puzzle(n, p);
				node.copyOf(p);
				node.moveZeroToDown();
				h = Heuristic.heuristic(node);
				list.add(h + g, node, h);
			}

			if (zeroColumn != 0) {
				Puzzle node = new Puzzle(n, p);
				node.copyOf(p);
				node.moveZeroToLeft();
				h = Heuristic.heuristic(node);
				list.add(h + g, node, h);
			}

			if (zeroColumn != n - 1) {
				Puzzle node = new Puzzle(n, p);
				node.copyOf(p);
				node.moveZeroToRight();
				h = Heuristic.heuristic(node);
				list.add(h + g, node, h);
			}

			p = list.pop();

		} // end while

		this.extendedNodes = new Puzzle[extendedNodes.size()];
		extendedNodes.toArray(this.extendedNodes);

		Stack<Puzzle> path = new Stack<>();
		path.add(p);

		while (p.getParent() != null) {
			path.add(p.getParent());
			p = p.getParent();
		}

		int s = path.size();
		this.path = new Puzzle[s];
		for (int i = 0; i < s; i++)
			this.path[i] = path.pop();

	} // end method

	public Puzzle[] getExtendedNodes() {
		return extendedNodes;
	}

	public Puzzle[] getPath() {
		return path;
	}
}
