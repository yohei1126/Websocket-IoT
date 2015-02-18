import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class EncTest {
    public static void main(String args[]) {
        Pubnub pubnub = new Pubnub(
                "demo",
                "demo",
                "demo",
                "demo",
                true);
        try {
            pubnub.subscribe(new String[]{ "demo" }, new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    System.out.println("message " + message);
                }
                @Override
                public void errorCallback(String channel, PubnubError error) {
                    System.out.println("error " + error);
                }
            });
            while (true) {}
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }
}
