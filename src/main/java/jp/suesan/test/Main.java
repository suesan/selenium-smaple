package jp.suesan.test;

import org.junit.runner.JUnitCore;

/**
 * Created by suesan on 2016/10/26.
 */
public class Main {

    public Main(){
        //Do Nothing...
    }

    public static void main(String[] args) throws Exception {
        JUnitCore junit = new JUnitCore();
        junit.main("jp.suesan.test.ToolTest");
    }
}
