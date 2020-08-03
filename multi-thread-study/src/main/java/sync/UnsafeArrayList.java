package sync;

import java.util.ArrayList;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 17:35
 **/

public class UnsafeArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(list.size());
    }
}
