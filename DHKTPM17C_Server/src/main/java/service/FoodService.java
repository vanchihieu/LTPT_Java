package service;

import dao.FoodDAO;
import entities.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodService implements FoodDAO {
    private EntityManager entityManager;

    public FoodService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //    Thêm một món ăn mới. Trong đó, mã số của món phải bắt đầu là F và theo sau ít nhất 3 ký số.
    @Override
    public boolean addFood(Food food) {
        String foodId = food.getId();
        if (!foodId.matches("F\\d{3,}")) {
            return false;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(food);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    //    Tính giá gốc của từng món ăn sau khi chế biết thành phẩm. Kết quả sắp xếp giảm dần theo đơn giá gốc. Trong đó: Giá gốc món ăn = (số lượng nguyên liệu * đơn giá nguyên liệu) + (thời gian chuẩn bị + thời gian phục vụ) * 0.2$
    // select i.*, sum(ing.quantity * ing.price) + (i.preparation_time + i.serving_time) * 0.2 as cost
    // from foods i
    // join items it on it.id = i.id
    // join items_ingredients ig on it.id = ig.item_id
    // join ingredients ing on ig.ingredient_id = ing.ingredient_id
    // group by i.id
    // order by cost desc
    @Override
    public Map<Food, Double> listFoodAndCost() {
//        String query = "select i.id, sum(ing.quantity * ing.price) + (i.preparation_time + i.serving_time) * 0.2 as cost\n" +
//                "     from foods i\n" +
//                "     join items it on it.id = i.id\n" +
//                "     join items_ingredients ig on it.id = ig.item_id\n" +
//                "     join ingredients ing on ig.ingredient_id = ing.ingredient_id\n" +
//                "     group by i.id\n" +
//                "     order by cost desc";
//
//        List<?> resultList = entityManager.createNativeQuery(query).getResultList();
//
//        Map<Food, Double> map = new HashMap<>();
//        resultList.stream().map(o -> (Object[]) o).forEach(obj -> {
//            String foodId = (String) obj[0];
//            Food food = entityManager.find(Food.class, foodId);
//            double cost = ((Number) obj[1]).doubleValue();
//
//            map.put(food, cost);
//        });
//
//        return map;
        String jpql = "SELECT i.id, SUM(ing.quantity * ing.price) + (i.preparationTime + i.servingTime) * 0.2 "
                + "FROM Food i JOIN i.item it JOIN it.ingredient ing "
                + "GROUP BY i.id "
                + "ORDER BY SUM(ing.quantity * ing.price) + (i.preparationTime + i.servingTime) * 0.2 DESC";
        List<?> resultList = entityManager.createQuery(jpql).getResultList();

        Map<Food, Double> map = new HashMap<>();
        resultList.stream().map(o -> (Object[]) o).forEach(obj -> {
            String foodId = (String) obj[0];
            Food food = entityManager.find(Food.class, foodId);
            double cost = ((Number) obj[1]).doubleValue();

            map.put(food, cost);
        });

        return map;
    }
}
