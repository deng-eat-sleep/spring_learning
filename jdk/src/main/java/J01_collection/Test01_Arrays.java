package J01_collection;

import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Test01_Arrays {

    public void sort(){
        Integer[] array = new Integer[]{1,2,5,3,6,4};

        //匿名内部类
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        //Lambda
        Arrays.sort(array,(s1,s2) -> s1.compareTo(s2));
        Arrays.sort(array,Comparator.naturalOrder());

        Arrays.sort(array,(s1,s2) -> s2.compareTo(s1));
        Arrays.sort(array,Comparator.reverseOrder());


    }

    public static int[] sum(int[] nums){
        int l = nums.length;
        int[] sums = new int[l];
        sums[0] = nums[0];
        for(int i = 1; i< l; i++){
            System.out.println(i);
            sums[i] = sums[i-1] + nums[i];
        }
        return sums;
    }

    public boolean canConstruct(String ransomNote, String magazine) {

        char[] chars = ransomNote.toCharArray();
        for(char c: chars){
//            magazine.
        }
        StringBuilder d = new StringBuilder();
        d.append("");
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int[] sums = sum(nums);

    }
}
