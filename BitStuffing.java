package cnlab;


public class BitStuffing {

    public static String bitStuffing(String data) {
        StringBuilder stuffedData = new StringBuilder("01111110");

        for (char bit : data.toCharArray()) {
            stuffedData.append(bit);
            if (stuffedData.substring(stuffedData.length() - 6).equals("111110")) {
                stuffedData.append('0');
            }
        }

        stuffedData.append("01111110");
        return stuffedData.toString();
    }

    public static String bitDestuffing(String stuffedData) {
        StringBuilder destuffedData = new StringBuilder();
        int countOnes = 0;

        for (char bit : stuffedData.substring(8, stuffedData.length() - 8).toCharArray()) {
            destuffedData.append(bit);
            if (bit == '1') {
                countOnes++;
                if (countOnes == 5) {
                    countOnes = 0;
                    continue;  // Skip the extra '0'
                }
            } else {
                countOnes = 0;
            }
        }

        return destuffedData.toString();
    }

    public static void main(String[] args) {
        String data = "110111110";

        String stuffedData = bitStuffing(data);
        System.out.println("Stuffed Data: " + stuffedData);

        String destuffedData = bitDestuffing(stuffedData);
        System.out.println("Destuffed Data: " + destuffedData);
    }
}

