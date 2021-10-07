/**
 * Main class for the Morse Code Translator.
 *
 * @author alexeastlake
 */
class Main {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Translator translator = new Translator();
		GUI gui = new GUI(translator);
	}
}