import java.util.HashMap;

public class LeetCode {

    public static void main(String[] args) {
        System.out.println("Answer: " + romanToInteger("LVIII"));
    }


    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     * <p>
     * Example 1:
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     */
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + nums[i + 1] == target) {
                return new int[]{i, i + 1};
            }
        }
        return new int[]{0, 0};
    }

    /**
     * Given an integer x, return true if x is a palindrome, and false otherwise.
     * <p>
     * Example 1:
     * Input: x = 121
     * Output: true
     * <p>
     * Example 2:
     * Input: x = -121
     * Output: false
     */
    public static boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars.length == 2) {
                if (chars[i] != chars[chars.length / 2 - i]) {
                    return false;
                }
            }
            if (chars[i] == chars[chars.length / 2 - 1 - i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     * <p>
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     * <p>
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     * <p>
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * <p>
     * Given a roman numeral, convert it to an integer.
     */

    public static int romanToInteger(String s) {
        char[] chars = s.toCharArray();

        var map = new HashMap<String, Integer>();

        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int res = 0;

        for (int i = chars.length - 1; i >= 0; i--) {
            if (i == 0 && !map.containsKey("" + chars[i] + chars[i + 1])) {
                res += map.get("" + chars[i]);
                break;
            }

            var t = "" + chars[i - 1] + chars[i];

            if (map.containsKey(t)) {
                i--;
                res += map.get(t);
                continue;
            }

            res += map.get("" + chars[i]);

            System.out.println(res); // промежуточное значение
        }

        return res;
    }

    // Given an integer n, return the number of positive integers in the range [1, n] that have at least one repeated digit.
    static int repeated(int n) {
        int iWantTo = 0;

        for (int i = 11; i <= n; i++) {
            char[] charArr = ("" + i).toCharArray();

            free:
            for (int j = 0; j < charArr.length - 1; j++) {
                for (int k = j + 1; k < charArr.length; k++) {
                    if (charArr[j] == charArr[k]) {
                        iWantTo++; break free;
                    }
                }
            }
        }
        return iWantTo;
    }
}
