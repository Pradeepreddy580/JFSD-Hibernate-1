package skill6;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class StoreEmployee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int EmpId;
		String Ename;
		String Dept;
		int age;
		double salary;
		
		Scanner sc =new Scanner(System.in);
	
			SessionFactory f =new Configuration().configure().buildSessionFactory();
			
			Session s = f.openSession();
			
			Transaction tx = s.beginTransaction();
		
			/*
			create sequence EmpIdSeq start with 1000 increment by 1;
			*/
			System.out.println("Enter Employee name:");
			Ename = sc.next();
			
			System.out.println("Enter Department:");
			Dept = sc.next();
			
			System.out.println("Enter Age:");
			age = sc.nextInt();
			
			System.out.println("Enter Salary:");
			salary = sc.nextDouble();
	
			EmployeeDetails ed = new EmployeeDetails();
			
			ed.setEname(Ename);
			ed.setDept(Dept);
			ed.setAge(age);
			ed.setSalary(salary);
			
			s.save(ed);
			
			tx.commit();
			
			System.out.println("Values inserted");

		
	}

}
