package cnlab;


	public class characterStuffing {

	    public static String characterStuffing(String data, char controlCharacter) {
	        StringBuilder stuffedData = new StringBuilder();
	        stuffedData.append(controlCharacter);

	        for (char character : data.toCharArray()) {
	            if (character == controlCharacter) {
	                stuffedData.append(controlCharacter);  // Double the control character
	            }
	            stuffedData.append(character);
	        }

	        stuffedData.append(controlCharacter);  // End control character
	        return stuffedData.toString();
	    }

	    public static String characterDestuffing(String stuffedData, char controlCharacter) {
	        StringBuilder destuffedData = new StringBuilder();
	        boolean isStuffed = false;

	        for (char character : stuffedData.toCharArray()) {
	            if (character == controlCharacter) {
	                isStuffed = !isStuffed;
	            } else {
	                if (isStuffed) {
	                    destuffedData.append(character);
	                } else {
	                    if (character != controlCharacter) {  // Ignore single control characters
	                        destuffedData.append(character);
	                    }
	                }
	            }
	        }

	        return destuffedData.toString();
	    }

	    public static void main(String[] args) {
	        String data = "AABBBCCC";
	        char controlCharacter = 'B';

	        String stuffedData = characterStuffing(data, controlCharacter);
	        System.out.println("Stuffed Data: " + stuffedData);

	        String destuffedData = characterDestuffing(stuffedData, controlCharacter);
	        System.out.println("Destuffed Data: " + destuffedData);
	    }
	}



