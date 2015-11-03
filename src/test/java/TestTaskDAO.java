/**
 * Created by Home on 02.11.2015.
 */
import org.junit.Test;
import ua.artcode.dao.SimpleTaskDaoImpl;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.NoSuchTaskException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class TestTaskDAO {

    @Test(expected = NoSuchTaskException.class)
    public void testFindByIdException() throws NoSuchTaskException {
        CodingBatTaskContainer taskContainerMock = mock(CodingBatTaskContainer.class);
        SimpleTaskDaoImpl dao = new SimpleTaskDaoImpl(taskContainerMock);

        dao.findById("notId");
        verify(dao.findById("notId"));
    }

}
