package skill6;

import java.util.Scanner;

import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class Skill7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Display all employee details in orgsnization.");
		System.out.println("2. Display all nmaes of all employees whose age is grater than 40.");
		System.out.println("3. delete employee whose empid = 1005");
		System.out.println("4. Update the name of an employee with ****** whose empid = 1003");
		int n=sc.nextInt();
		switch(n) {
		case 1:diplay();
		break;
		case 2:displayAge();
		break;
		case 3:delete();
		break;
		case 4:update();
		break;
		}
	}

	private static void diplay() {
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
		
		String hql = "FROM EmployeeDetails";
		Query query = session.createQuery(hql);
		List list = ((org.hibernate.Query) query).list();
		
		Iterator<EmployeeDetails> iter = list.iterator();
		
		while(iter.hasNext()) {
			EmployeeDetails employee = iter.next();
				System.out.print("EMP ID: " + employee.getEmpId()); 
	            System.out.print("  First Name: " + employee.getEname()); 
	            System.out.print("  Department: " + employee.getDept()); 
	            System.out.print("  Age: " + employee.getAge()); 
	            System.out.print("  Salary: " + employee.getSalary()); 
	            System.out.println();
	     }
	         tx.commit();
	         session.clear();
	         session.close();
	}

	private static void displayAge() {
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
		
		String hql = "FROM EmployeeDetails where age>40";
		Query query = session.createQuery(hql);
		List list = ((org.hibernate.Query) query).list();
		Iterator<EmployeeDetails> iter = list.iterator();
		while(iter.hasNext()) {
			EmployeeDetails employee = iter.next();
	            System.out.println("Names whose age is Greater than 45: " + employee.getEname()); 
	            
	     }
	         tx.commit();
	         session.clear();
	         session.close();
		
	}

	private static void delete() {
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
		String hql = "DELETE FROM EmployeeDetails WHERE empid = :employee_id";
			Query query = session.createQuery(hql);
			query.setParameter("employee_id", 1005);
			int result = query.executeUpdate();
			
			System.out.println(result+"Row (or) rows effected");
			tx.commit();
	         session.clear();
	         session.close();
		
	}

	private static void update() {
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE EmployeeDetails set ename = :empname WHERE empid = :employee_id";
		String name="******";
		Query query = session.createQuery(hql);
		query.setParameter("empname", name);
		query.setParameter("employee_id", 1003);
		int result = query.executeUpdate();
		System.out.println(result +" Row (or) rows effected");
		tx.commit();
	    session.clear();
	    session.close();
		
	}

}
