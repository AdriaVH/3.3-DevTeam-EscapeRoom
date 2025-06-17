package repository.dao;

import model.ScapeRoom;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class ScapeRoomDAOSQLTest {

    @Test
    void findByIdMocked() {
            ScapeRoomDAO mockDao = Mockito.mock(ScapeRoomDAO.class);
            ScapeRoom testRoom = new ScapeRoom(1, "Test Room" , BigDecimal.valueOf(15));

            Mockito.when(mockDao.findById(1)).thenReturn(testRoom);

            ScapeRoom result = mockDao.findById(1);
            assertNotNull(result);
            assertEquals("Test Room" , result.getName());
            assertEquals(BigDecimal.valueOf(15) , result.getTicketPrice());

            Mockito.verify(mockDao).findById(1);
        }
    }