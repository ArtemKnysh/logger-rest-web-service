package logger.rest.web.service.service.impl;

import logger.rest.web.service.domain.LogRecord;
import logger.rest.web.service.repository.LogRecordRepository;
import logger.rest.web.service.service.LogRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LogRecordServiceImpl implements LogRecordService {

    private final LogRecordRepository repository;

    public LogRecordServiceImpl(LogRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LogRecord> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<LogRecord> findAllByParameters(Map<String, String> parameters) {
        return repository.findAllByParameters(parameters);
    }

    @Override
    public Optional<LogRecord> findByUserIdAndSessionId(String userId, String sessionId) {
        return repository.findByUserIdAndSessionId(userId, sessionId);
    }

    @Override
    public List<LogRecord> findAllByActionType(String type) {
        return repository.findAllByActionType(type);
    }

    @Override
    public List<LogRecord> findAllByActionTimeBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findAllByActionTimeBetween(start, end);
    }

    @Override
    public LogRecord save(LogRecord logRecord) {
        return repository.save(logRecord);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
