package sean.yu.awttest;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-28 17:10
 **/

public class TestInnerClass {
    public static void main(String[] args) {
        new Outer();
    }
}

class Outer {

    public Outer() {
        new Inner().innerSay();
    }

    private void outerSay(){
        System.out.println("outer say");
    }

    private class Inner{
        private void innerSay(){
            outerSay();
        }

//        private void outerSay(){
//            System.out.println("inner say");
//        }
    }
}
