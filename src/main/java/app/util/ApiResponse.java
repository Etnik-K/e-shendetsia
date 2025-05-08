package app.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Një klasë gjenerike që përfaqëson përgjigjen e API-t.
 * Përdoret për të standardizuar formatin e përgjigjeve në të gjithë aplikacionin.
 *
 * @param <T> Lloji i të dhënave që do të kthehet
 */
@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String error;
}