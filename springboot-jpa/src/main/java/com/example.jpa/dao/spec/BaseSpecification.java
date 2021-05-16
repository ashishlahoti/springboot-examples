package com.abc.dao.spec;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T> {

	public Specification<T> findByField(String fieldName, String fieldValue) {
		return (root, query, cb) -> {
			return (fieldValue == null || fieldValue.isEmpty()) ? null : cb.equal(root.get(fieldName),
					fieldValue);
		};
	}
	
	public Specification<T> findBetweenDates(String fieldName, Date fromDate, Date toDate) {
		return (root, query, cb) -> {
			return (fromDate == null || toDate==null) ? null : cb.between(root.get(fieldName), toDate, fromDate);
		};
	}
}
