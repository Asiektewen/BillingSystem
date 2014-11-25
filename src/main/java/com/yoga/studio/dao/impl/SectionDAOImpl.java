package com.yoga.studio.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yoga.studio.dao.SectionDAO;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Section;

@Repository
public class SectionDAOImpl implements SectionDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Section saveSection(Section section) {
		// TODO Auto-generated method stub

		return (Section) sessionFactory.getCurrentSession().merge(section);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> listSections() {
		// TODO Auto-generated method stub
		return (List<Section>) sessionFactory.getCurrentSession()
				.createCriteria(Section.class).list();
	}

	@Override
	public List<Section> listCurrentSections(long id) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(
				Customer.class, id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				" from Section as s where startDate>=?");
		// Query query =
		// sessionFactory.getCurrentSession().createQuery(" from Section s  s.enrollments as e with e.customer=?");
		// query.setParameter(0,customer);
		query.setParameter(0, new Date());
		List<Section> list = query.list();

		return list;
	}

	@Override
	public Section getSection(Long id) {
		// TODO Auto-generated method stub
		return (Section) sessionFactory.getCurrentSession().get(Section.class,
				id);

	}

	@Override
	public void deleteSection(Long id) {
		// TODO Auto-generated method stub
		Section section = getSection(id);
		if (section != null)
			sessionFactory.getCurrentSession().delete(section);
	}

	@Override
	public boolean checkLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
