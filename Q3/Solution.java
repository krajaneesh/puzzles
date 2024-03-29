package q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Solution {
	public static class Heading {
		protected int weight;
		protected String text;

		public Heading(int weight, String text) {
			this.weight = weight;
			this.text = text;
		}
	}

	public static class Node {
		protected Heading heading;
		protected List<Node> children;

		public Node(Heading heading) {
			this.heading = heading;
			this.children = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws java.lang.Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        List<Heading> headings = new ArrayList<>();
//        for (String line = br.readLine(); line != null; line = br.readLine()) {
//            headings.add(parse(line));
//        }
		List<String> titleList = Arrays.asList("H1 North America", "H2 America", "H2 Mexico", "H1 South America",
				"H2 Uruguay", "H2 Argentina", "H3 Buenos Aires", "H2 Brazil", "H3 Sao Paulo", "H1 Asia", "H2 India",
				"H3 Mumbai", "H2 Pakistan", "H1 Europe", "H2 UK", "H3 Britain", "H4 London", "H1 Africa", "H2 Uganda"

		);
		List<Heading> headings = new ArrayList<>();
		for (String title : titleList) {
			headings.add(parse(title));
		}
		Node outline = toOutline(headings);
		String html = toHtml(outline);
		System.out.println(html);
	}

	/////////// BEGIN EDITABLE //////////////
	private static Node toOutline(List<Heading> headings) {
		// Implement this function. Sample code below builds an
		// outline of only the first heading.

		Node root = new Node(new Heading(0, ""));
		
		// Map of Nodes with their Node levels as keys. 
		Map<Integer, Node> mapOfNodeLevels = new HashMap<Integer, Node>();
		
		mapOfNodeLevels.put(0, root);
		for (Heading heading : headings) {
			Node node = new Node(heading);
			mapOfNodeLevels.get(heading.weight - 1).children.add(node);
			mapOfNodeLevels.put(heading.weight, node);
		}
		return root;
	}
	/////////// END EDITABLE //////////////

	/**
	 * Parses a line of input. This implementation is correct for all predefined
	 * test cases.
	 */
	private static Heading parse(String record) {
		String[] parts = record.split(" ", 2);
		int weight = Integer.parseInt(parts[0].substring(1));
		Heading heading = new Heading(weight, parts[1].trim());
		return heading;
	}

	/**
	 * Converts a node to HTML. This implementation is correct for all predefined
	 * test cases.
	 */
	private static String toHtml(Node node) {
		StringBuilder buf = new StringBuilder();
		if (!node.heading.text.isEmpty()) {
			buf.append(node.heading.text);
			buf.append("\n");
		}
		Iterator<Node> iter = node.children.iterator();
		if (iter.hasNext()) {
			buf.append("<ul>");

			while (iter.hasNext()) {
				Node child = iter.next();
				buf.append("<li>");
				buf.append(toHtml(child));
				buf.append("</li>");
				if (iter.hasNext()) {
					buf.append("\n");
				}
			}
			buf.append("</ul>");
		}
		return buf.toString();
	}
}
