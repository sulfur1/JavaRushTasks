package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        Solution solution;
        Solution.InnerClass innerClass;
        for (int i = 0; i < solutions.length; i++) {
            solution = new Solution();
            for (int j = 0; j < solution.innerClasses.length; j++) {
                innerClass = new Solution().new InnerClass();
                solution.innerClasses[j] = innerClass;
            }
            solutions[i] = solution;
        }
        return solutions;
    }

    public static void main(String[] args) {

    }
}
