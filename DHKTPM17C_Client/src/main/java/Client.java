import entities.Food;
import entities.Item;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            try(Socket socket = new Socket("172.27.241.168", 7000)){
                ObjectOutputStream out = new ObjectOutputStream((socket.getOutputStream()));
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                while (true){
                    int choice = 0;
                    System.out.println("\nEnter your choice: \n" +
                            "1. addFood\n" +
                            "2. listItems\n" +
                            "3. listFoodAndCost\n" +
                            "4. Exit\n");
                    choice = sc.nextInt();
                    out.writeInt(choice);
                    out.flush();

                    switch (choice){
                        case 1:
//                            boolean result = in.readBoolean();
//                            if(result){
//                                System.out.println("Food added successfully");
//                            }else{
//                                System.out.println("Food not added");
//                            }
                            break;
                        case 2:
                            List<Item> listItems = (List<Item>) in.readObject();
                            listItems.forEach(System.out::println);
                            break;
                        case 3:
                            Map<Food, Double> listFoodAndCost = (Map<Food, Double>) in.readObject();
                            listFoodAndCost.forEach((k, v) -> System.out.println(k + " " + v));
                            break;
                        case 4:
                            System.exit(0);
                            break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
