class Solution {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"ab", "a"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        int len = 0;

        while (len <= 200) {

            for (int i = 0; i < strs.length; i++) {

                if (strs[i].length() <= len || strs[i].charAt(len) != strs[0].charAt(len)) {

                    return strs[0].substring(0, len);

                }

            }

            len++;

        }

        return "";
    }
}