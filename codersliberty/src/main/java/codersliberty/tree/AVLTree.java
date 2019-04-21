package codersliberty.tree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{

	public void insert(T data) {
		Node<T> temp = new Node<T>(data);
		this.root = insert(root, temp);
	}

	public Node<T> insert(Node<T> node, Node<T> newNode) {
		if (this.root == null) {
			this.root = newNode;
			return node;
		}
		Node<T> current = null;
		newNode.setHeight(node.getHeight()+1);
		if (newNode.getData().compareTo(node.getData()) < 0) {
			current = node.getLeft();
			if (current == null) {
				node.setLeft(newNode);
				return newNode;
			}
		} else {
			current = node.getRight();
			if (current == null) {
				node.setRight(newNode);
				return newNode;
			}
		}
		
		int balance = balanceFactor(node.getLeft(), node.getRight());
		// If this node becomes unbalanced, then there 
        // are 4 cases 
		// Left Left Case 
        if (balance > 1 && (newNode.getData().compareTo(getData(node.getLeft())) < 0)) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && (newNode.getData().compareTo(getData(node.getRight())) > 0)) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && (newNode.getData().compareTo(getData(node.getLeft())) > 0)) { 
        	node.setLeft(leftRotate(node.getLeft())); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && (newNode.getData().compareTo(getData(node.getRight())) < 0)) { 
        	node.setRight(rightRotate(node.getRight())); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
		return insert(current, newNode);
	}
	
	private Node<T> rightRotate(Node<T> node) {
		Node<T> l = node.getLeft();
		Node<T> lr=l.getRight();
		l.setRight(node);
		node.setLeft(lr);
		l.setHeight(Math.max(height(node.getLeft()), height(node.getRight())));
		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())));
		return l;
	}
	
	private Node<T> leftRotate(Node<T> node) {
		Node<T> r=node.getRight();
		Node<T> rl=r.getLeft();
		r.setLeft(node);
		node.setRight(rl);
		r.setHeight(Math.max(height(r.getLeft()), height(r.getRight())));
		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())));
		return r;
	}
	
	private int balanceFactor(Node<T> node1, Node<T> node2) {
		return height(node1)-height(node2);
	}
	
	private int height(Node<T> n) {
		if(n != null) {
			return n.getHeight();
		}
		return 0;
	}
	
	private T getData(Node<T> node) {
		if(node != null) {
			return node.getData();
		}
		return null;
	}
	public static void main(String[] args) {
		AVLTree<Integer> avlTree = new AVL<>();
		avlTree.insert(20);
		avlTree.insert(5);
		avlTree.insert(25);
		avlTree.insert(3);
		avlTree.insert(7);
		avlTree.insert(27);
		avlTree.insert(24);
		System.out.println("In order");
		avlTree.inOrder(avlTree.getRoot());
		System.out.println(avlTree.getRoot());
		System.out.println(avlTree.balanceFactor(avlTree.getRoot(), avlTree.getRoot().getLeft().getLeft()));
		
		AVLTree<Integer> tree=new AVLTree<>();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(25);
		System.out.println("In order");
		tree.inOrder(tree.getRoot());
		System.out.println(tree.getRoot());
		
	}
	/*
	 	10														30
			20												20		40
				30										  10   25      50
			   25  40
			   		 50
	 */

}
