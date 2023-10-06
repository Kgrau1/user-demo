package matc.persistence;

import edu.matc.entity.Order;
import edu.matc.entity.User;
import edu.matc.persistence.OrderDao;
import edu.matc.persistence.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Order dao test.
 */
class OrderDaoTest {

    OrderDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new OrderDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all orders successfully.
     */
    @Test
    void getAllOrdersSuccess() {
        List<Order> orders = dao.getAllOrders();
        assertEquals(7, orders.size());
    }

    /**
     * Verifies an order is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Order retrievedOrder = dao.getById(2);
        assertNotNull(retrievedOrder);
        assertEquals("Books", retrievedOrder.getDescription());
    }

    /**
     * Verify successful insert of an order
     */
    @Test
    void insertSuccess() {

        //Gets a user from the database
        UserDao userDao = new UserDao();
        User user = userDao.getById(1);
        //Adds user to the order
        Order newOrder = new Order("Plates", user);
        //Adds order to the user
        user.addOrder(newOrder);

        int id = dao.insert(newOrder);

        assertNotEquals(0,id);
        Order insertedOrder = dao.getById(id);
        assertEquals("Plates", insertedOrder.getDescription());
        assertNotNull(insertedOrder.getUser());
        assertEquals("Joe", insertedOrder.getUser().getFirstName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/orderguide/html_single/Hibernate_Order_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful update of order
     */
    @Test
    void updateSuccess() {
        String description = "Books";
        Order orderToUpdate = dao.getById(2);
        orderToUpdate.setDescription(description);
        dao.saveOrUpdate(orderToUpdate);
        Order retrievedOrder = dao.getById(2);
        assertEquals(description, retrievedOrder.getDescription());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Order> orders = dao.getByPropertyEqual("description", "Books");
        assertEquals(1, orders.size());
        assertEquals(2, orders.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Order> orders = dao.getByPropertyLike("description", "b");
        assertEquals(3, orders.size());
    }
}