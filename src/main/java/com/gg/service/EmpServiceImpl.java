package com.gg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.exception.ResourceNotFoundException;
import com.gg.model.Emp;
import com.gg.repository.EmpRepository;
import com.gg.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepository empRepository;

	@Override
	public List<Emp> findAll() {
		List<Emp> emps = new ArrayList<>();
		empRepository.findAll().forEach(e -> emps.add(e));
		return emps;
	}

	@Override
	public Emp findById(int empno) {
		Emp emp = empRepository.findById(empno).orElseThrow(() -> new ResourceNotFoundException("Emp", "empno", empno));
		return emp;
	}

	@Override
	public void deleteById(int empno) {
		empRepository.deleteById(empno);
	}

	@Override
	public Emp save(Emp emp) {
		empRepository.save(emp);
		return emp;
	}

	@Override
	public List<Emp> findBySalBetween(int sal1, int sal2) {
		List<Emp> emps = empRepository.findBySalBetween(sal1, sal2);
		System.out.println(emps.size() + ">>>>>>>>>>>>>>>>" + sal1 + sal2);
		if (emps.size() > 0)
			return emps;
		else
			return null;
	}

	@Override
	public void updateById(int empno, Emp emp) {
		Emp e = empRepository.findById(empno).orElseThrow(() -> new ResourceNotFoundException("Emp", "empno", empno));
		e.setEname(emp.getEname());
		e.setSal(emp.getSal());
		empRepository.save(emp);
	}
}