package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.ICatamaranFactory;
import hse.kpo.interfaces.IEngine;
import hse.kpo.params.EmptyEngineParams;

public class CatamaranFactory implements ICatamaranFactory<EmptyEngineParams> {

    /**
     *
     * @param speed
     * @param params
     * @return
     */
    @Override
    public Catamaran createCatamaran(int speed, EmptyEngineParams params) {
        HandEngine engine = new HandEngine();
        return new Catamaran(speed, engine);
    }
}
