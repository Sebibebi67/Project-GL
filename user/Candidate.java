package user;

public abstract class Candidate implements Role{

    private CandidateStatus candidateStatus;

    public Candidate(){
        candidateStatus = null;
    }
}