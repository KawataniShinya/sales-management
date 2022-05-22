package com.sales.common;

public class ThreadVariables {

    public static ThreadLocal<ThreadVariables> threadLocal = new ThreadLocal<ThreadVariables>(){
        @Override
        protected ThreadVariables initialValue(){
            return new ThreadVariables();
        }
    };

    private int logRowNumberInThisThread;

    public ThreadVariables() {
        this.logRowNumberInThisThread = 0;
    }

    public int getLogRowNumberInThisThread() {
        return this.logRowNumberInThisThread++;
    }

}
