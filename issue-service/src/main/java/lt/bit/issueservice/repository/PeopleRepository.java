package lt.bit.issueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.bit.issueservice.entity.People;

public interface PeopleRepository extends JpaRepository<People, Integer> {

	People findByUserId(Integer userId);
}
