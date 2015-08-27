package ua.com.otpbank.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.otpbank.tools.UUIDgenerator;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Igor on 07.05.2015.
 */
@Service("participantsService")
@Transactional
public class ParticipantsService {
    public ParticipantsService() {
    }

    protected static Logger logger = Logger.getLogger(ParticipantsService.class);

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public String getHeadOfList() {
        Session session = sessionFactory.getCurrentSession();

        Query sql_query = session.createSQLQuery("select name from vw_result_head").setMaxResults(1);

        if (sql_query.list().isEmpty())
            return "empty";
        else
            return sql_query.list().get(0).toString();
    }

    @Transactional(readOnly = false)
    public void addParticipant(String uuid, Integer orderNo, String name) {
        logger.debug("add participant <" + name + ">");
        Date timestamp = new Date();

        Session session = sessionFactory.getCurrentSession();

        Query addParticipant = session.createSQLQuery("insert into RESULTS (NAME, ORDERNO, TIMESTAMP, UUID) values (?, ?, ?, ?)")
                .setParameter(0, name)
                .setParameter(1, orderNo)
                .setParameter(2, timestamp)
                .setParameter(3, uuid);

        addParticipant.executeUpdate();

    }

    public String persistParticipants(List<String> participants) {
        logger.debug("run ParticipantsService->persistParticipants()->");
        UUIDgenerator uuidInstance = UUIDgenerator.getInstance();
        String uuid = uuidInstance.getNewUuid();
        Integer orderNo = 1;
        String result = "Data was successfully persisted\n with unique id: " + uuid;

        if(participants.isEmpty())
            return result="Nothing to persist! Participants list is empty.";
        if (!participants.isEmpty())
            for (String item : participants) {
                logger.debug("item: " + item);
                addParticipant(uuid, orderNo, item);
                //enrichParticipant(uuid, orderNo, item);
                orderNo++;
            }

        return result;
    }

    public List<String> changeListOrder(List<String> list) {
        String headName = getHeadOfList();
        String getHead = list.get(0);
        if (headName != null & getHead.equals(headName)) {
            list.add(getHead);
            list.remove(0);
        }
        return list;
    }


}
