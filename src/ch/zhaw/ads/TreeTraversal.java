package ch.zhaw.ads;

import java.util.*;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    private void inorderRecursion(TreeNode<T> node, Visitor<T> vis) {
      if (node != null) {
        inorderRecursion(node.left, vis);
        vis.visit(node.getValue());
        inorderRecursion(node.right, vis);
      }
    }

    public void inorder(Visitor<T> vis) {
      inorderRecursion(root, vis);
    }

    private void preorderRecursion(TreeNode<T> node, Visitor<T> vis) {
      if (node != null) {
        vis.visit(node.getValue());
        preorderRecursion(node.left, vis);
        preorderRecursion(node.right, vis);
      }
    }

    public void preorder(Visitor<T> vis) {
        preorderRecursion(root, vis);
    }

    private void postorderRecursion(TreeNode<T> node, Visitor<T> vis) {
      if (node != null) {
        postorderRecursion(node.left, vis);
        postorderRecursion(node.right, vis);
        vis.visit(node.getValue());
      }
    }

    public void postorder(Visitor<T> vis) {
        postorderRecursion(root, vis);
    }

    private void levelorderRecursion(TreeNode<T> node, int level, Map<Integer, List<T>> levelOrderList) {
      if (node != null) {
        if (levelOrderList.containsKey(level)) {
          levelOrderList.get(level).add(node.getValue());
        } else {
          levelOrderList.put(level, new ArrayList<T>(Arrays.asList(node.getValue())));
        }
        levelorderRecursion(node.left, level + 1, levelOrderList);
        levelorderRecursion(node.right, level + 1, levelOrderList);
      }

    }

    public void levelorder(Visitor<T> vistor) {
      Map<Integer, List<T>> levelOrderList= new HashMap<>();
      levelorderRecursion(root, 0, levelOrderList);
      levelOrderList.forEach((key, value) -> value.forEach(v -> vistor.visit(v)));
    }

    private void intervalRecursion(T min, T max, TreeNode<T> node, Visitor<T> vistor) {
      if (node != null) {
        vistor.visit(node.getValue());
        if (node.getValue().compareTo(min) >= 0 && node.getValue().compareTo(max) <= 0) {
          intervalRecursion(min, max, node.left, vistor);
          intervalRecursion(min, max, node.right, vistor);
        } else if(node.getValue().compareTo(min) < 0) {
          intervalRecursion(min, max, node.right, vistor);
        } else if(node.getValue().compareTo(max) > 0) {
          intervalRecursion(min, max, node.left, vistor);
        }
      }
    }

    public void interval(T min, T max, Visitor<T> vistor) {
      if (root == null) return;
      intervalRecursion(min, max, root, vistor);
    }
}
