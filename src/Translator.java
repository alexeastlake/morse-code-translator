import java.util.HashMap;
import java.util.Map;

/**
 * Translator class containing main functionality for the Morse Code Translator.
 *
 * @author alexeastlake
 */
class Translator {
	
	// Maps holding translations
	private Map<String, String> morseToCharacter;
	private Map<String, String> characterToMorse;
	
	/**
	 * Constructs a Translator instance.
	 */
	public Translator() {
		morseToCharacter = new HashMap<String, String>();
		characterToMorse = new HashMap<String, String>();
		this.assignMorseToCharacterMap();
		this.assignCharacterToMorseMap();
	}
	
	/**
	 * Translates the input String of Morse code into normal text.
	 *
	 * @param untranslated String of Morse code
	 */
	public String translateFromMorse(String untranslated) {
		String[] morseStrings = untranslated.replace("\n", " \n ").split(" ");
		StringBuilder translation = new StringBuilder();
		
		for (String s : morseStrings) {
			if (s.equals("\n")) {
				translation.append(s);
				continue;
			}
			
			if (morseToCharacter.containsKey(s)) {
				translation.append(morseToCharacter.get(s));
			} else {
				translation.append("?");
			}
		}
		
		return translation.toString().toUpperCase();
	}
	
	/**
	 * Translates the input String of normal text into Morse code.
	 *
	 * @param untranslated String of normal text
	 */
	public String translateToMorse(String untranslated) {
		char[] untranslatedChars = untranslated.toUpperCase().toCharArray();
		StringBuilder translation = new StringBuilder();
		boolean isNewLine = true;
		
		for (char c : untranslatedChars) {
			String cString = Character.toString(c);
			
			if (cString.equals("\n")) {
				translation.append(cString);
				isNewLine = true;
				continue;
			} else if (translation.length() != 0 && !isNewLine) {
				translation.append(" ");
			}
			
			if (characterToMorse.containsKey(cString)) {
				translation.append(characterToMorse.get(cString));
			} else {
				translation.append("?");
			}
			
			isNewLine = false;
		}
		
		return translation.toString();
	}
	
	/**
	 * Assigns strings of characters to a morse code key in the morseToCharacter map.
	 */
	private void assignMorseToCharacterMap() {
		// Alphabet
		morseToCharacter.put(".-", "A");
		morseToCharacter.put("-...", "B");
		morseToCharacter.put("-.-.", "C");
		morseToCharacter.put("-..", "D");
		morseToCharacter.put(".", "E");
		morseToCharacter.put("..-.", "F");
		morseToCharacter.put("--.", "G");
		morseToCharacter.put("....", "H");
		morseToCharacter.put("..", "I");
		morseToCharacter.put(".---", "J");
		morseToCharacter.put("-.-", "K");
		morseToCharacter.put(".-..", "L");
		morseToCharacter.put("--", "M");
		morseToCharacter.put("-.", "N");
		morseToCharacter.put("---", "O");
		morseToCharacter.put(".--.", "P");
		morseToCharacter.put("--.-", "Q");
		morseToCharacter.put(".-.", "R");
		morseToCharacter.put("...", "S");
		morseToCharacter.put("-", "T");
		morseToCharacter.put("..-", "U");
		morseToCharacter.put("...-", "V");
		morseToCharacter.put(".--", "W");
		morseToCharacter.put("-..-", "X");
		morseToCharacter.put("-.--", "Y");
		morseToCharacter.put("--..", "Z");
		
		// Numbers
		morseToCharacter.put(".----", "1");
		morseToCharacter.put("..---", "2");
		morseToCharacter.put("...--", "3");
		morseToCharacter.put("....-", "4");
		morseToCharacter.put(".....", "5");
		morseToCharacter.put("-....", "6");
		morseToCharacter.put("--...", "7");
		morseToCharacter.put("---..", "8");
		morseToCharacter.put("----.", "9");
		morseToCharacter.put("-----", "0");
		
		// Punctuation
		morseToCharacter.put(".-.-.-", ".");
		morseToCharacter.put("--..--", ",");
		morseToCharacter.put("/", " ");
	}
	
	/**
	 * Assigns morse code to a string key in the characterToMorse map.
	 */
	private void assignCharacterToMorseMap() {
		// Alphabet
		characterToMorse.put("A", ".-");
		characterToMorse.put("B", "-...");
		characterToMorse.put("C", "-.-.");
		characterToMorse.put("D", "-..");
		characterToMorse.put("E", ".");
		characterToMorse.put("F", "..-.");
		characterToMorse.put("G", "--.");
		characterToMorse.put("H", "....");
		characterToMorse.put("I", "..");
		characterToMorse.put("J", ".---");
		characterToMorse.put("K", "-.-");
		characterToMorse.put("L", ".-..");
		characterToMorse.put("M", "--");
		characterToMorse.put("N", "-.");
		characterToMorse.put("O", "---");
		characterToMorse.put("P", ".--.");
		characterToMorse.put("Q", "--.-");
		characterToMorse.put("R", ".-.");
		characterToMorse.put("S", "...");
		characterToMorse.put("T", "-");
		characterToMorse.put("U", "..-");
		characterToMorse.put("V", "...-");
		characterToMorse.put("W", ".--");
		characterToMorse.put("X", "-..-");
		characterToMorse.put("Y", "-.--");
		characterToMorse.put("Z", "--..");
		
		// Numbers
		characterToMorse.put("1", ".----");
		characterToMorse.put("2", "..---");
		characterToMorse.put("3", "...--");
		characterToMorse.put("4", "....-");
		characterToMorse.put("5", ".....");
		characterToMorse.put("6", "-....");
		characterToMorse.put("7", "--...");
		characterToMorse.put("8", "---..");
		characterToMorse.put("9", "----.");
		characterToMorse.put("0", "-----");
		
		// Punctuation
		characterToMorse.put(".", ".-.-.-");
		characterToMorse.put(",", "--..--");
		characterToMorse.put(" ", "/");
	}
}