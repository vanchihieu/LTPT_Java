package app;

import entities.Food;
import entities.Item;
import entities.Type;
import service.EntityManagerFactoryUtil;
import service.FoodService;
import service.ItemService;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryUtil managerFactoryUtil = new EntityManagerFactoryUtil();

        FoodService foodService = new FoodService(managerFactoryUtil.getEnManager());
        ItemService itemService = new ItemService(managerFactoryUtil.getEnManager());

//        Food food = new Food();
//        food.setId("F900");
//        food.setType(Type.MAIN_COURSE);
//        food.setPreparationTime(70);
//        food.setServingTime(13);
//        food.setName("Hieu");
//        food.setDescription("mota");
//        food.setOnSpecial(true);
//        food.setPrice(30.0);
//        System.out.println(foodService.addFood(food));

//        System.out.println(foodService.addFood(new Food("MAIN_COURSE", 70, 13,
//                new Item("B500", "Hieu", 30.0, "mota", 0);
//        )));
//        foodService.listFoodAndCost().forEach( (k, v) -> System.out.println(k + " " + v));

        System.out.println(itemService.listItems("Eve"));
//        System.out.println(foodService.listFoodAndCost());
    }
}
