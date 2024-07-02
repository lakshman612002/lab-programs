package cnlab;

public class CRCExample {

    // CRC-12 Polynomial: 0x80F (1100001111011 in binary)
    private static final int CRC12_POLYNOMIAL = 0x80F;

    // CRC-16 Polynomial: 0x8005 (11000000000000101 in binary)
    private static final int CRC16_POLYNOMIAL = 0x8005;

    // CRC CCITT Polynomial: 0x1021 (1000000100001 in binary)
    private static final int CRC_CCITT_POLYNOMIAL = 0x1021;

    public static int calculateCRC12(byte[] data) {
        return calculateCRC(data, CRC12_POLYNOMIAL, 12);
    }

    public static int calculateCRC16(byte[] data) {
        return calculateCRC(data, CRC16_POLYNOMIAL, 16);
    }

    public static int calculateCRC_CCITT(byte[] data) {
        return calculateCRC(data, CRC_CCITT_POLYNOMIAL, 16);
    }

    private static int calculateCRC(byte[] data, int polynomial, int bitWidth) {
        int crc = 0;

        for (byte b : data) {
            crc ^= (b & 0xFF) << (bitWidth - 8);

            for (int i = 0; i < 8; i++) {
                if ((crc & (1 << (bitWidth - 1))) != 0) {
                    crc = (crc << 1) ^ polynomial;
                } else {
                    crc <<= 1;
                }
            }
        }

        return crc & ((1 << bitWidth) - 1);
    }

    public static void main(String[] args) {
        String data = "Hello, CRC!";
        byte[] dataBytes = data.getBytes();

        // CRC-12
        int crc12Result = calculateCRC12(dataBytes);
        System.out.println("CRC-12: " + Integer.toHexString(crc12Result));

        // CRC-16
        int crc16Result = calculateCRC16(dataBytes);
        System.out.println("CRC-16: " + Integer.toHexString(crc16Result));

        // CRC CCITT
        int crcCCITTResult = calculateCRC_CCITT(dataBytes);
        System.out.println("CRC CCITT: " + Integer.toHexString(crcCCITTResult));
    }
}
