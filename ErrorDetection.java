package cnlab;

public class ErrorDetection {

    // Simple checksum-based error detection
    public static int calculateChecksum(String data) {
        int checksum = 0;

        for (char c : data.toCharArray()) {
            checksum += (int) c;
        }

        return checksum;
    }

    public static boolean validateChecksum(String data, int receivedChecksum) {
        int calculatedChecksum = calculateChecksum(data);

        return calculatedChecksum == receivedChecksum;
    }

    public static void main(String[] args) {
        String originalData = "Hello, Error Detection!";
        int checksum = calculateChecksum(originalData);

        System.out.println("Original Data: " + originalData);
        System.out.println("Checksum: " + checksum);

        // Simulate transmission with possible errors
        String receivedData = "Hello, Error Detection!";
        int receivedChecksum = calculateChecksum(receivedData);

        // Validate checksum to detect errors
        boolean isValid = validateChecksum(receivedData, receivedChecksum);

        if (isValid) {
            System.out.println("Received Data is valid.");
        } else {
            System.out.println("Error detected in received data.");
        }
    }
}
