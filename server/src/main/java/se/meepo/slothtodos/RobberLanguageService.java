package se.meepo.slothtodos;

import java.util.StringTokenizer;

public class RobberLanguageService implements IRobberLanguageService {

  private static String VOWELS = "AEIOUYÅÄÖaeiouyåäö";
  private static String CONSONANTS = "BCDFGHJKLMNPQRSTVWXZbcdfghjklmnpqrstvwxz";

  private boolean isVowel(char c) {
    return VOWELS.indexOf(c) != -1;
  }
  
  private boolean isConsonant(char c) {
    return CONSONANTS.indexOf(c) != -1;
  }

  private boolean isLetter(char c) {
    return isConsonant(c) || isVowel(c);
  }

  private String convertWord(String word) {
    char[] characters = word.toCharArray();
    String result = "";
    for (char character: characters) {
      if (isLetter(character)) {
        if (isVowel(character)) {
          result += character;
        } else {
          String robberConsonant = character + "o" + Character.toString(character).toLowerCase();
          if (!result.endsWith("-") && !result.isEmpty() && isLetter(result.charAt(result.length() - 1))) {
            result += "-";
          }
          result += robberConsonant + "-";
        }
      }
      else {
        result += character;
      }
    }

    if (result.endsWith("-")) {
      result = result.substring(0, result.length() - 1);
    }
    
    if (result.startsWith("-")) {
      result = result.substring(1, result.length());
    }

    return result;
  }

  @Override
  public String convertToRobberLanguage(String input) {
    if (input == null || input == "") {
      return input;
    }

    String wordDelimiters = "\n ";
    StringTokenizer tokenizer = new StringTokenizer(input, wordDelimiters, true);

    String result = "";

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      String converted = convertWord(token);
      result += converted;
    }

    return result;
  }
  
}
