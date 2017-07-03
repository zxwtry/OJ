package template;

import java.util.LinkedList;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Graph_AVLTree.java
 * @date        2017年7月3日 下午8:37:24
 * @details     
 */
public class RECITE_Graph_AVLTree {

    static class AVLTree<T extends Comparable<? super T>> {
        private static final int ALLOWED_IMBALANCE = 1;
        private AVLNode<T> root;

        public void insert(T x) {
            this.root = insert(x, this.root);
        }

        public void remove(T x) {
            this.root = remove(x, this.root);
        }

        public LinkedList<T> print() {
            LinkedList<T> ll = new LinkedList<>();
            inOrder(ll, this.root);
            return ll;
        }

        public int height() {
            return height(this.root);
        }

        private void inOrder(LinkedList<T> ll, AVLNode<T> n) {
            if (n == null)
                return;
            inOrder(ll, n.left);
            ll.addLast(n.element);
            inOrder(ll, n.right);
        }

        private AVLNode<T> insert(T x, AVLNode<T> n) {
            if (n == null) {
                return new AVLNode<T>(x);
            }
            int cmpRes = x.compareTo(n.element);
            if (cmpRes < 0) {
                n.left = insert(x, n.left);
            } else if (cmpRes > 0) {
                n.right = insert(x, n.right);
            } else {
                // 重复，看怎么约定
            }
            return balance(n);
        }

        private AVLNode<T> remove(T x, AVLNode<T> n) {
            if (n == null) {
                return n;
            }
            int cmpRes = x.compareTo(n.element);
            if (cmpRes < 0) {
                n.left = remove(x, n.left);
            } else if (cmpRes > 0) {
                n.right = remove(x, n.right);
            } else if (n.left != null && n.right != null) {
                n.element = findMin(n.right).element;
                n.right = remove(n.element, n.right);
            } else {
                n = (n.left != null) ? n.left : n.right;
            }
            return balance(n);
        }

        private AVLNode<T> balance(AVLNode<T> n) {
            if (n == null) {
                return n;
            }
            int leftHeight = height(n.left);
            int rightHeight = height(n.right);

            if (leftHeight - rightHeight > ALLOWED_IMBALANCE) {
                if (height(n.left.left) >= height(n.left.right)) {
                    n = rotateWithLeftChild(n);
                } else {
                    n = doubleWithLeftChild(n);
                }
            } else if (rightHeight - leftHeight > ALLOWED_IMBALANCE) {
                if (height(n.right.right) >= height(n.right.left)) {
                    n = rotateWithRightChild(n);
                } else {
                    n = doubleWithRightChild(n);
                }
            }
            n.height = Math.max(height(n.left), height(n.right)) + 1;
            return n;
        }

        private AVLNode<T> doubleWithLeftChild(AVLNode<T> k3) {
            k3.left = rotateWithRightChild(k3.left);
            return rotateWithLeftChild(k3);
        }

        private AVLNode<T> doubleWithRightChild(AVLNode<T> k3) {
            k3.right = rotateWithLeftChild(k3.right);
            return rotateWithRightChild(k3);
        }

        private AVLNode<T> rotateWithLeftChild(AVLNode<T> k2) {
            AVLNode<T> k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
            k1.height = Math.max(height(k1.left), k2.height) + 1;
            return k1;
        }

        private AVLNode<T> rotateWithRightChild(AVLNode<T> k2) {
            AVLNode<T> k1 = k2.right;
            k2.right = k1.left;
            k1.left = k2;
            k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
            k1.height = Math.max(height(k1.right), k2.height) + 1;
            return k1;
        }

        private int height(AVLNode<T> n) {
            return n == null ? -1 : n.height;
        }

        private AVLNode<T> findMin(AVLNode<T> n) {
            if (n == null) {
                return null;
            }
            while (n.left != null) {
                n = n.left;
            }
            return n;
        }
    }

    private static class AVLNode<T> {
        T element;
        AVLNode<T> left;
        AVLNode<T> right;
        int height;

        AVLNode(T element) {
            this(element, null, null);
        }

        AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        @Override
        public String toString() {
            return String.format("element: %s\nleft: %s\nright: %s\nheight: %d",
                this.element.toString(),
                this.left == null ? "null" : this.left.element.toString(),
                this.right == null ? "null" : this.right.element.toString(),
                this.height);
        }
    }
}
