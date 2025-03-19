
import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;
import org.example.enums.PaymentTypes;
import org.example.exporting.Exporter;
import org.example.exporting.impl.CsvExporter;
import org.example.exporting.impl.JsonExporter;
import org.example.facades.UserFacade;
import org.example.importing.Importer;
import org.example.importing.impl.CsvImporter;
import org.example.importing.impl.JsonImporter;
import org.example.service.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest(classes = UserFacade.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class FunctionalityTests {

    @Autowired
    private UserFacade userFacade;

    private Importer jsonImporter;
    private Importer csvImporter;
    private Exporter jsonExporter;
    private Exporter csvExporter;

    @BeforeEach
    public void setUp() {
        // Инициализация импортеров и экспортеров
        jsonImporter = new JsonImporter();
        csvImporter = new CsvImporter();
        jsonExporter = new JsonExporter();
        csvExporter = new CsvExporter();

        // Очистка данных перед каждым тестом
        userFacade = new UserFacade();
    }

    @Test
    public void testCreateAccount() {
        userFacade.createAccount("Savings Account");
        assertNotNull(userFacade.getBankAccountFacade().getAccountByName("Savings Account"));
    }

    @Test
    public void testAddCategory() {
        userFacade.addCategory("Food", "expense");
        assertNotNull(userFacade.getCategoryByName("Food"));
    }

    @Test
    public void testDeleteCategory() {
        userFacade.addCategory("Food", "expense");
        userFacade.deleteCategory("Food");
        assertNull(userFacade.getCategoryByName("Food"));
    }

    @Test
    public void testDeleteAccount() {
        userFacade.createAccount("Savings Account");
        userFacade.deleteAccount("Savings Account");
        assertNull(userFacade.getBankAccountFacade().getAccountByName("Savings Account"));
    }

    @Test
    public void testChangeAccountName() {
        userFacade.createAccount("Savings Account");
        userFacade.changeAccountName("Savings Account", "Checking Account");
        assertNotNull(userFacade.getBankAccountFacade().getAccountByName("Checking Account"));
    }

    @Test
    public void testAddOperation() {
        userFacade.createAccount("Savings Account");
        userFacade.addCategory("Food", "income");
        int operationId = userFacade.addOperation("Savings Account", "Food", 100);
        assertNotEquals(-1, operationId);
    }

    @Test
    public void testDeleteOperationById() {
        userFacade.createAccount("Savings Account");
        userFacade.addCategory("Food", "income");
        int operationId = userFacade.addOperation("Savings Account", "Food", 100);
        userFacade.deleteOperationById(operationId);
        assertNull(userFacade.getOperationById(operationId));
    }

    @Test
    public void testChangeCategoryName() {
        userFacade.addCategory("Food", "expense");
        userFacade.changeCategoryName("Food", "Groceries");
        assertNotNull(userFacade.getCategoryByName("Groceries"));
    }

    @Test
    public void testExportAndImportAccountsJson() {
        userFacade.createAccount("Savings Account");
        userFacade.exportAccounts(jsonExporter);
        userFacade.importAccounts(jsonImporter, "accounts.json");
        assertNotNull(userFacade.getBankAccountFacade().getAccountByName("Savings Account"));
    }

    @Test
    public void testExportAndImportAccountsCsv() {
        userFacade.createAccount("Savings Account");
        userFacade.exportAccounts(csvExporter);
        userFacade.importAccounts(csvImporter, "accounts.csv");
        assertNotNull(userFacade.getBankAccountFacade().getAccountByName("Savings Account"));
    }

    @Test
    public void testExportAndImportCategoriesJson() {
        userFacade.addCategory("Food", "expense");
        userFacade.exportCategories(jsonExporter);
        userFacade.importCategories(jsonImporter, "categories.json");
        assertNotNull(userFacade.getCategoryByName("Food"));
    }

    @Test
    public void testExportAndImportCategoriesCsv() {
        userFacade.addCategory("Food", "expense");
        userFacade.exportCategories(csvExporter);
        userFacade.importCategories(csvImporter, "categories.csv");
        assertNotNull(userFacade.getCategoryByName("Food"));
    }

    @Test
    public void testExportAndImportOperationsJson() {
        userFacade.createAccount("Savings Account");
        userFacade.addCategory("Food", "expense");
        userFacade.addOperation("Savings Account", "Food", -100);
        userFacade.exportOperations(jsonExporter);
        List<Integer> ids = userFacade.importOperations(jsonImporter, "operations.json");
        ids.forEach(id -> {
            assertNotNull(userFacade.getOperationById(id));
        });
    }

    @Test
    public void testExportAndImportOperationsCsv() {
        userFacade.createAccount("Savings Account");
        userFacade.addCategory("Food", "income");
        int operationId = userFacade.addOperation("Savings Account", "Food", 100);
        userFacade.exportOperations(csvExporter);
        userFacade.importOperations(csvImporter, "operations.csv");
        assertNotNull(userFacade.getOperationById(operationId));
    }
}