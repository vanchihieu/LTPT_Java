package app;

import entities.Candidate;
import entities.Certificate;
import entities.Position;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            try (Socket socket = new Socket("172.27.241.168", 8080)) {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    int choice = 0;
                    System.out.println("\nEnter your choice: \n" +
                            "1. List positions\n" +
                            "2. listCadidatesByCompanies\n" +
                            "3. listCandidatesWithLongestWorking\n" +
                            "4. addCandidate\n" +
                            "5. listYearsOfExperienceByPosition\n" +
                            "6. listCadidatesAndCertificates\n" +
                            "7. Exit\n");

                    choice = sc.nextInt();
                    out.writeInt(choice);
                    out.flush();

                    switch (choice) {
                        case 1:
                            List<Position> listPositions = (List<Position>) in.readObject();
                            listPositions.forEach(System.out::println);
                            break;
                        case 2:
                            Map<Candidate, Long> listCadidatesByCompanies = (Map<Candidate, Long>) in.readObject();
                            listCadidatesByCompanies.forEach((k, v) -> System.out.println(k + " " + v));
                            break;
                        case 3:
                            Map<Candidate, Position> listCandidatesWithLongestWorking = (Map<Candidate, Position>) in.readObject();
                            listCandidatesWithLongestWorking.forEach((k, v) -> System.out.println(k + " " + v));
                            break;
                        case 4:
                            boolean addCandidate = in.readBoolean();
                            System.out.println(addCandidate);
                            break;
                        case 5:
                            Map<Position, Integer> listYearsOfExperienceByPosition = (Map<Position, Integer>) in.readObject();
                            listYearsOfExperienceByPosition.forEach((k, v) -> System.out.println(k + " " + v));
                            break;
                        case 6:
                            Map<Candidate, Set<Certificate>> listCadidatesAndCertificates = (Map<Candidate, Set<Certificate>>) in.readObject();
                            listCadidatesAndCertificates.forEach((k, v) -> System.out.println(k + " " + v));
                            break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
