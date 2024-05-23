package app;

import entities.Candidate;
import entities.Certificate;
import entities.Position;
import jakarta.persistence.EntityManager;
import services.CandidateService;
import services.EntityManagerFactoryUtil;
import services.PositionService;
import services.CandidateService;
import services.EntityManagerFactoryUtil;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is running on port 8080");
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
    private CandidateService candidateService;
    private PositionService positionService;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        this.entityManager = entityManagerFactoryUtil.getEnManager();
        this.candidateService = new CandidateService(this.entityManager);
        this.positionService = new PositionService(this.entityManager);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            int choice = 0;
            while(true){
                choice = in.readInt();
                switch (choice){
                    case 1:
                        List<Position> listPositions = positionService.listPositions("Software Engineer", 12000.0, 16000.0);
                        out.writeObject(listPositions);
                        out.flush();
                        break;
                    case 2:
                        Map<Candidate, Long> listCadidatesByCompanies = candidateService.listCadidatesByCompanies();
                        out.writeObject(listCadidatesByCompanies);
                        out.flush();
                        break;
                    case 3:
                        Map<Candidate, Position> listCandidatesWithLongestWorking = candidateService.listCandidatesWithLongestWorking();
                        out.writeObject(listCandidatesWithLongestWorking);
                        out.flush();
                        break;
                    case 4:
                        boolean addCandidate = candidateService.addCandidate(
                                new Candidate("C124","SE" ,"Chi Hieu", 2003, "Male", "hieu@gmail.com", "0334225101",
                                        new Position("C201", "SE", "test", 2000.0, 3) ));
                        out.writeBoolean(addCandidate);
                        out.flush();
                        break;
                    case 5:
                        Map<Position, Integer> listYearsOfExperienceByPosition = positionService.listYearsOfExperienceByPosition("C101");
                        out.writeObject(listYearsOfExperienceByPosition);
                        out.flush();
                        break;
                    case 6:
                        Map<Candidate, Set<Certificate>> listCadidatesAndCertificates = candidateService.listCadidatesAndCertificates();
                        out.writeObject(listCadidatesAndCertificates);
                        out.flush();
                        break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.entityManagerFactoryUtil.closeEntityManager();
            this.entityManagerFactoryUtil.closeEntityManagerFactory();
        }
    }
}
