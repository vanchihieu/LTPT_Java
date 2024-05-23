import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import service.EntityManagerFactoryUtil;
import service.FoodService;
import service.ItemService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Test {
    private static EntityManagerFactoryUtil entityManagerFactoryUtil;
    private static EntityManager entityManager;

    @BeforeAll
    static void setup(){
        entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        entityManager = entityManagerFactoryUtil.getEntityManager();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("listItems")
    void test(){
        ItemService itemService = new ItemService(entityManager);
        assertNotNull(itemService.listItems("Eve"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("listFoodAndCost")
    void test2(){
        FoodService foodService = new FoodService(entityManager);
        assertNotNull(foodService.listFoodAndCost());
    }

    @AfterAll
    public static void afterAll() {
        entityManagerFactoryUtil.closeEntityManager();
        entityManagerFactoryUtil.closeEntityManagerFactory();
    }
}
