package hse.kpo.params;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 */

public record EmptyEngineParams() {
    /**
     *
     */
    public static final EmptyEngineParams DEFAULT = new EmptyEngineParams();
}
