package services;

import dao.PositionDAO;
import entities.Position;
import jakarta.persistence.EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionService implements PositionDAO {
    private EntityManager entityManager;

    public PositionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối) và mức lương khoảng từ,kết quả sắp xếp theo tên vị trí công việc.
    // select * from positions where name like '%name%' and salary between salaryFrom and salaryTo order by name;
    @Override
    public List<Position> listPositions(String name, Double salaryFrom, Double salaryTo) {
//        return entityManager.createNativeQuery("SELECT * FROM positions WHERE name like :name AND salary BETWEEN :salaryFrom AND :salaryTo ORDER BY name", Position.class)
//                .setParameter("name", "%" + name + "%")
//                .setParameter("salaryFrom", salaryFrom)
//                .setParameter("salaryTo", salaryTo)
//                .getResultList();
        return entityManager.createQuery("SELECT p FROM Position p WHERE p.name like :name AND p.salary BETWEEN :salaryFrom AND :salaryTo ORDER BY p.name", Position.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("salaryFrom", salaryFrom)
                .setParameter("salaryTo", salaryTo)
                .getResultList();
    }

//    e) Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.
    // select position_id, TIMESTAMPDIFF(YEAR, start_date, end_date) as years
    // from experiences
    // where candidate_id = 'candidateID';
    // group by position_id;

    @Override
    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
        String query = "SELECT position_id, TIMESTAMPDIFF(YEAR, from_date, to_date) as years\n" +
                "FROM experiences\n" +
                "WHERE candidate_id = :candidateID\n" +
                "GROUP BY position_id";

        List<?> resultList = entityManager.createNativeQuery(query).setParameter("candidateID", candidateID).getResultList();

        Map<Position, Integer> map = new HashMap<>();
        resultList.stream().map(o -> (Object[]) o).forEach(obj -> {
            String position_id = (String) obj[0];
            Position position = entityManager.find(Position.class, position_id);
            int years = ((Number) obj[1]).intValue();

            map.put(position, years);
        });

        return map;
    }


}
