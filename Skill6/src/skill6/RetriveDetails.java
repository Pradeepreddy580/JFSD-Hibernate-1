package skill6;

import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.persistence.Query;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class RetriveDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter 1 to 'retrieve the details of an employee whose empid =1001'");
		System.out.println("Enter 2 to 'update the salary of an employee whose empid = 1004 with "
				+ "average of all the employee salaries'");
		System.out.println("Enter 3 to 'delete an employee record from the table whose salary is minimum'");
		int value = sc.nextInt();
		switch(value) {
			case 1 : empDetails();
			break;
			case 2 : UpdateDetails();
			break;
			case 3 : DeleteDetails();
			break;
		}
			
	}
	static void empDetails() {
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
	         String sql = "SELECT * FROM employeedetails WHERE EmpId = 1001";
	         
	         SQLQuery query = session.createSQLQuery(sql);
	         query.addEntity(EmployeeDetails.class);
	         List employees = query.list();
	 		

	         for (Iterator iter = employees.iterator(); iter.hasNext();){
	            EmployeeDetails employee = (EmployeeDetails) iter.next(); 
	            System.out.print("First Name: " + employee.getEname()); 
	            System.out.print("  Department: " + employee.getDept()); 
	            System.out.print("  Age: " + employee.getAge()); 
	            System.out.print("  Salary: " + employee.getSalary()); 
	         }
	         tx.commit();
	         session.clear();
	         session.close();
	    
	}
	static void UpdateDetails() {
		
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query qu=session.createSQLQuery("SELECT AVG(Salary) FROM EmployeeDetails");
		List list = ((org.hibernate.Query) qu).list();
		BigDecimal amount = (BigDecimal)list.get(0);
		String sal = amount.toString();
		System.out.println(sal);
		int empid = 1004;
		String qryString = "update EmployeeDetails set salary =:sal where empid=:empid";
        Query query2 = session.createQuery(qryString);
        query2.setParameter("sal", Double.parseDouble(sal));
        query2.setParameter("empid", empid);
        query2.executeUpdate();
        System.out.println("Average Salary is : "+sal);
		System.out.println("Update completed");

        tx.commit();
        session.clear();
        session.close();
	}
	static void DeleteDetails() {
		
		SessionFactory f = new Configuration().configure().buildSessionFactory();
		Session session =f.openSession();
		
		Transaction tx = session.beginTransaction();
		
			Query qu=session.createSQLQuery("SELECT min(Salary) FROM EmployeeDetails");
			List list = ((org.hibernate.Query) qu).list();
			BigDecimal amount = (BigDecimal)list.get(0);
			String sal = amount.toString();
			System.out.println(sal);
			
	         String qryString = "delete from EmployeeDetails where Salary =:sal";
	         Query query2 = session.createQuery(qryString);
	         query2.setParameter("sal", Double.parseDouble(sal));
	         query2.executeUpdate();
	         System.out.println("Minimum Salary is : "+sal);
	         System.out.println("Delete Successfull");
	         
	         tx.commit();
	         session.clear();
	         session.close(); 
	}

}
