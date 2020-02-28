package com.practice;

import com.google.common.collect.Sets;
import com.winston.practice.DemoApplication;
import com.winston.practice.hibernate.entity.CourseEntity;
import com.winston.practice.hibernate.entity.StudentEntity;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

	@PersistenceContext
	private EntityManager entityManager;

	public Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}

	@Test
	@Transactional
	@Rollback(false)
	void addCourse() {
		CourseEntity coursecEntity = CourseEntity.builder()
		.cid(300L)
		.name("math")
		.build();
		getCurrentSession().merge(coursecEntity);
	}

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() {

		CourseEntity coursecEntity = CourseEntity.builder()
		.cid(300L)
		.name("math")
		.build();

		StudentEntity xiaoming = StudentEntity.builder()
		.sid(9922L)
		.name("zhuzhu")
		.courses(Sets.newHashSet(coursecEntity))
		.compulsoryCourses(Sets.newHashSet(300L))
		.build();

		getCurrentSession().merge(xiaoming);
	}

}
