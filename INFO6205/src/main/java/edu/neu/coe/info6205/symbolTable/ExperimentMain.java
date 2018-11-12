package edu.neu.coe.info6205.symbolTable;

import java.util.Random;

public class ExperimentMain {

    public static void main(String[] args){

        final int size[] = {100,200,400,800, 1600}; // number of keys of the initial tree
        final int largSize[] = {10000, 20000, 40000, 80000, 160000}; // larger size
        final int RANGE; // the range of key: 0 - 2 * size
        final int OPERATION = 200000; // number of insertions and deletions each

//        System.out.println(calculateDepth(100, 200, 10000));

        // run 10 times size: 100
        for(int i=0; i<10; i++){
            System.out.println("final depth: "+calculateDepth(100,200,OPERATION));
        }


//        for(int currSize : size) {
//            int range = 2 * currSize;
//            int depth = 0;
////            for(int i=0; i<10; i++){
////                depth += calculateDepth(currSize,range,OPERATION);
////            }
//            System.out.println("final depth: "+calculateDepth(currSize,range,OPERATION));
////            System.out.println("after delete and insert: "+depth*1.0/10);
//        }

    }

    public static int calculateDepth(int size, int range, int operation){

        Random random = new Random();

        // generate Map with distinct keys, and initialize BST
        // set key as Integer with values all equal 1
        BSTSimple bstSimple = new BSTSimple();
        while(bstSimple.size() != size){
            int key = random.nextInt(range+1);
            if(bstSimple.get(key)==null) {
                bstSimple.put(key, 1);
            }
        }
        System.out.println("initial depth of tree: "+bstSimple.getDepth()+", size: "+bstSimple.size());
//        System.out.println(bstSimple.size());


        // insert or delete
        int insertionCount = 0;
        int deletionCount = 0;
        do{
            int select = random.nextInt(2); // 1: insert; 0: delete
            if(select == 1){
                if(insertionCount == operation) continue;
                while(true){
                    int key = random.nextInt(range+1);
                    bstSimple.put(key, 1);
                    insertionCount++;
//                        if(insertionCount%(operation/5) == 0)
//                            System.out.println("insertions - "+insertionCount+", depth of tree: "+bstSimple.getDepth()+", size: "+bstSimple.size());
                    break;
                }
            }else{ // delete
                if(deletionCount == operation) continue;
                if(bstSimple.size() == 0) continue;
                while(true){
                    int key = random.nextInt(range+1);
                    bstSimple.delete(key);
                    deletionCount++;
//                        if(deletionCount%(operation/5) == 0)
//                            System.out.println("deletions - "+deletionCount+", depth of tree: "+bstSimple.getDepth()+", size: "+bstSimple.size());
                    break;
                }
            }
        }while(insertionCount != operation || deletionCount != operation);

        System.out.println("final size: "+bstSimple.size());
        return bstSimple.getDepth();
    }
}
