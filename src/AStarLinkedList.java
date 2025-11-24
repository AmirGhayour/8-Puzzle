
public class AStarLinkedList {
	private class Node {

		private int data;
		private Puzzle puzzle;
		private int heuristic;
		private Node next;

		public Node(int data, Puzzle puzzle, int heuristic) {
			this.data = data;
			this.puzzle = puzzle;
			this.heuristic = heuristic;
			this.next = null;
		}

		public Node(int data, Puzzle puzzle, int heuristic, Node n) {
			this.data = data;
			this.puzzle = puzzle;
			this.heuristic = heuristic;
			this.next = n;
		}

		public int data() {
			return data;
		}

		public int heuristic() {
			return heuristic;
		}

		public Node next() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	private Node start;
	private int length;

	public AStarLinkedList() {
		length = 0;
	}

	public AStarLinkedList(int data, Puzzle puzzle, int heuristic) {
		start = new Node(data, puzzle, heuristic);
		length = 1;
	}

	public int length() {
		return length;
	}

	public boolean isEmpty() {
		return (start == null);
	}

	public void add(int data, Puzzle puzzle, int heuristic) {
		Node q;
		if (length != 0) {
			q = start;
			while (q != null) {
				if (q.heuristic() == heuristic && checkPuzzle(q.puzzle.getPuzzle(), puzzle.getPuzzle()) == true)
					return;
				q = q.next();
			}
		}
		q = start;
		if (length == 0 || q.data() > data) {
			start = new Node(data, puzzle, heuristic, start);
			length++;
			return;
		}
		while (q.next() != null && q.next().data() < data)
			q = q.next();
		q.setNext(new Node(data, puzzle, heuristic, q.next()));
		length++;
	}

	private boolean checkPuzzle(int[][] q, int[][] puzzle) {

		int n = puzzle.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (q[i][j] != puzzle[i][j])
					return false;

		return true;
	}

	public Puzzle pop() {
		if (start == null)
			return null;
		Node q = start;
		start = start.next();
		length--;
		return q.puzzle;
	}

	public void print() {
		Node q = start;
		while (q != null) {
			System.out.println(q.data());
			q = q.next();
		}
	}

}
