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

    @Override
    public Map<Food, Double> listFoodAndCost() {
        String jpql = "select f.id, sum(ig.quantity * ig.price) + (f.preparationTime + f.servingTime) * 0.2 " +
                " from Food f join f.ingredients ig " +
                "group by f.id " +
                "order by sum(ig.quantity * ig.price) + (f.preparationTime + f.servingTime) * 0.2 desc";
        List<?> resultList = entityManager.createQuery(jpql).getResultList();

        Map<Food, Double> map = new HashMap<>();
        resultList.stream().map((record) -> (Object[]) record).forEach((record) -> {
            String foodId = (String) record[0];
            Food food = entityManager.find(Food.class, foodId);
            Double cost = (Double) record[1];

            map.put(food, cost);
        });

        return map;
    }
}
