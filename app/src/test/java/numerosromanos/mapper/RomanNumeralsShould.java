package numerosromanos.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class RomanNumeralsShould {
    //@Rule
    //public ExpectedException illegalArgException = IllegalArgumentException.class;

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
    void returnErrorWithZeroOrLess() {
        try{
            RomanNumerals.arabicToRoman(0);
        } catch(IllegalArgumentException ex){
            assertEquals("Number out of range (1-3999)", ex.getMessage());

        }
    }

    @Test
    void returnErrorwithMoreThanFourThousand() {
        try{
            RomanNumerals.arabicToRoman(4000);
        } catch(IllegalArgumentException ex){
            assertEquals("Number out of range (1-3999)", ex.getMessage());
        }        

    }
    @Test
    void returnMMDVIIwith2507() {
        assertEquals(RomanNumerals.arabicToRoman(2507), "MMDVII");
    }
}
