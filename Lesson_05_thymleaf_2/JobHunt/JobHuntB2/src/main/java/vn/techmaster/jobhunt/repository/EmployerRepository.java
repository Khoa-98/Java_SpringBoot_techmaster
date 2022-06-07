package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    private ConcurrentHashMap<String, Employer> employers;

    public EmployerRepository() {
        employers = new ConcurrentHashMap<>();
        employers.put("0X-01",
                new Employer("0X-01", "CMC", null, " https://cmc.com.vn", "CMCcorporation@gmail.com"));
        employers.put("0X-02", new Employer("0X-02", "FPT", null, "https://fpt.com.vn", "FPT@gmail.com"));
        employers.put("0X-03",
                new Employer("0X-03", "Viettel", null, "https://vietteltelecom.vn/", "Viettel@gmail.com"));
    }

    public ConcurrentHashMap<String, Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(ConcurrentHashMap<String, Employer> employers) {
        this.employers = employers;
    }

    public List<Employer> getAllEmployers() {
        return employers.values().stream().collect(Collectors.toList());
    }

    public void saveEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public Employer findEmployerByID(String id) {
        return employers.get(id);
    }

    public void updateEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public void deleteEmployerById(String id) {
        employers.remove(id);
    }

}
