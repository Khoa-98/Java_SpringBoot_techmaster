package vn.techmaster.hellojpa;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;

import vn.techmaster.hellojpa.model.Employer;
import vn.techmaster.hellojpa.model.Job;

@SpringBootApplication
@Transactional
public class HellojpaApplication implements ApplicationRunner {

	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(HellojpaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Faker faker = new Faker();
		for (int i = 0; i < 100; i++) {
			var employer = Employer.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.website("http://" + faker.internet().domainName()).build();
			em.persist(employer);
		}

		for (int j = 0; j < 100; j++) {
			var job = Job.builder()
					.title(faker.name().fullName())
					.description(faker.company().name()).build();
			em.persist(job);
		}
		em.flush();

	}

}
