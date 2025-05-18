package edu.unipr.eshendetsia.util;

/**
 * Percakton metodat e ndryshme HTTP qe perdoren ne aplikacion.
 * Lista e metodave mbeshtet protokollin HTTP/1.1
 */
public enum HttpMethod {
    /**
     * Merr te dhena nga serveri
     */
    GET,
    /**
     * Dergon te dhena te reja ne server
     */
    POST,
    /**
     * Perditeson te gjitha te dhenat ekzistuese
     */
    PUT,
    /**
     * Perditeson pjeserisht te dhenat ekzistuese
     */
    PATCH,
    /**
     * Fshin te dhenat nga serveri
     */
    DELETE
}
