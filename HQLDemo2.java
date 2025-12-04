package LearningGit101;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;
import java.util.Scanner;

public class HQLDemo2
{
    public static void login3()
    {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        System.out.println("Session Factory created");

        Session session = sessionFactory.openSession();
        System.out.println("Session created");

        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<Users> query = cb.createQuery(Users.class);
        JpaRoot<Users> table = query.from(Users.class);

        System.out.println("Enter Phone no. :");
        Scanner scn = new Scanner(System.in);
        String phone = scn.next();
        System.out.println("Enter password");
        String pass = scn.next();
        JpaPredicate condition1 = cb.equal(table.get("mobile"), phone);
        JpaPredicate condition2 = cb.equal(table.get("password"), pass);

        query.select(table).where(condition1, condition2);
        List<Users> users = session.createQuery(query).getResultList();
        for(Users u: users)
            System.out.println(u);
        System.out.println("Execution completed...");
    }
    public static void main(String[] args)
    {
        login3();
    }
}
