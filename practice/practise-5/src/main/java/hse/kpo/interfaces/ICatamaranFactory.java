package hse.kpo.interfaces;

import hse.kpo.domains.Catamaran;

public interface ICatamaranFactory<TParams> {

    /**
     *
     * @param speed
     * @param engineParams
     * @return
     */
    Catamaran createCatamaran(int speed, TParams engineParams);
}
