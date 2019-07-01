package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewDemo {
    public static void main(String [] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //start Transaction
            session.beginTransaction();

            //get the course
            int theID=10;
            Course tempCourse = session.get(Course.class,theID);

            //print the course
            System.out.println("Deleting the course...");
            System.out.println(tempCourse);
            
            //print the course reviews
            System.out.println(tempCourse.getReviews());

            //delete the course
            session.delete(tempCourse);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally {
            //close the session and Factory
            session.close();
            factory.close();
        }
    }
}
