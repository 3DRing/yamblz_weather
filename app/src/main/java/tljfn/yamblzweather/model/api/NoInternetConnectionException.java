package tljfn.yamblzweather.model.api;

/**
 * Created by ringov on 26.07.17.
 */

public class NoInternetConnectionException extends RuntimeException {
    public NoInternetConnectionException(String message) {
        super(message);
    }
}
