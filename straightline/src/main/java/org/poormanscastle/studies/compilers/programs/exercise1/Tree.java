package org.poormanscastle.studies.compilers.programs.exercise1;

/**
 * persistent functional binary search tree, so that if tree2 = insert(x, tree1), tree1 is still unchanged available for
 * lookups while tree2 can also already be used.
 * <p>
 * Created by georg on 12.12.15.
 */
public class Tree {

    // binary tree has a left branch and a right branch. or a branch1 and a branch2. or whatever, anyway there a 2 of them.
    private Tree left;
    private Tree right;
    private String key;

    public Tree(Tree left, Tree right, String key) {
        this.left = left;
        this.right = right;
        this.key = key;
    }

    /**
     * no-args constructor to start with a new Tree.
     *
     * @param key
     */
    public Tree(String key) {
        this.key = key;
    }

    public Tree insert(String key) {
        return null;
    }
}
