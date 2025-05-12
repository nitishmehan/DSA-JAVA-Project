package dsa;

import models.Train;

public class BST {
    private static class BSTNode {
        Train train;
        BSTNode left, right;

        public BSTNode(Train train) {
            this.train = train;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;

    public BST() {
        this.root = null;
    }

    public void insert(Train train) {
        root = insertRec(root, train);
    }

    private BSTNode insertRec(BSTNode root, Train train) {
        if (root == null) {
            root = new BSTNode(train);
            return root;
        }
        if (train.getTrainNumber() < root.train.getTrainNumber()) {
            root.left = insertRec(root.left, train);
        } else if (train.getTrainNumber() > root.train.getTrainNumber()) {
            root.right = insertRec(root.right, train);
        }
        return root;
    }

    public Train searchByNumber(int trainNumber) {
        return searchByNumberRec(root, trainNumber);
    }

    private Train searchByNumberRec(BSTNode root, int trainNumber) {
        if (root == null || root.train.getTrainNumber() == trainNumber) {
            return root != null ? root.train : null;
        }
        return trainNumber < root.train.getTrainNumber() ? 
            searchByNumberRec(root.left, trainNumber) : 
            searchByNumberRec(root.right, trainNumber);
    }

    public Train searchByName(String name) {
        return searchByNameRec(root, name);
    }

    private Train searchByNameRec(BSTNode root, String name) {
        if (root == null) {
            return null;
        }
        if (root.train.getName().equalsIgnoreCase(name)) {
            return root.train;
        }
        Train foundTrain = searchByNameRec(root.left, name);
        return (foundTrain != null) ? foundTrain : searchByNameRec(root.right, name);
    }
}