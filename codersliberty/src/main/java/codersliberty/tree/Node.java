package codersliberty.tree;

public class Node<T> {
	private T data;
	private int height;
	private Node<T> left;
	private Node<T> right;
	
	public Node(T data) {
		this.data = data;
		this.height = 1;
		this.left = null;
		this.right = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", height=" + height + ", left=" + left + ", right=" + right + "]";
	}

}
