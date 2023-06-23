package numerosromanos.mapper;
public class RomanNumerals {
    private static final String[] ROMAN_NUMERALS = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    private static final int[] ARABIC_VALUES = {
    1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    public static String arabicToRoman(int arabicNumber){
        if (arabicNumber <= 0 || arabicNumber > 3999) {
            throw new IllegalArgumentException("Number out of range (1-3999)");
        }

        StringBuilder romanNumeral = new StringBuilder();

        for (int i = 0; i < ARABIC_VALUES.length; i++) {
            while (arabicNumber >= ARABIC_VALUES[i]) {
                romanNumeral.append(ROMAN_NUMERALS[i]);
                arabicNumber -= ARABIC_VALUES[i];
            }
        }

        return romanNumeral.toString();
    }

}
