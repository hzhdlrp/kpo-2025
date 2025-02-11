package hse.kpo.interfaces;

import hse.kpo.domains.Catamaran;

public interface ICatamaranFactory<TParams> {

    Catamaran createCatamaran(int speed, TParams engineParams);
}
