package numerosromanos.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class RomanNumeralsShould {
    @Test
    void returnIwithOne() {
        assertEquals(RomanNumerals.arabicToRoman(1), "I");
    }
    
    @Test
    void returnIIwithTwo() {
        assertEquals(RomanNumerals.arabicToRoman(2), "II");
    }

    @Test
    void returnCMwithNineHunder() {
        assertEquals(RomanNumerals.arabicToRoman(900), "CM");
    }

    @Test
    void returnXCIXwithNinetyNine() {
        assertEquals(RomanNumerals.arabicToRoman(99), "XCIX");
    }


}
