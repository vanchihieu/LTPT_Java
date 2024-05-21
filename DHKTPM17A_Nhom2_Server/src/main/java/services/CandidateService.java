package services;

import dao.CandidateDAO;
import entities.Candidate;
import entities.Certificate;
import entities.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CandidateService implements CandidateDAO {
    private EntityManager entityManager;

    public CandidateService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //  Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.
//    SELECTSELECT c.year_of_birth, c.candidate_id, c.description, c.email, c.full_name, c.gender, c.phone , COUNT(e.company_name) AS num_companies
//    FROM candidates c
//    JOIN experiences e ON c.candidate_id = e.candidate_id
//    GROUP BY SELECT c.year_of_birth, c.candidate_id, c.description, c.email, c.full_name, c.gender, c.phone
    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        String query = ("SELECT c.candidate_id ,COUNT(e.company_name) AS num_companies\n" +
                "FROM candidates c\n" +
                "JOIN experiences e ON c.candidate_id = e.candidate_id\n" +
                "GROUP BY c.candidate_id");

        List<?> resultList = entityManager.createNativeQuery(query).getResultList();

        Map<Candidate, Long> map = new HashMap<>();
        resultList.stream().map(o -> (Object[]) o).forEach(obj -> {
            String candidate_id = (String) obj[0];
            Candidate candidate = entityManager.find(Candidate.class, candidate_id);
            long numCompanies = (long) obj[1];

            map.put(candidate, numCompanies);
        });
        return map;
    }


//    c) Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.
//select candidate_id,position_id,TIMESTAMPDIFF(DAY, '2016-01-01', '2023-01-01') as days
//    from experiences as temp
//    where TIMESTAMPDIFF(DAY, '2016-01-01', '2023-01-01')   >= all   ( select TIMESTAMPDIFF(DAY,'2016-01-01', '2023-01-01')
//    from experiences as temp_b
//    where  temp_b.candidate_id = temp.candidate_id )

    @Override
    public Map<Candidate, Position> listCandidatesWithLongestWorking() {
        String query = "select candidate_id,position_id,TIMESTAMPDIFF(DAY, '2016-01-01', '2023-01-01') as days\n" +
                "from experiences as temp\n" +
                "where TIMESTAMPDIFF(DAY, '2016-01-01', '2023-01-01')   >= all   ( select TIMESTAMPDIFF(DAY,'2016-01-01', '2023-01-01')\n" +
                "from experiences as temp_b\n" +
                "where  temp_b.candidate_id = temp.candidate_id )";

        List<?> resultList = entityManager.createNativeQuery(query).getResultList();

        Map<Candidate, Position> map = new HashMap<>();
        resultList.stream().map(o -> (Object[]) o).forEach(obj -> {
            String candidate_id = (String) obj[0];
            Candidate candidate = entityManager.find(Candidate.class, candidate_id);
            String position_id = (String) obj[1];
            Position position = entityManager.find(Position.class, position_id);

            map.put(candidate, position);
        });
        return map;
    }

    // d) Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số.
    @Override
    public boolean addCandidate(Candidate candidate) {
        String candidateId = candidate.getCandidate_id();
        if (!candidateId.matches("C\\d{3,}")) {
            return false;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            if (candidate.getPosition() != null) {
                entityManager.persist(candidate.getPosition());
            }

            entityManager.persist(candidate);
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

    //    f) Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.
    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        List<Candidate> candidates = entityManager.createQuery("SELECT c FROM Candidate c", Candidate.class).getResultList();

        Map<Candidate, Set<Certificate>> map = new HashMap<>();
        for (Candidate candidate : candidates) {
            Set<Certificate> certificates = candidate.getCertificates();
            map.put(candidate, certificates);
        }
        return map;
    }


}
