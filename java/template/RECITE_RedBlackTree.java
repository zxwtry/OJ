package template;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_RedBlackTree.java
 * @type        RECITE_RedBlackTree
 * @date        2017年3月14日 下午9:10:51
 * @details     
 */
public class RECITE_RedBlackTree<T extends Comparable<? super T>> {
//    private static final int BLACK = 1;     //BLACK must be 1
//    private static final int RED = 0;
//    private RedBlackNode<T> header;
//    private RedBlackNode<T> nullNode;
//    public RECITE_RedBlackTree( ) {
//        
//    }
//    static class RedBlackNode<T> {
//        RedBlackNode(T e) {
//            this(e, null, null);
//        }
//        RedBlackNode(T e, RedBlackNode<T> lt, RedBlackNode<T> rt) {
//            this.element = e;
//            this.left = lt;
//            this.right = rt;
//            this.color = BLACK;
//        }
//        T element;
//        RedBlackNode<T> left;
//        RedBlackNode<T> right;
//        int color;
//    }
//    RedBlackNode<T> rotate(T item, RedBlackNode<T> parent) {
//        return compare(item, parent) < 0 ? 
//            (
//                parent.left = compare(item, parent.left) < 0 ? 
//                rotateWithLeftChild(parent.left) :  //LL
//                rotateWithRightChild(parent.left)   //LR
//            ) : 
//            (
//                parent.right = compare(item, parent.right) < 0 ?
//                rotateWithLeftChild(parent.right) : //RL
//                rotateWithRightChild(parent.right)  //RR    
//            );
//    }
//    private RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> left) {
//        
//        return null;
//    }
//    private RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> left) {
//        return null;
//    }
//    private final int compare(T t, RedBlackNode<T> n) {
//        return t == header ? 1 : t.compareTo(n.element);
//    }
}
