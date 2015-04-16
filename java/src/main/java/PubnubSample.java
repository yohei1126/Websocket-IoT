import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class PubnubSample {
    public static void main(String args[]) {
        Callback callback = new Callback() {
            @Override
            public void successCallback(String channel, Object message) {
                String crlf = System.getProperty("line.separator");
                System.out.print(crlf + "message " + message + crlf + "prompt:>");
            }
            @Override
            public void errorCallback(String channel, PubnubError error) {
                System.out.println("error " + error);
            }
        };

        Pubnub pubnub = new Pubnub("demo", "demo");
        try {
            pubnub.subscribe("demo", callback);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = "";
                do {
                    System.out.print("prompot:>");

                    input = br.readLine().trim();
                    if (!input.equals("")) {
                        pubnub.publish("demo", input, callback);
                    }
                } while (!input.equals(""));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }
}
