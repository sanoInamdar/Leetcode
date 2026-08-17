class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();

        int index = s.lastIndexOf(" ");

        String lastWord = s.substring(index + 1);

        return lastWord.length();
    }
}