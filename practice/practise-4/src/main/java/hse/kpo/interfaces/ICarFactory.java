package hse.kpo.interfaces;

import hse.kpo.domains.Car;
import org.springframework.stereotype.Service;

/**
 *
 * @param <TParams>
 */

public interface ICarFactory<TParams> {
    /**
     *
     * @param carParams
     * @param carNumber
     * @return
     */
    Car createCar(TParams carParams, int carNumber);
}
