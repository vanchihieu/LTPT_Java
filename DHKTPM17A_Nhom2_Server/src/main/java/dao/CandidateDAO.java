package dao;


import entities.Candidate;
import entities.Certificate;
import entities.Position;

import java.util.Map;
import java.util.Set;

public interface CandidateDAO {
    public Map<Candidate, Long> listCadidatesByCompanies();

    public Map<Candidate, Position> listCandidatesWithLongestWorking();

    public boolean addCandidate(Candidate candidate);

    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}
