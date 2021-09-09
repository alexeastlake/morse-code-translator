import java.util.HashMap;
import java.util.Map;

class Translator {
	
	private Map<String, String> morseToCharacter;
	private Map<String, String> characterToMorse;
	
	public Translator() {
		morseToCharacter = new HashMap<String, String>();
		characterToMorse = new HashMap<String, String>();
		this.assignMorseToCharacterMap();
		this.assignCharacterToMorseMap();
	}
	
	public String translateMorse(String untranslated) {
		String[] morseStrings = untranslated.split(" ");
		StringBuilder translation = new StringBuilder();
		
		for (String s : morseStrings) {
			if (morseToCharacter.containsKey(s)) {
				translation.append(morseToCharacter.get(s));
			} else {
				translation.append("?");
			}
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