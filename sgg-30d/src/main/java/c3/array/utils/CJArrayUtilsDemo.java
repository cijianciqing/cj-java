package c3.array.utils;

import java.util.Arrays;

public class CJArrayUtilsDemo {
    public static void main(String[] args) {
        int [] numbers = {5,900,1,5,77,30,64,700};
        Arrays.sort(numbers);
        for(int i = 0; i < numbers.length; i++){
            System.out.println(numbers[i]);
        }
    }
}
