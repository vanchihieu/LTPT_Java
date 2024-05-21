package dao;

import entities.Food;

import java.util.Map;

public interface FoodDAO {
   public boolean addFood(Food food);
   public Map<Food, Double> listFoodAndCost();

}
