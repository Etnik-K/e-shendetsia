package app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Hasher {
    private static final int SALT_LENGTH = 32; // length of salt in bytes
    private static final int HASH_LENGTH = 256; // length of hash in bytes
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Gjeneron nje vlere salt te sigurt per perdorim ne hash
     *
     * @return nje string te enkoduar ne Base64 qe permban vleren e salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Krijon nje hash te salted nga fjalekalimi dhe salt i dhene
     *
     * @param password fjalekalimi qe do te hashohet
     * @param salt     vlera e salt qe do te perdoret
     * @return nje string qe permban hashin e kombinuar me salt
     */
    public static String generateSaltedHash(String password, String salt) {
        byte[] hash = hashWithSalt(password, salt);

        StringBuilder sb = new StringBuilder(SALT_LENGTH + HASH_LENGTH);

        byte [] saltBytes = salt.getBytes();
        for (byte saltByte : saltBytes){
            sb.append(String.format("%02x", saltByte));
        }
        for (byte hashByte : hash){
            sb.append(String.format("%02x", hashByte));
        }
        return sb.toString();
    }

    /**
     * Krahason nje tekst te thjeshte me nje hash te salted
     *
     * @param plaintext  teksti i thjeshte per krahasim
     * @param salt       vlera e salt e perdorur ne hash
     * @param saltedHash vlera e hash e salted per krahasim
     * @return true nese tekstet perputhen, false nese jo
     */
    public static boolean compareSaltedHash(String plaintext, String salt, String saltedHash) {
        String generatedPasswordHash = generateSaltedHash(plaintext, salt);
        return generatedPasswordHash.equals(saltedHash);
    }

    /**
     * Metode private qe perdoret per te krijuar hash me salt
     *
     * @param plaintext teksti qe do te hashohet
     * @param salt      vlera e salt qe do te perdoret
     * @return nje array byte[] qe permban hashin perfundimtar
     * @throws RuntimeException nese algoritmi i hash nuk gjendet
     */
    private static byte[] hashWithSalt(String plaintext, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.reset();
            digest.update(salt.getBytes());
            byte[] hash = digest.digest(plaintext.getBytes());
            for (int i = 0; i < 1000; i++) {
                digest.reset();
                hash = digest.digest(hash);
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(STR."Failed to hash plaintext: \{e.getMessage()}", e);
        }
    }
}