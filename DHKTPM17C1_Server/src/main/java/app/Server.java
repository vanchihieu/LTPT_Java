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
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Server is running on port 6000");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread t = new Thread(new ClientHandler(clientSocket));
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
    private ItemService itemService;
    private FoodService foodService;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        this.entityManager = entityManagerFactoryUtil.getEntityManager();
        this.foodService = new FoodService(this.entityManager);
        this.itemService = new ItemService(this.entityManager);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while (true){
                int choice = 0;
                choice = in.readInt();
                switch (choice){
                    case 1:
                        Food food = new Food();
                        food.setId("C245");
                        food.setDescription("Milk");
                        food.setPrice(20.0);
                        food.setOnSpecial(true);
                        food.setName("Milk");
                        food.setServingTime(30);
                        food.setPreparationTime(20);
                        food.setType(Type.APPETIZER);
                        boolean addFood = foodService.addFood(food);

                        out.writeBoolean(addFood);
                        out.flush();
                        break;
                    case 2:
//                        String supplierName = in.readUTF();
                        out.writeObject(itemService.listItems("Eve"));
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