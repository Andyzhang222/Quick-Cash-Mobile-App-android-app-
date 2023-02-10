import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class emailTest {

    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see < a href=" ">Testing documentation</ a>
     */
    public class ValidateRegisterEmailTest {
        @Test
        public void addition_isCorrect() {
            assertEquals(4, 2 + 2);
        }


        private ValidateRegisterEmailTest email = new ValidateRegisterEmailTest();

        // Email format : letters,numbers,symbols+@+letters+"."+letters


        @Test
        public void checkEmailHasAtSymbolTest() {
            Validator v1 = new Validator();
            Validator v2 = new Validator();
            Validator v3 = new Validator();
            assertFalse(v1.validateEmail("Abcd2023.fdsa.sss"));// upper case & lower case+ . +letters+ . +letters
            assertFalse(v2.validateEmail("abjshd.com"));// without @ symbol
            assertFalse(v3.validateEmail("abjshd@@dal.com"));// extra at symbol

        }

        @Test
        public void checkEmailHasDotSymbolTest() {
            Validator v1 = new Validator();
            Validator v2 = new Validator();
            Validator v3 = new Validator();

            assertTrue(v1.validateEmail("asdf@com"));// without the dot symbol
            assertTrue(v2.validateEmail("asdf@dal.ca"));// right format
            assertTrue(v3.validateEmail("asdf@dal..ca"));// extra dot symbol
        }

        @Test
        public void checkEmailSequenceTest() {
            Validator v1 = new Validator();
            Validator v2 = new Validator();

            assertTrue(v1.validateEmail("asdf.dal@com"));// dot symbol is in front of at symbol
            assertTrue(v2.validateEmail("asdf@dal.ca"));// right format
        }
    }
}