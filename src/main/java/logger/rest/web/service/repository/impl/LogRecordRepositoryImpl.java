package logger.rest.web.service.repository.impl;

import logger.rest.web.service.domain.LogRecord;
import logger.rest.web.service.repository.LogRecordRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LogRecordRepositoryImpl implements LogRecordRepository {

    private final MongoTemplate mongoTemplate;

    public LogRecordRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<LogRecord> findAllByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, LogRecord.class);
    }

    @Override
    public List<LogRecord> findAllByParameters(Map<String, String> parameters) {
        Query query = new Query();
        parameters.forEach((name, value) -> query.addCriteria(Criteria.where(name).is(value)));
        return mongoTemplate.find(query, LogRecord.class);
    }

    @Override
    public Optional<LogRecord> findByUserIdAndSessionId(String userId, String sessionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("sessionId").is(sessionId));
        return Optional.ofNullable(mongoTemplate.findOne(query, LogRecord.class));
    }

    @Override
    public List<LogRecord> findAllByActionType(String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(type));
        return mongoTemplate.find(query, LogRecord.class);
    }

    @Override
    public List<LogRecord> findAllByActionTimeBetween(LocalDateTime start, LocalDateTime end) {
        Query query = new Query();
        query.addCriteria(Criteria.where("time").gte(start).lt(end));
        return mongoTemplate.find(query, LogRecord.class);
    }

    @Override
    public LogRecord save(LogRecord logRecord) {
        return mongoTemplate.save(logRecord);
    }

    @Override
    public void deleteAll() {
        mongoTemplate.remove(LogRecord.class);
    }
}
