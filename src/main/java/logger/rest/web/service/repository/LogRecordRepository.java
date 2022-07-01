package logger.rest.web.service.repository;

import logger.rest.web.service.domain.LogRecord;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LogRecordRepository {

    List<LogRecord> findAllByUserId(String userId);

    List<LogRecord> findAllByParameters(Map<String, String> parameters);

    Optional<LogRecord> findByUserIdAndSessionId(String userId, String sessionId);

    List<LogRecord> findAllByActionType(String type);

    List<LogRecord> findAllByActionTimeBetween(LocalDateTime start, LocalDateTime end);

    LogRecord save(LogRecord logRecord);

    void deleteAll();
}
