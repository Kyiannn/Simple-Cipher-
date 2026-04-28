import java.util.Scanner;
import java.util.ArrayList;

public class EncryptionChat {

    public static String caesarEncrypt(String text, int shift) {
        String result = "";
        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result = result + (char)((c - 'A' + shift) % 26 + 'A');
            } else {
                result = result + c;
            }
        }
        return result;
    }

    public static String caesarDecrypt(String text, int shift) {
        String result = "";
        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result = result + (char)((c - 'A' - shift + 26) % 26 + 'A');
            } else {
                result = result + c;
            }
        }
        return result;
    }

    public static String vigenereEncrypt(String text, String key) {
        String result = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(j % key.length()) - 'A';
                result = result + (char)((c - 'A' + shift) % 26 + 'A');
                j++;
            } else {
                result = result + c;
            }
        }
        return result;
    }

    public static String vigenereDecrypt(String text, String key) {
        String result = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(j % key.length()) - 'A';
                result = result + (char)((c - 'A' - shift + 26) % 26 + 'A');
                j++;
            } else {
                result = result + c;
            }
        }
        return result;
    }

    public static String railFenceEncrypt(String text, int rails) {
        text = text.toUpperCase();
        String[] fence = new String[rails];

        for (int i = 0; i < rails; i++) {
            fence[i] = "";
        }

        int row = 0;
        int direction = 1;

        for (int i = 0; i < text.length(); i++) {
            fence[row] = fence[row] + text.charAt(i);
            if (row == 0) {
                direction = 1;
            } else if (row == rails - 1) {
                direction = -1;
            }
            row = row + direction;
        }

        String result = "";
        for (int i = 0; i < rails; i++) {
            result = result + fence[i];
        }
        return result;
    }

    public static String railFenceDecrypt(String text, int rails) {
        int n = text.length();
        int[] indexOrder = new int[n];
        int row = 0;
        int direction = 1;

        for (int i = 0; i < n; i++) {
            indexOrder[i] = row;
            if (row == 0) {
                direction = 1;
            } else if (row == rails - 1) {
                direction = -1;
            }
            row = row + direction;
        }

        char[] result = new char[n];
        int pos = 0;

        for (int r = 0; r < rails; r++) {
            for (int i = 0; i < n; i++) {
                if (indexOrder[i] == r) {
                    result[i] = text.charAt(pos);
                    pos++;
                }
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<String> chatHistory = new ArrayList<String>();

        System.out.println("==============================");
        System.out.println("  Conventional Encryption Chat");
        System.out.println("==============================");

        while (true) {

             System.out.println("\n[1] Caesar  [2] Vigenere  [3] Rail Fence  [0] Quit");
            System.out.print("Choose cipher: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }


            System.out.print("Enter key: ");
            String key = input.nextLine();

            System.out.print("User A > ");
            String message = input.nextLine();

            String encrypted = "";
            String decrypted = "";

            if (choice == 1) {
                int shift = Integer.parseInt(key);
                encrypted = caesarEncrypt(message, shift);
                decrypted = caesarDecrypt(encrypted, shift);

            } else if (choice == 2) {
                encrypted = vigenereEncrypt(message, key);
                decrypted = vigenereDecrypt(encrypted, key);

            } else if (choice == 3) {
                int rails = Integer.parseInt(key);
                encrypted = railFenceEncrypt(message, rails);
                decrypted = railFenceDecrypt(encrypted, rails);

            } else {
                System.out.println("Invalid choice.");
                continue;
            }

            System.out.println("Encrypted  > " + encrypted);
            System.out.println("User B received...");
            System.out.println("Decrypted  > " + decrypted);

            chatHistory.add("Plain: " + message.toUpperCase() + " | Encrypted: " + encrypted + " | Decrypted: " + decrypted);
        }
    }

}