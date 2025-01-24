package studying;

import studying.domains.Customer;
import studying.factories.HandCarFactory;
import studying.factories.PedalCarFactory;
import studying.params.EmptyEngineParams;
import studying.params.PedalEngineParams;
import studying.services.CarService;
import studying.services.CustomerStorage;
import studying.services.HseCarService;

import static studying.params.EmptyEngineParams.DEFAULT;

public class Main {

    private HseCarService hseCarService;

    public static void main(String[] args) {
        CustomerStorage customerStorage = new CustomerStorage();

        customerStorage.addCustomer(new Customer("customer1", 6, 4));
        customerStorage.addCustomer(new Customer("customer2", 4, 6));
        customerStorage.addCustomer(new Customer("customer1", 6, 6));
        customerStorage.addCustomer(new Customer("customer2", 4, 4));

        CarService carService = new CarService();
        HandCarFactory handCarFactory = new HandCarFactory();
        PedalCarFactory pedalCarFactory = new PedalCarFactory();

        carService.addCar(handCarFactory, DEFAULT);
        carService.addCar(handCarFactory, DEFAULT);

        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));


    }
}
