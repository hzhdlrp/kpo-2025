# Список терминов семинара
###### ServiceLocator
Определение: ServiceLocator — это паттерн проектирования, который предоставляет централизованный механизм для поиска и получения сервисов или зависимостей в приложении. Вместо того чтобы создавать зависимости напрямую, клиентский код запрашивает их у ServiceLocator.

Пример из жизни: Представьте, что вы находитесь в большом торговом центре и хотите найти конкретный магазин. Вместо того чтобы бродить по всему центру, вы обращаетесь к информационной стойке (ServiceLocator), которая подскажет, где находится нужный магазин.

Пример кода:

java
Copy
public class ServiceLocator {
private static Map<String, Object> services = new HashMap<>();

    public static void registerService(String key, Object service) {
        services.put(key, service);
    }

    public static Object getService(String key) {
        return services.get(key);
    }
}

// Использование
ServiceLocator.registerService("emailService", new EmailService());
EmailService emailService = (EmailService) ServiceLocator.getService("emailService");
2. DIP (Dependency Inversion Principle)
   Определение: Принцип инверсии зависимостей (DIP) — один из принципов SOLID, который гласит, что модули верхнего уровня не должны зависеть от модулей нижнего уровня. Оба типа модулей должны зависеть от абстракций. Абстракции не должны зависеть от деталей, а детали должны зависеть от абстракций.

Пример из жизни: Представьте, что вы используете пульт от телевизора (абстракция) для управления различными устройствами (телевизор, кондиционер, музыкальный центр). Вам не нужно знать, как работает каждое устройство, вы просто используете общий интерфейс (пульт).

Пример кода:

java
Copy
interface Switchable {
void turnOn();
void turnOff();
}

class LightBulb implements Switchable {
public void turnOn() { System.out.println("Light is on"); }
public void turnOff() { System.out.println("Light is off"); }
}

class Fan implements Switchable {
public void turnOn() { System.out.println("Fan is on"); }
public void turnOff() { System.out.println("Fan is off"); }
}

class Switch {
private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void operate() {
        device.turnOn();
    }
}
3. IoC (Inversion of Control)
   Определение: Inversion of Control (IoC) — это принцип, при котором управление потоком выполнения программы передается внешнему фреймворку или контейнеру. Вместо того чтобы управлять зависимостями и жизненным циклом объектов вручную, разработчик делегирует эту задачу контейнеру.

Пример из жизни: Представьте, что вы заказываете еду в ресторане. Вы не готовите еду сами (не управляете процессом), а доверяете это повару (контейнеру), который решает, как и когда приготовить блюдо.

Пример кода:

java
Copy
@Configuration
public class AppConfig {
@Bean
public MyService myService() {
return new MyServiceImpl();
}
}

public class MyApp {
public static void main(String[] args) {
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
MyService service = context.getBean(MyService.class);
service.doSomething();
}
}
4. Singleton
   Определение: Singleton — это паттерн проектирования, который гарантирует, что класс имеет только один экземпляр, и предоставляет глобальную точку доступа к этому экземпляру.

Пример из жизни: Президент страны — это пример Singleton. В любой момент времени может быть только один президент, и все обращаются к нему для решения важных вопросов.

Пример кода:

java
Copy
public class President {
private static President instance;

    private President() {}

    public static President getInstance() {
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }
}
5. Prototype
   Определение: Prototype — это паттерн проектирования, который позволяет создавать новые объекты путем копирования существующих объектов (прототипов), вместо создания новых через конструктор.

Пример из жизни: Клонирование овечки Долли — это пример использования прототипа. Ученые создали копию существующего организма, вместо того чтобы создавать новый с нуля.

Пример кода:

java
Copy
class Sheep implements Cloneable {
private String name;

    public Sheep(String name) {
        this.name = name;
    }

    public Sheep clone() {
        return new Sheep(this.name);
    }
}

Sheep original = new Sheep("Dolly");
Sheep cloned = original.clone();
6. Юнит-тесты (JUnit)
   Определение: Юнит-тесты — это тесты, которые проверяют корректность работы отдельных модулей или компонентов программы. JUnit — это популярный фреймворк для написания и запуска юнит-тестов в Java.

Пример из жизни: Представьте, что вы проверяете работу калькулятора. Вы тестируете каждую операцию (сложение, вычитание и т.д.) по отдельности, чтобы убедиться, что она работает правильно.

Пример кода:

java
Copy
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
@Test
void testAddition() {
Calculator calculator = new Calculator();
assertEquals(5, calculator.add(2, 3));
}
}
7. Mockito (mock + spy)
   Определение: Mockito — это библиотека для создания mock-объектов и spy-объектов в юнит-тестах. Mock-объекты имитируют поведение реальных объектов, а spy-объекты позволяют частично мокировать реальные объекты.

Пример из жизни: Представьте, что вы тестируете систему отправки писем. Вместо реальной отправки писем вы используете mock-объект, который имитирует отправку, чтобы не засорять почту.

Пример кода:

java
Copy
import static org.mockito.Mockito.*;

List<String> mockedList = mock(List.class);
when(mockedList.get(0)).thenReturn("first");

List<String> spyList = spy(new ArrayList<>());
spyList.add("first");
verify(spyList).add("first");
8. @SpringBootTest
   Определение: @SpringBootTest — это аннотация в Spring Boot, которая используется для запуска интеграционных тестов, загружая весь контекст приложения.

Пример из жизни: Представьте, что вы тестируете весь автомобиль, а не отдельные его части. Вы проверяете, как все компоненты работают вместе.

Пример кода:

java
Copy
@SpringBootTest
class MyIntegrationTest {
@Autowired
private MyService myService;

    @Test
    void testService() {
        assertNotNull(myService);
    }
}
9. @Autowired
   Определение: @Autowired — это аннотация в Spring, которая автоматически внедряет зависимости в компоненты. Spring контейнер сам находит подходящий бин и присваивает его полю, конструктору или методу.

Пример из жизни: Представьте, что вы нанимаете помощника (Spring), который сам находит и приносит вам нужные инструменты (зависимости) для работы.

Пример кода:

java
Copy
@Service
public class MyService {
@Autowired
private MyRepository repository;
}
10. @Component
    Определение: @Component — это аннотация в Spring, которая указывает, что класс является компонентом, управляемым Spring контейнером. Это общая аннотация для любых Spring-бинов.

Пример из жизни: Представьте, что вы собираете мебель из IKEA. Каждая деталь (компонент) имеет свою роль, и вы используете их для сборки конечного продукта.

Пример кода:

java
Copy
@Component
public class MyComponent {
public void doSomething() {
System.out.println("Doing something");
}