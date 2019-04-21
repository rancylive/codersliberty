package codersliberty.tree;

public class BinarySearchTree<T extends Comparable> {
	protected Node<T> root;

	public void insert(T data) {
		Node temp = new Node<T>(data);
		if (root == null) {
			root = temp;
			return;
		} else {
			Node current = root;
			while (true) {
				Node parent = current;
				if (data.compareTo(current.getData()) < 0) {
					current = current.getLeft();
					if (current == null) {
						temp.setHeight(parent.getHeight()+1);
						parent.setLeft(temp);
						return;
					}
				} else {
					current = current.getRight();
					if (current == null) {
						temp.setHeight(parent.getHeight()+1);
						parent.setRight(temp);
						return;
					}
				}
			}
		}
	}

	public boolean search(T data) {
		return search(root, data);
	}

	public boolean search(Node root, T data) {
		if (root == null) {
			return false;
		}
		if (data.compareTo(root.getData()) == 0) {
			return true;
		} else {
			if (data.compareTo(root.getData()) < 0) {
				return search(root.getLeft(), data);
			} else {
				return search(root.getRight(), data);
			}
		}
	}

	public void preOrder(Node root) {
		if (root != null) {
			System.out.print(root.getData() + " ");
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}

	public void inOrder(Node root) {
		if (root != null) {
			inOrder(root.getLeft());
			System.out.print(root.getData() + " ");
			inOrder(root.getRight());
		}
	}

	public void postOrder(Node node) {
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}

	public Node<T> getRoot() {
		return root;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(20);
		bst.insert(5);
		bst.insert(25);
		bst.insert(3);
		bst.insert(7);
		bst.insert(27);
		bst.insert(24);
		System.out.println("\nIn Order ");
		bst.inOrder(bst.getRoot());
		System.out.println("\nPre Order ");
		bst.preOrder(bst.getRoot());
		System.out.println("\nPost order ");
		bst.postOrder(bst.getRoot());
		System.out.println(bst.search(3));
		System.out.println(bst.getRoot());
	}
			
/*	 20
   5    25
  3 7 24  27
  		*/ 
}
