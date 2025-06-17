package repository.dao;

import model.ScapeRoom;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScapeRoomDAOSQLTest {

    @Test
    void findByIdMocked() {
        ScapeRoomDAO mockDao = mock(ScapeRoomDAO.class);
        ScapeRoom testRoom = new ScapeRoom(1, "Test Room", BigDecimal.valueOf(15));

        when(mockDao.findById(1)).thenReturn(testRoom);

        ScapeRoom result = mockDao.findById(1);
        assertNotNull(result);
        assertEquals("Test Room", result.getName());
        assertEquals(BigDecimal.valueOf(15), result.getTicketPrice());

        verify(mockDao).findById(1);
    }

    @Test
    void findAllMocked() {
        ScapeRoomDAO mockDao = mock(ScapeRoomDAO.class);

        List<ScapeRoom> testRooms = Arrays.asList(
                new ScapeRoom(1, "Scape Room Test1", BigDecimal.valueOf(15)),
                new ScapeRoom(2, "Scape Room Test2", BigDecimal.valueOf(8))
        );

        when(mockDao.findAll()).thenReturn(testRooms);

        List<ScapeRoom> result = mockDao.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Scape Room Test1", result.get(0).getName());
        assertEquals(BigDecimal.valueOf(15), result.get(0).getTicketPrice());
        assertEquals("Scape Room Test2", result.get(1).getName());
        assertEquals(BigDecimal.valueOf(8), result.get(1).getTicketPrice());

        verify(mockDao).findAll();
    }
}