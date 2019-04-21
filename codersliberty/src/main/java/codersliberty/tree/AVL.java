package codersliberty.tree;

public class AVL<T extends Comparable<T>> extends AVLTree<T> {

	@Override
	public void insert(T data) {
		Node<T> temp = new Node<T>(data);
		if (root == null) {
			temp.setHeight(1);
			root = temp;
			return;
		}
		Node<T> parent = root;
		while (true) {
			Node<T> current = parent;
			if (data.compareTo(current.getData()) < 0) {
				current = parent.getLeft();
				if (current == null) {
					temp.setHeight(parent.getHeight() + 1);
					parent.setLeft(temp);
					return;
				}
			} else {
				current = current.getRight();
				if (current == null) {
					temp.setHeight(parent.getHeight() + 1);
					parent.setRight(temp);
					return;
				}
			}
		}
		
	}
}
