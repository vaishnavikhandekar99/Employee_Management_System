package com.qsp.springbootemployee2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springbootemployee2.dao.EmployeeDao;
import com.qsp.springbootemployee2.dto.Employee;
import com.qsp.springbootemployee2.exception.DataNotFound;
import com.qsp.springbootemployee2.exception.EmailNotFound;
import com.qsp.springbootemployee2.exception.IdNotFound;
import com.qsp.springbootemployee2.exception.PhoneNotFound;
import com.qsp.springbootemployee2.util.ResponseStructure;

@Service
public class EmployeeService 
{
		@Autowired
		private EmployeeDao dao;

		public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) 
		{
			double salary = employee.getSalary();
			if (salary < 10000) {
				employee.setGrade('A');
			} else if (salary >= 10000 && salary < 20000) {
				employee.setGrade('B');
			} else if (salary >= 20000 && salary < 40000) {
				employee.setGrade('C');
			} else {
				employee.setGrade('D');
			}
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Data Saved Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
		}

		public ResponseEntity<ResponseStructure<Employee>> findById(int id) 
		{
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			Employee employee = dao.findEmployee(id);
			if (employee != null) 
			{
				structure.setMessage("Data Found");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
			} 
			else 
			{
				throw new IdNotFound("Id Not Found");
			}
		}

		public ResponseEntity<ResponseStructure<List<Employee>>> fetchAllEmployee() 
		{
			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
			List<Employee> list = dao.fetchAllEmployee();
			if (list.isEmpty()) 
			{
				throw new DataNotFound("data not found");
			} 
			else 
			{
				structure.setMessage("Data Found");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(list);
				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
			}
		}

		public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) 
		 {
			Employee employee = dao.deleteEmployee(id);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employee != null) 
			{
				structure.setMessage("Data Deleted Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} 
//			else 
//			{
//				structure.setMessage("Id Not Found ");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(employee);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//			}
			else
			{
				throw new IdNotFound("Id Not Found");
			}

		}

		public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, Employee employee) 
		{
			double salary = employee.getSalary();
			if (salary < 10000) 
			{
				employee.setGrade('A');
			} 
			else if (salary >= 10000 && salary < 20000) 
			{
				employee.setGrade('B');
			} 
			else if (salary >= 20000 && salary < 40000) 
			{
				employee.setGrade('C');
			} 
			else {
				employee.setGrade('D');
			}
			
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			Employee employees = dao.findEmployee(id);
			if(employees!=null)
			{
				structure.setMessage("Data Updated Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.updateEmployee(id, employee));
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			}
			else
			{
				throw new IdNotFound("Id Not Found");
			}	
		}

		public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email)
		{
			Employee employees = dao.findEmployee(id);
			//employees.setEmail(email);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employees != null) 
			{
				structure.setMessage("Email Updated Successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(employees);
				employees.setEmail(email);   
				dao.updateEmployee(id, employees);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} 
//			else 
//			{
//				structure.setMessage("Id Not Found ");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//			}
			else
			{
				throw new IdNotFound("Id Not Found");
			}	
		}

		public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) 
		 {
			Employee employee = dao.findEmployee(id);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employee != null) 
			 {
				structure.setMessage("Phone Updated Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(employee);
				employee.setPhone(phone);
				dao.updateEmployee(id, employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);

			 } 
//			else 
//			{
//				structure.setMessage("Id Not Found ");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(employee);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//			}
			else
			{
				throw new IdNotFound("Id Not Found");
			}
		}

		public ResponseEntity<ResponseStructure<Employee>> updateName(int id, String name) 
		{
			Employee employee = dao.findEmployee(id);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employee != null) 
			{
				structure.setMessage("Name Updated Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(employee);
				employee.setName(name);
				dao.updateEmployee(id, employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);

			}
//			else    
//			{
//				structure.setMessage("Id Not Found ");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(employee);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//			}
			else
			{
				throw new IdNotFound("Id Not Found");
			}

		}

		public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary)
		{
			Employee employee = dao.findEmployee(id);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employee != null) 
			{
				structure.setMessage("Salary Updated Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(employee);
				employee.setSalary(salary);
				
				double salary1 = employee.getSalary();
				if (salary1 < 10000) {
					employee.setGrade('A');
				} else if (salary1 >= 10000 && salary1 < 20000) {
					employee.setGrade('B');
				} else if (salary1 >= 20000 && salary1 < 40000) {
					employee.setGrade('C');
				} else {
					employee.setGrade('D');
				}
				dao.updateEmployee(id, employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);

			}
//			else 
//			{
//				structure.setMessage("Id Not Found ");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(employee);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//			}
			else
			{
				throw new IdNotFound("Id Not Found");
			}
		}

		public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) 
		{
			Employee employee = dao.findByEmail(email);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employee != null) 
			{
				structure.setMessage("Data Found");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
			} 
//			else
//			{
//				structure.setMessage("Data Not Found");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(employee);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//			}
			else
			{
				throw new EmailNotFound("Email Not Found");
				
			}
		}

		public ResponseEntity<ResponseStructure<Employee>> findByPhone(long phone) 
		{
			Employee employee = dao.findByPhone(phone);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			if (employee != null) 
			{
				structure.setMessage("Data Found");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
			} 
			else
			{
				throw new PhoneNotFound("Phone Not Found");
			}
		}

		public ResponseEntity<ResponseStructure<List<Employee>>> findBySalaryGreaterThan(double salary) 
		{
			List<Employee> employees = dao.findEmployeeBySalaryGreaterThan(salary);
			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
			if (employees.isEmpty()) 
			{
				throw new DataNotFound("Data Not Found");
			} 
			else
			{
				structure.setMessage("Data Found");
				structure.setStatus(HttpStatus.FOUND.value());
			    structure.setData(employees);
				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
			}
		}

		public ResponseEntity<ResponseStructure<List<Employee>>> findBySalaryLessThan(double salary) 
		{
			List<Employee> employees = dao.findEmployeeBySalaryLessThan(salary);
			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
			if (employees.isEmpty()) 
			{
				throw new DataNotFound("Data Not Found");
			}
//			else
//			{
//				structure.setMessage("Data Found");
//				structure.setStatus(HttpStatus.FOUND.value());
//				structure.setData((Employee) employees);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
//			}
			else
			{                                                              
				structure.setMessage("Data Found");
				structure.setStatus(HttpStatus.FOUND.value());
			    structure.setData(employees);
				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
			
			}
		
		}

	}


