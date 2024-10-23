package Utils;

/**
 * Helper functions for recurring operations.
 */
public class Utility {
    // converts 1 <-> A(65) etc.
    public static char intToAlpha(int num) {
        final int INT_ASCII_CONVERSION_DIFF = 64;
        return (char) (num + INT_ASCII_CONVERSION_DIFF);
    }
    public static int alphaToInt(char alpha) {
        assert (alpha >= 'A' && alpha <= 'Z') || (alpha >= 'a' && alpha <= 'z');
        final int INT_ASCII_CONVERSION_DIFF = 64;
        return Character.toUpperCase(alpha) - INT_ASCII_CONVERSION_DIFF;
    }
}
