package inc.temp.right.always.restcassandraapi.controllers;

import inc.temp.right.always.restcassandraapi.dto.AnomaliesCount;
import inc.temp.right.always.restcassandraapi.model.AnomalyByRoom;
import inc.temp.right.always.restcassandraapi.model.AnomalyByThermometer;
import inc.temp.right.always.restcassandraapi.model.AnomalyByTime;
import inc.temp.right.always.restcassandraapi.repositories.AnomalyByRoomRepository;
import inc.temp.right.always.restcassandraapi.repositories.AnomalyByThermometerRepository;
import inc.temp.right.always.restcassandraapi.repositories.AnomalyByTimeRepository;
import inc.temp.right.always.restcassandraapi.services.AnomaliesService;
import inc.temp.right.always.restcassandraapi.util.DateAndTimeConversionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AnomaliesController {
    @Autowired
    private AnomalyByRoomRepository anomalyByRoomRepository;
    @Autowired
    private AnomalyByThermometerRepository anomalyByThermometerRepository;
    @Autowired
    private AnomalyByTimeRepository anomalyByTimeRepository;
    @Autowired
    private AnomaliesService anomaliesService;
    @Autowired
    private DateAndTimeConversionManager dateAndTimeConversionManager;

    @GetMapping("/anomaliesByRoom/{roomId}")
    @ResponseBody
    public List<AnomalyByRoom> getAnomaliesByRoom(@PathVariable("roomId") String roomId) {
        return anomalyByRoomRepository.findAllByRoomId(roomId);
    }

    @GetMapping("/anomaliesByThermometer/{thermometerId}")
    @ResponseBody
    public List<AnomalyByThermometer> getAnomaliesByThermometer(@PathVariable("thermometerId") String thermometerId) {
        return anomalyByThermometerRepository.findAllByThermometerId(thermometerId);
    }

    @GetMapping("/anomaliesByTime/start/{start}/end/{end}")
    @ResponseBody
    public List<AnomalyByTime> getAnomaliesByTime(@PathVariable("start") String start, @PathVariable("end") String end) {
        return anomalyByTimeRepository.findAllBetweenStartDateAndEndDate(dateAndTimeConversionManager.fromStringDateToInstant(start), dateAndTimeConversionManager.fromStringDateToInstant(end));
    }

    @GetMapping("/anomalies/{threshold}")
    @ResponseBody
    public List<AnomaliesCount> getThermometersWithAnomaliesCountBiggerThan(@PathVariable("threshold") int threshold) {
        return anomaliesService.findAllWithAnomaliesCountBiggerThan(threshold);
    }

    public void setAnomalyByRoomRepository(AnomalyByRoomRepository anomalyByRoomRepository) {
        this.anomalyByRoomRepository = anomalyByRoomRepository;
    }

    public void setAnomalyByThermometerRepository(AnomalyByThermometerRepository anomalyByThermometerRepository) {
        this.anomalyByThermometerRepository = anomalyByThermometerRepository;
    }

    public void setAnomalyByTimeRepository(AnomalyByTimeRepository anomalyByTimeRepository) {
        this.anomalyByTimeRepository = anomalyByTimeRepository;
    }

    public void setAnomaliesService(AnomaliesService anomaliesService) {
        this.anomaliesService = anomaliesService;
    }

    public void setDateAndTimeConversionManager(DateAndTimeConversionManager dateAndTimeConversionManager) {
        this.dateAndTimeConversionManager = dateAndTimeConversionManager;
    }
}
