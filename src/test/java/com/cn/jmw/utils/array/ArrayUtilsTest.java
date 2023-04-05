package com.cn.jmw.utils.array;

import com.cn.jmw.uitls.algorithm.array.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author jmw
 * @Description TODO
 * @date 2023��02��07�� 14:16
 * @Version 1.0
 */
public class ArrayUtilsTest {

    @Test
    public void one() {
        ArrayList<Object> objects = new ArrayList<>();
        Integer[] arr = new Integer[]{0,1,0,3,199};
        String[] arr2 = new String[]{"0","1","0","3","199"};
        ArrayUtils<String> integerArrayUtils = new ArrayUtils<String>(arr2);
        integerArrayUtils.moveZeroes("0");
        System.out.println(Arrays.toString(arr2));
        ArrayUtils<Integer> integerArrayUtils1 = new ArrayUtils<Integer>(arr);
        integerArrayUtils1.moveZeroes(0);
        System.out.println(Arrays.toString(arr));

        integerArrayUtils1.moveZeroes(new int[]{0,1,0,3,199});
        System.out.println();
    }
}
