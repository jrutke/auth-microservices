package rutke.julio.gptw.auth.endpoint.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    private final static String ACCOUNT_SID = "AC215e15db7455a0ad0bcc501774878a0e";
    private final static String AUTH_ID = "f799491978a4415a23c583e134afc709";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }

    public boolean sendSMS(String mobileNumber, String code){
        Message.creator(new PhoneNumber(mobileNumber), new PhoneNumber("+12053465218"),"Seu número de Autenticação é: "+code);
        return true;
    }

}
