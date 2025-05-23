package edu.unipr.eshendetsia.service.interfaces;

public interface HasherService {

    /**
     * Gjeneron nje vlere salt te sigurt per perdorim ne hash
     *
     * @return nje string te enkoduar ne Base64 qe permban vleren e salt
     */
    String generateSalt();

    /**
     * Krijon nje hash te salted nga fjalekalimi dhe salt i dhene
     *
     * @param password fjalekalimi qe do te hashohet
     * @param salt     vlera e salt qe do te perdoret
     * @return nje string qe permban hashin e kombinuar me salt
     */
     String generateSaltedHash(String password, String salt);

    /**
     * Krahason nje tekst te thjeshte me nje hash te salted
     *
     * @param plaintext  teksti i thjeshte per krahasim
     * @param salt       vlera e salt e perdorur ne hash
     * @param saltedHash vlera e hash e salted per krahasim
     * @return true nese tekstet perputhen, false nese jo
     */
    boolean compareSaltedHash(String plaintext, String salt, String saltedHash);
}
