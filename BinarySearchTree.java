/*
This class implements multiple binary search tree methods associated 
such as search, insert, delete, path, leftSubTree, rightSubTree, 
getNumberOfLeaves, sameTree, preorder, inorder, and post order
 */
package assignment5;

import java.io.*;
import java.util.*;

/**
 * CS-202-18371 4/11/2017
 * @author Jonathan Steininger
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;

    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     *
     * @param objects: elements of the tree
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     *
     * @param e: the element sought
     * @return true if the element is found, false otherwise
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                //count[0]++;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                //count[0]++;
                current = current.right;
            } else // element matches current.element
            {
                //count[0]++;
                return true; // Element is found
            }
        }
        return false;
    }
    /**
     * Returns true if the element is in the tree and counts the number of 
     * comparisons made to find the element.
     * @param e: the element sought
     * @param count: the number of comparisons made
     * @return true if the element is found, false otherwise
     */
    public boolean search(E e, int[] count) {
        TreeNode<E> current = root; // Start from the root
        count[0] = 0;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                count[0]++;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                count[0]++;
                current = current.right;
            } else // element matches current.element
            {
                count[0]++;
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     *
     * @param e: the element to be inserted
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     * Creates a new node for the tree
     *
     * @param e: element to be put in the node
     * @return: a newly created node
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     *
     * @param root: root of the tree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     *
     * @param root: root of the tree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     *
     * @param root: root of the tree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     *
     * @param <E>: a generic type for the class
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         * Constructor for the TreeNode
         *
         * @param e: the element to be placed in the tree
         */
        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     *
     * @return the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     *
     * @param e: the element whose path is sought
     * @return an ArrayList containing the elements in the path to e
     */
    public ArrayList<E> path(E e) {
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        //implement the code here as in search method.
        list.add(current.element);
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                list.add(current.element);
                current = current.left;
                //list.add(current.element); //should these be before the current = and then in the else it would have one of these before returning?
            } else if (e.compareTo(current.element) > 0) {
                list.add(current.element);
                current = current.right;
                //list.add(current.element);
            } else // element matches current.element
            {
                list.add(current.element);
                return list;
            }
        }
        if (list.get(list.size() - 1).equals(e)) {
            return list;
        } else {
            ArrayList<E> listEmpty = new ArrayList<>();
            return listEmpty;
        }
    }

    /**
     * Determines the number of leaf nodes in this tree, returns 0 if tree is
     * empty
     *
     * @return the number of leaf nodes in this tree, returns 0 if tree is empty
     */
    public int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    /**
     * Determines the number of leaf nodes in this tree, returns 0 if tree is
     * empty
     *
     * @param root: the root node of the tree
     * @return the number of leaf nodes in this tree, returns 0 if tree is empty
     */
    private int getNumberOfLeaves(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        } else {
            return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
        }
    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified
     * element’s left sub-tree, returns an empty ArrayList if no such element
     * exists.
     *
     * @param e: the element whose left subtree is sought
     * @return an ArrayList containing all elements in preorder of the specified
     * element’s left sub-tree, returns an empty ArrayList if no such element
     * exists.
     */
    public ArrayList<E> leftSubTree(E e) {
        //left for you to implement in Lab 7
        TreeNode<E> current = root; // Start from the root
        ArrayList<E> containerLeft = new ArrayList<>();
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return leftSubTree(current.left, containerLeft);
            }
        }

        return containerLeft;
    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified
     * element’s left sub-tree, returns an empty ArrayList if no such element
     * exists.
     *
     * @param root: the element whose left subtree is sought
     * @param containerLeft: ArrayList containing all elements of the subtree
     * found so far
     * @return an ArrayList containing all elements in preorder of the specified
     * element’s left sub-tree, returns an empty ArrayList if no such element
     * exists.
     */
    private ArrayList<E> leftSubTree(TreeNode<E> root, ArrayList<E> containerLeft) {
        //ArrayList<E> containerLeft = new ArrayList<>();
        if (root != null) {
            containerLeft.add(root.element);
        }
        if (root.left != null) {
            leftSubTree(root.left, containerLeft);
        }
        if (root.right != null) {
            leftSubTree(root.right, containerLeft);
        }
        return containerLeft;

    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified
     * element’s right sub-tree, returns an empty ArrayList if no such element
     * exists.
     *
     * @param e: the element whose right subtree is sought
     * @return an ArrayList containing all elements in preorder of the specified
     * element’s right sub-tree, returns an empty ArrayList if no such element
     * exists.
     */
    public ArrayList<E> rightSubTree(E e) {
        //left for you to implement in Lab 7
        TreeNode<E> current = root; // Start from the root
        ArrayList<E> containerRight = new ArrayList<>();
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return rightSubTree(current.right, containerRight);
            }
        }

        return containerRight;
    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified
     * element’s right sub-tree, returns an empty ArrayList if no such element
     * exists.
     *
     * @param root: the element whose right subtree is sought
     * @param containerRight: ArrayList containing all elements of the subtree
     * found so far
     * @return an ArrayList containing all elements in preorder of the specified
     * element’s right sub-tree, returns an empty ArrayList if no such element
     * exists.
     */
    private ArrayList<E> rightSubTree(TreeNode<E> root, ArrayList<E> containerRight) {
        //ArrayList<E> containerLeft = new ArrayList<>();
        if (root != null) {
            containerRight.add(root.element);
        }
        if (root.left != null) {
            rightSubTree(root.left, containerRight);
        }
        if (root.right != null) {
            rightSubTree(root.right, containerRight);
        }
        return containerRight;

    }

    /**
     * Determines if two trees have are the same by comparing the elements at
     * each position
     *
     * @param tree: the tree to be compared to the calling tree
     * @return true if both trees have the same elements in the same positions,
     * false otherwise
     */
    public boolean sameTree(BinarySearchTree<E> tree) {
        return sameTree(this.root, tree.root);
    }

    /**
     * Determines if two trees have are the same by comparing the elements at
     * each position
     *
     * @param root1: root node of tree1
     * @param root2: root node of tree2
     * @return true if both trees have the same elements in the same positions,
     * false otherwise
     */
    private boolean sameTree(TreeNode<E> root1, TreeNode<E> root2) {
        if ((root1 == null && root2 == null)) {
            return true;
        } else if ((root1 != null && root2 != null) && root1.element.equals(root2.element)) {
            return sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    /* Returns the inorder predecessor of the specified element, returns null if tree is empty or element 'e' is not in the tree. */
    /**
     *
     * @param e
     * @return
     */
    public E inorderPredecessor(E e) {
        //left for you to implement in Lab 7
        return e;
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     *
     * @param e: the element to be deleted
     * @return true if the deletion is successful, false otherwise
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     *
     * @return
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     *
     * @return
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
