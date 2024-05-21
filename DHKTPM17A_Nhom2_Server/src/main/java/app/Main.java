package app;

import entities.Candidate;
import entities.Position;
import services.CandidateService;
import services.EntityManagerFactoryUtil;
import services.PositionService;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryUtil managerFactoryUtil = new EntityManagerFactoryUtil();

        CandidateService candidateService = new CandidateService(managerFactoryUtil.getEnManager());
        PositionService positionService = new PositionService(managerFactoryUtil.getEnManager());
//        System.out.println(candidateService.listCadidatesByCompanies());
//        System.out.println(candidateService.listCandidatesWithLongestWorking());
//        System.out.println(candidateService.listCadidatesAndCertificates());
//        boolean addCandidate = candidateService.addCandidate(new Candidate("C123","SE" ,"Chi Hieu", 2003, "Male", "hieu@gmail.com", "0334225101", "P200"));

//        System.out.println(candidateService.addCandidate(
//                new Candidate("C124","SE" ,"Chi Hieu", 2003, "Male", "hieu@gmail.com", "0334225101",
//                        new Position("C200", "SE", "test", 2000.0, 3) )));
//        System.out.println( positionService.listYearsOfExperienceByPosition("C101"));
        System.out.println(positionService.listPositions("Software Engineer", 12000.0, 16000.0));
    }
}
