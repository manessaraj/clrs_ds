package Trees;

import models.TreeNode;

public interface TreeRotations<T> {
    /*
    * Any tree which maintains some sort of property for particular advantage in some key operations,
    * gets distorted with deletes and inserts. For e.g., In a red-Black tree, insert/delete might violate the property
    * of having same number of black nodes in each path, or black children for each red node. So, to maintain key properties,
    * we need rotations. Any tree which maintains some properties, need to implement this interface.
    *
    * */
    TreeNode<T> rotateLeft(TreeNode<T> x);

    TreeNode<T> rotateRight(TreeNode<T> y);
}

