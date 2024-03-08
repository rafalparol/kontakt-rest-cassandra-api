package inc.temp.right.always.restcassandraapi;

import inc.temp.right.always.restcassandraapi.controllers.AnomaliesController;
import inc.temp.right.always.restcassandraapi.dto.AnomaliesCount;
import inc.temp.right.always.restcassandraapi.model.AnomalyByRoom;
import inc.temp.right.always.restcassandraapi.model.AnomalyByThermometer;
import inc.temp.right.always.restcassandraapi.model.AnomalyByTime;
import inc.temp.right.always.restcassandraapi.repositories.AnomalyByRoomRepository;
import inc.temp.right.always.restcassandraapi.repositories.AnomalyByThermometerRepository;
import inc.temp.right.always.restcassandraapi.repositories.AnomalyByTimeRepository;
import inc.temp.right.always.restcassandraapi.services.AnomaliesService;
import inc.temp.right.always.restcassandraapi.util.DateAndTimeConversionManager;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class AnomaliesControllerTests {
    @Test
    public void getAnomaliesByRoom_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyByRoomRepository anomalyByRoomRepository = mock(AnomalyByRoomRepository.class);
        ArrayList<AnomalyByRoom> mockedResult = new ArrayList<AnomalyByRoom>();
        mockedResult.add(new AnomalyByRoom("room-1", Timestamp.valueOf("2024-02-01 00:00:00").toInstant(), "thermometer-1", 31.0));
        mockedResult.add(new AnomalyByRoom("room-1", Timestamp.valueOf("2024-02-02 00:00:00").toInstant(), "thermometer-1", 32.0));
        when(anomalyByRoomRepository.findAllByRoomId("room-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyByRoomRepository(anomalyByRoomRepository);

        List<AnomalyByRoom> returnedResult = anomaliesController.getAnomaliesByRoom("room-1");

        verify(anomalyByRoomRepository, times(1)).findAllByRoomId("room-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by room.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByRoom_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyByRoomRepository anomalyByRoomRepository = mock(AnomalyByRoomRepository.class);
        ArrayList<AnomalyByRoom> mockedResult = new ArrayList<AnomalyByRoom>();
        when(anomalyByRoomRepository.findAllByRoomId("room-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyByRoomRepository(anomalyByRoomRepository);

        List<AnomalyByRoom> returnedResult = anomaliesController.getAnomaliesByRoom("room-1");

        verify(anomalyByRoomRepository, times(1)).findAllByRoomId("room-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by room.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByThermometer_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyByThermometerRepository anomalyByThermometerRepository = mock(AnomalyByThermometerRepository.class);
        ArrayList<AnomalyByThermometer> mockedResult = new ArrayList<AnomalyByThermometer>();
        mockedResult.add(new AnomalyByThermometer("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:00").toInstant(), "room-1", 31.0));
        mockedResult.add(new AnomalyByThermometer("thermometer-1", Timestamp.valueOf("2024-02-02 00:00:00").toInstant(), "room-1", 32.0));
        when(anomalyByThermometerRepository.findAllByThermometerId("thermometer-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyByThermometerRepository(anomalyByThermometerRepository);

        List<AnomalyByThermometer> returnedResult = anomaliesController.getAnomaliesByThermometer("thermometer-1");

        verify(anomalyByThermometerRepository, times(1)).findAllByThermometerId("thermometer-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by thermometer.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByThermometer_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyByThermometerRepository anomalyByThermometerRepository = mock(AnomalyByThermometerRepository.class);
        ArrayList<AnomalyByThermometer> mockedResult = new ArrayList<AnomalyByThermometer>();
        when(anomalyByThermometerRepository.findAllByThermometerId("thermometer-1")).thenReturn(mockedResult);

        anomaliesController.setAnomalyByThermometerRepository(anomalyByThermometerRepository);

        List<AnomalyByThermometer> returnedResult = anomaliesController.getAnomaliesByThermometer("thermometer-1");

        verify(anomalyByThermometerRepository, times(1)).findAllByThermometerId("thermometer-1");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by thermometer.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByTime_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyByTimeRepository anomalyByTimeRepository = mock(AnomalyByTimeRepository.class);
        ArrayList<AnomalyByTime> mockedResult = new ArrayList<AnomalyByTime>();
        mockedResult.add(new AnomalyByTime(Timestamp.valueOf("2024-02-01 00:00:00").toInstant(), "thermometer-1", "room-1", 31.0));
        mockedResult.add(new AnomalyByTime(Timestamp.valueOf("2024-02-02 00:00:00").toInstant(), "thermometer-1", "room-1", 32.0));
        when(anomalyByTimeRepository.findAllBetweenStartDateAndEndDate(Timestamp.valueOf("2024-01-01 00:00:00").toInstant(), Timestamp.valueOf("2024-03-01 00:00:00").toInstant())).thenReturn(mockedResult);

        DateAndTimeConversionManager dateAndTimeConversionManager = mock(DateAndTimeConversionManager.class);
        when(dateAndTimeConversionManager.fromStringDateToInstant("2024-01-01")).thenReturn(Timestamp.valueOf("2024-01-01 00:00:00").toInstant());
        when(dateAndTimeConversionManager.fromStringDateToInstant("2024-03-01")).thenReturn(Timestamp.valueOf("2024-03-01 00:00:00").toInstant());

        anomaliesController.setAnomalyByTimeRepository(anomalyByTimeRepository);
        anomaliesController.setDateAndTimeConversionManager(dateAndTimeConversionManager);

        List<AnomalyByTime> returnedResult = anomaliesController.getAnomaliesByTime("2024-01-01", "2024-03-01");

        verify(dateAndTimeConversionManager, times(1)).fromStringDateToInstant("2024-01-01");
        verify(dateAndTimeConversionManager, times(1)).fromStringDateToInstant("2024-03-01");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by time.", mockedResult, returnedResult));
    }

    @Test
    public void getAnomaliesByTime_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomalyByTimeRepository anomalyByTimeRepository = mock(AnomalyByTimeRepository.class);
        ArrayList<AnomalyByTime> mockedResult = new ArrayList<AnomalyByTime>();
        when(anomalyByTimeRepository.findAllBetweenStartDateAndEndDate(Timestamp.valueOf("2024-01-01 00:00:00").toInstant(), Timestamp.valueOf("2024-03-01 00:00:00").toInstant())).thenReturn(mockedResult);

        DateAndTimeConversionManager dateAndTimeConversionManager = mock(DateAndTimeConversionManager.class);
        when(dateAndTimeConversionManager.fromStringDateToInstant("2024-01-01")).thenReturn(Timestamp.valueOf("2024-01-01 00:00:00").toInstant());
        when(dateAndTimeConversionManager.fromStringDateToInstant("2024-03-01")).thenReturn(Timestamp.valueOf("2024-03-01 00:00:00").toInstant());

        anomaliesController.setAnomalyByTimeRepository(anomalyByTimeRepository);
        anomaliesController.setDateAndTimeConversionManager(dateAndTimeConversionManager);

        List<AnomalyByTime> returnedResult = anomaliesController.getAnomaliesByTime("2024-01-01", "2024-03-01");

        verify(dateAndTimeConversionManager, times(1)).fromStringDateToInstant("2024-01-01");
        verify(dateAndTimeConversionManager, times(1)).fromStringDateToInstant("2024-03-01");
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting anomalies by time.", mockedResult, returnedResult));
    }

    @Test
    public void getThermometersWithAnomaliesCountBiggerThan_NonEmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomaliesService anomalyService = mock(AnomaliesService.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        mockedResult.add(new AnomaliesCount("thermometer-1", 5));
        mockedResult.add(new AnomaliesCount("thermometer-2", 2));
        when(anomalyService.findAllWithAnomaliesCountBiggerThan(1)).thenReturn(mockedResult);

        anomaliesController.setAnomaliesService(anomalyService);

        List<AnomaliesCount> returnedResult = anomaliesController.getThermometersWithAnomaliesCountBiggerThan(1);

        verify(anomalyService, times(1)).findAllWithAnomaliesCountBiggerThan(1);
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting thermometers with anomalies.", mockedResult, returnedResult));
    }

    @Test
    public void getThermometersWithAnomaliesCountBiggerThan_EmptyList_Test() {
        AnomaliesController anomaliesController = new AnomaliesController();

        AnomaliesService anomalyService = mock(AnomaliesService.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        when(anomalyService.findAllWithAnomaliesCountBiggerThan(10)).thenReturn(mockedResult);

        anomaliesController.setAnomaliesService(anomalyService);

        List<AnomaliesCount> returnedResult = anomaliesController.getThermometersWithAnomaliesCountBiggerThan(10);

        verify(anomalyService, times(1)).findAllWithAnomaliesCountBiggerThan(10);
        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when getting thermometers with anomalies.", mockedResult, returnedResult));
    }
}
