package se.meepo.slothtodos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobberLanguageServiceTest {
  private IRobberLanguageService instanceToTest;
  
  @BeforeEach
  public void setup() {
    instanceToTest = new RobberLanguageService();
  }

  private void assertInputBecomesExpected(String input, String expected) {
    String actual = instanceToTest.convertToRobberLanguage(input);
    assertEquals(expected, actual);
  }

  @Test
  public void whenEmptyInput_shouldReturnEmptyString() {
    assertInputBecomesExpected("", "");
  }

  @Test
  public void whenInputNull_shouldReturnNull() {
    assertInputBecomesExpected(null, null);
  }

  @Test
  public void whenInputIsSingleVowel_shouldReturnSameLetter() {
    assertInputBecomesExpected("a", "a");  
  }
  
  @Test
  public void whenInputIsSingleLowercaseB_shouldReturnLowercaseBob() {
    assertInputBecomesExpected("b", "bob");  
  }

  @Test
  public void whenInputIsSingleUppercaseB_shouldReturnCapitalizedBob() {
    assertInputBecomesExpected("B", "Bob");
  }
  
  @Test
  public void whenInputIsTwoBs_shouldReturnBob_bob() {
    assertInputBecomesExpected("Bb", "Bob-bob");
  }
  
  @Test
  public void whenInputIsTwoAs_shouldReturnThemAsTheyAre() {
    assertInputBecomesExpected("Aa", "Aa");
  }
  
  @Test
  public void whenInputIsBob_shouldReturnBob_o_bob() {
    assertInputBecomesExpected("Bob", "Bob-o-bob");
  }
  
  @Test
  public void shouldHandleTwoWords() {
    assertInputBecomesExpected("Hi Bob", "Hoh-i Bob-o-bob");
  }
  
  @Test
  public void shouldHandleNewline() {
    assertInputBecomesExpected("Hi\nBob", "Hoh-i\nBob-o-bob");
  }
  
  @Test
  public void shouldKeepDelimitersAsTheyAre() {
    assertInputBecomesExpected("?? Hi!\n/Bob", "?? Hoh-i!\n/Bob-o-bob");
  }
  
  @Test
  public void shouldKeepUppercaseForSuchWords() {
    assertInputBecomesExpected("BBB", "Bob-Bob-Bob");
  }
  
  @Test
  public void shouldNotModifyOriginalString() {
    String original = "original";
    String expected = "original";
    instanceToTest.convertToRobberLanguage(original);
    assertEquals(expected, original);
  }
}
