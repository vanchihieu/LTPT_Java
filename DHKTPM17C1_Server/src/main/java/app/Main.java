package app;

import service.EntityManagerFactoryUtil;
import service.FoodService;
import service.ItemService;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil();

        FoodService foodService = new FoodService((entityManagerFactoryUtil.getEntityManager()));
        ItemService itemService = new ItemService((entityManagerFactoryUtil.getEntityManager()));
        System.out.println(itemService.listItems("Eve"));
//        System.out.println(foodService.listFoodAndCost());
    }
}
