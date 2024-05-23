package app;

import entities.Food;
import entities.Item;
import entities.Type;
import jakarta.persistence.EntityManager;
import service.EntityManagerFactoryUtil;
import service.FoodService;
import service.ItemService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("Server is running on port 7000");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread t = new Thread((new ClientHandler(clientSocket)));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private EntityManagerFactoryUtil entityManagerFactoryUtil;
    private EntityManager entityManager;
    private FoodService foodService;
    private ItemService itemService;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        this.entityManager = entityManagerFactoryUtil.getEnManager();
        this.foodService = new FoodService(this.entityManager);
        this.itemService = new ItemService(this.entityManager);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            int choice = 0;
            while (true) {
                choice = in.readInt();
                switch (choice) {
                    case 1:
                        Food food = new Food();
//                        food.setId("F940");
                        food.setType(Type.DESSERT);
                        food.setPreparationTime(270);
                        food.setServingTime(413);
//                        food.setName("Be Em");
//                        food.setDescription("my mom");
//                        food.setOnSpecial(true);
//                        food.setPrice(30.0);
                        System.out.println(food);
                        boolean addFood = foodService.addFood(food);
                        out.writeBoolean(addFood);
                        out.flush();
                        break;
                    case 2:
                        List<Item> listItems = itemService.listItems("Anna");
                        out.writeObject(listItems);
                        out.flush();
                        break;
                    case 3:
                        Map<Food, Double> listFoodAndCost = foodService.listFoodAndCost();
                        out.writeObject(listFoodAndCost);
                        out.flush();
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.entityManagerFactoryUtil.closeEntityManagerFactory();
            this.entityManagerFactoryUtil.closeEntityManager();
        }
    }
}