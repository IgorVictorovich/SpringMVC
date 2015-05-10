package ua.com.otpbank.domain;

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
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public String getHeadOfList(){
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT NAME FROM (SELECT NAME, COUNT(*) AS QNTY FROM (SELECT ORDERNO,NAME FROM RESULT WHERE ORDERNO=1) P GROUP BY 1 ORDER BY 2 DESC) T");
        query.setMaxResults(1);

       // List<String> listRes = query.list();

        return query.list().get(0).toString();
    }

    public Participants enrichParticipant(String uuid,Integer orderNo,String name){
        Participants participant = new Participants();
        Date timestamp = new Date();

        participant.setUuid(uuid);
        participant.setOrderNo(orderNo);
        participant.setName(name);
        participant.setTimestamp(timestamp);

        return participant;
    }

    public void persistParticipant(Participants participant){
        Session session = sessionFactory.getCurrentSession();
        session.save(participant);
    }

    public String persistParticipants(List<String> participants){
        UUIDgenerator uuidInstance = UUIDgenerator.getInstance();
        String uuid=uuidInstance.getNewUuid();
        Integer orderNo=1;
        String exception=null;

        for(String item:participants){
            try{
                persistParticipant(enrichParticipant(uuid, orderNo, item));
            }
            catch (Exception ex){exception=ex.toString();break;}
            orderNo++;
        }

        return exception;
    }

    public List<String> changeListOrder(List<String> list){
        String headName=getHeadOfList();
        String getHead = list.get(0);
        if (headName!=null & getHead.equals(headName)){
        list.add(getHead);
        list.remove(0);
        }
        return list;
    }



}
